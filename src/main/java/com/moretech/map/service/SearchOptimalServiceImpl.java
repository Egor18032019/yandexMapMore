package com.moretech.map.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moretech.map.entities.OfficeEntity;
import com.moretech.map.repositories.OfficeRepository;
import com.moretech.map.schemas.*;
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
    public OptimalOfficeResponse giveOptimalOffice(TaskListRequest request) throws JsonProcessingException {
//        Получает координаты центра отсчета.
        String[] centre = request.getPointCoordinates().split(",");
        int longitude = Integer.parseInt(centre[0].trim().split("\\.")[0]);//54.800584
        int latitude = Integer.parseInt(centre[1].trim().split("\\.")[0]);//54.675637
        Long decLatitude = Long.valueOf(centre[0].trim().split("\\.")[1]);//800584
        Long decLongitude = Long.valueOf(centre[1].trim().split("\\.")[1]);//675637
        Point point = new Point(request.getPointCoordinates(), latitude, decLatitude, longitude, decLongitude);

//         * Получает отделения в этой окружности которые подходят по списку задач.
        List<OfficeEntity> neighborhood = officeRepository.findByCoords(point);
// Получаем выборку из отделений в neighborhood которые подходят по списку задач
        //TODO если длина 0 то увеличиваем радиус ?
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
        if (neighborhoodTask.size() == 0) {
            //TODO запускаем заново поиск ?
            // TODO или ответ на фронт и пользователь решает увеличить радиус или уменьшить требования?
            return null;
        }
        //TODO если загруженость офиса больше 90% исключать из списка ? а если после исключения длина ноль ?
//        Загруженность в последствии получать от другого сервиса который получает ее например с электронной очереди
        List<OfficeEntity> neighborhoodTaskWithWorkload = workloadOfficeSort.giveMeWorkloadOfficeSort(neighborhoodTask);


        //        Считает и сравнивает маршруты до выбранных офисов.
        List<OfficeEntity> neighborhoodTaskWithLength = routeLengthSort.giveMeListOfficeWithLengthSort(neighborhoodTaskWithWorkload, request.getPointCoordinates());


        OfficeEntity answer = neighborhoodTaskWithLength.get(0);
        OptimalOfficeResponse optimalOfficeResponse = new OptimalOfficeResponse();
        optimalOfficeResponse.setWorkload(answer.getWorkload());
        String uri = answer.getCoords().toString();   //37.62,55.75
        optimalOfficeResponse.setUri(uri);

        return optimalOfficeResponse;

    }

    @Override
    public OfficesResponse getAllOffices(Point point) throws JsonProcessingException {
        List<Office> neighborhood = officeRepository.findAllOffices(point);


        return new OfficesResponse(neighborhood);
    }
}
