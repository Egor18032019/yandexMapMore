package com.moretech.map.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.moretech.map.entities.OfficeEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class RouteLengthSortImpl implements RouteLengthSort {

    //TODO заглушка .. Найти как яндекс апи отдает маршрут и от туда получать длину
    @Override
    public List<OfficeEntity> giveMeListOfficeWithLengthSort(List<OfficeEntity> officeEntities, String point) {
        RestTemplate restTemplate = new RestTemplate();
        List<OfficeEntity> list = new ArrayList<>();
        for (OfficeEntity office : officeEntities) {
            List<String> address = office.getCoords();
            String longitude = address.get(0).trim() ;
            String latitude = address.get(1).trim() ;
//            point "54.800584, 54.675637",
            String longitudePoint = point.split(",")[0].trim();
            String latitudePoint = point.split(",")[1].trim();
            //TODO вынести в отдельный метод
            try {
            String url = "https://api.mapbox.com/directions/v5/mapbox/walking/" +
                    longitude + "%2C" + latitude + "%3B" +
                    longitudePoint + "%2C" + latitudePoint +
                    "?" +
                    "alternatives=true&continue_straight=true" +
                    "&geometries=geojson" +
                    "&language=en" +
                    "&overview=full" +
                    "&steps=true" +
                    "&access_token=pk.eyJ1IjoiZWdvcnZpY3Rvcm92aWNoIiwiYSI6ImNsbmE1ZTNsZzAwajcydXAxYnFmdjA1NTkifQ.Oyodp7HaCdLolfU0JI0a9A";
                RequestEntity<Void> request = RequestEntity.get(URI.create(url))
                        .accept(MediaType.APPLICATION_JSON)
                        .build();
                ResponseEntity<JsonNode> response = restTemplate.exchange(request, JsonNode.class);
                JsonNode data =response.getBody();

                //TODO проверку сделай
                assert data != null;
                JsonNode routes = data.path("routes");
                JsonNode distance = routes.get(0).path("distance");

                int roureLength = distance.asInt();
                office.setRoureLength(roureLength);
                list.add(office);
        } catch (RestClientException e) {
                System.out.println(e.getMessage());
                System.out.println("получение маршрута не удалось");
            }


        }

        list.sort((a, b) -> {
            if (Objects.equals(a.getRoureLength(), b.getRoureLength())) {
                return 0;
            }
            if (a.getRoureLength() > b.getRoureLength()) {
                return 1;
            }
            return -1;
        });
        return list;
    }


}

