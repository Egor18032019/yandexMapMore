package com.moretech.map.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moretech.map.entities.OfficeEntity;
import com.moretech.map.repositories.OfficeRepository;
import com.moretech.map.schemas.OptimalOfficeRequest;
import com.moretech.map.schemas.Point;
import com.moretech.map.schemas.TaskListRequest;
import com.moretech.map.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchOptimalServiceImpl implements SearchOptimalService {
    final OfficeRepository officeRepository;
    final WorkloadOfficeSortImpl workloadOfficeSort;
    final RouteLengthSortImpl routeLengthSort;

    public SearchOptimalServiceImpl(OfficeRepository officeRepository, WorkloadOfficeSortImpl workloadOfficeSort, RouteLengthSortImpl routeLengthSort) {
        this.officeRepository = officeRepository;
        this.workloadOfficeSort = workloadOfficeSort;
        this.routeLengthSort = routeLengthSort;
    }

    /*


Получает список задач клиента.
(если нет то => обдумать)

Делает выборку из 2-3 офисов которые подходят.
Считает и сравнивает маршруты до выбранных офисов.
Отдает оптимальное отделение.
 */
    @Override
    public OptimalOfficeRequest giveOptimalOffice(TaskListRequest request) throws JsonProcessingException {
//        Получает координаты центра отсчета.
        String[] centre = request.getPoint().split(",");
        int latitude = Integer.parseInt(centre[0].split("\\.")[0]);
        int longitude = Integer.parseInt(centre[0].split("\\.")[0]);
        Long decLatitude = Long.valueOf(centre[1].split("\\.")[1]);//800584
        Long decLongitude = Long.valueOf(centre[1].split("\\.")[1]);//800584


        Point point = new Point(request.getPoint(),latitude, decLatitude, longitude, decLongitude);

//        Получает отделения в этой окружности(R примерно 3км) которые подходят по списку задач.
        //TODO если длина 0 то увеличиваем радиус
        List<OfficeEntity> neighborhood = officeRepository.findByCoords(point);
// Получаем выборку из отделений в neighborhood которые мапяться по списку задач
        //TODO если длина 0 то увеличиваем радиус
        List<OfficeEntity> neighborhoodTask = neighborhood.stream()
                .filter(office -> {
                    if (request.getCardIssue()) {
                        return office.getCardIssue();
                    }
                    return true;
                })
                .filter(office -> {
                    if (request.getWithdrawCash()) {
                        return office.getWithdrawCash();
                    }
                    return true;
                }).filter(office -> {
                    if (request.getCurrencyExchange()) {
                        return office.getCurrencyExchange();
                    }
                    return true;
                })
                .toList();
        //TODO если длина 0 то увеличиваем радиус
//        Где то берет загруженность.
        List<OfficeEntity> neighborhoodTaskWithWorkload = workloadOfficeSort.giveMeWorkloadOfficeSort(neighborhoodTask);


        //        Считает и сравнивает маршруты до выбранных офисов.
        //TODO если длина 0 то увеличиваем радиус
        List<OfficeEntity> neighborhoodTaskWithLength = routeLengthSort.giveMeListOfficeWithLengthSort(neighborhoodTaskWithWorkload);
        OfficeEntity answer = neighborhoodTaskWithLength.get(0);
        OptimalOfficeRequest optimalOfficeRequest = new OptimalOfficeRequest();
        optimalOfficeRequest.setWorkload(answer.getWorkload());
        //TODO или конструктор ??
        String uri = answer.getCoords();   //37.62,55.75
        optimalOfficeRequest.setUri(uri);

        return optimalOfficeRequest;
    }
}
