package com.moretech.map.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moretech.map.entities.OfficeEntity;
import com.moretech.map.schemas.Point;
import com.moretech.map.utils.AddingOfficeTask;
import com.moretech.map.utils.Const;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class OfficeRepository {

    /**
     * Получает отделения в этой окружности(+3) которые подходят по списку задач.
     *
     * @param point
     * @return список ближайших офисов
     * @throws JsonProcessingException
     */
    public List<OfficeEntity> findByCoords(Point point) throws JsonProcessingException {
        List<OfficeEntity> officeEntityList = new ArrayList<>();
        int count = 0;
        while (officeEntityList.size() == 0) {
            point.setLongitude(point.getLatitude() + count);
            point.setLongitude(point.getLongitude() + count);
            count = count + 3;
            // todo радиус в какую сторону должен увеличаться ?
            String data = getPostsPlainJSON(point);
            System.out.println(data);
            officeEntityList = AddingOfficeTask.giveMeOfficeWithTask(data);
        }
        return officeEntityList;
    }

    /**
     * Запрос к Яндекс апи для получение списка отделений
     * Впоследствии заменить на бд у которой хорошая скорость отдачи данных
     *
     * @param point
     * @return
     */
    public String getPostsPlainJSON(Point point) {
        RestTemplate restTemplate = new RestTemplate();
        String maxLatitude = point.getLatitude() + Const.Radius + "." + point.getDecLatitude();
        String maxLongitude = point.getLongitude() + Const.Radius + "." + point.getDecLongitude();
        String apiKey = "4492ad26-ceda-410a-a80d-1a5903eb986f";
        String url = "https://search-maps.yandex.ru/v1/?" +
                "text=Сбербанк&" +
                "type=biz&" +
                "lang=ru_RU&" +
                "ll=" + point.getCoord() + "&" +// центр
                "spn=" + maxLatitude + "," + maxLongitude + "&" + // максимум
                "apikey=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
}
/*
POST http://localhost:8080/api/check
Content-Type: application/json

{
  "point": "54.800584, 54.675637",
  "cardIssue": true,
  "withdrawCash": false,
  "currencyExchange": true
}



####
GET https://search-maps.yandex.ru/v1/
    ? apikey=4492ad26-ceda-410a-a80d-1a5903eb986f
    & text=Сбербанк
    & lang=ru_RU
    & ll=54.800584, 54.675637
    & spn=57.800584, 57.675637

 */