package com.moretech.map.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moretech.map.entities.OfficeEntity;
import com.moretech.map.schemas.Point;
import com.moretech.map.utils.AddingOfficeTask;
import com.moretech.map.utils.Const;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class OfficeRepository {

    private static final List<OfficeEntity> GOOD_ENTITIES = List.of(
            new OfficeEntity(1L, 2, true, true, true, "22.11,33.144",
                    111, 222L, 333, 444L),
            new OfficeEntity(2L, 3, false, true, true, "22.11,33.144",
                    55, 222L, 55, 444L),
            new OfficeEntity(3L, 3, false, false, true, "22.11,33.144",
                    77, 222L, 77, 444L));

    public Optional<OfficeEntity> findById(Long id) {

        return GOOD_ENTITIES
                .stream()
                .filter(goodDto -> Objects.equals(goodDto.getId(), id))
                .findFirst();
    }


    public List<OfficeEntity> findByCoords(Point point) throws JsonProcessingException {

        String data = getPostsPlainJSON(point);
        System.out.println(data);

        return AddingOfficeTask.giveMeOfficeWithTask(data);
    }

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
                "spn=" + maxLatitude +"," + maxLongitude + "&" + // максимум
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