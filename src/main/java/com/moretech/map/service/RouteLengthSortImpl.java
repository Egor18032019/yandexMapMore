package com.moretech.map.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moretech.map.entities.OfficeEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class RouteLengthSortImpl implements RouteLengthSort {
    //TODO заглушка .. Найти как яндекс апи отдает маршрут и от туда получать длину
    @Override
    public List<OfficeEntity> giveMeListOfficeWithLengthSort(List<OfficeEntity> officeEntities, String point) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        List<OfficeEntity> list = new ArrayList<>();
        for (OfficeEntity office : officeEntities) {
            List<String> address = office.getCoords();
            // TODO правильно ли longitude and  latitude - не перепутал ли местами
            String longitude = address.get(0) ;
            String latitude = address.get(1) ;
//            point "54.800584, 54.675637",
            String longitudePoint = point.split(",")[0].trim();
            String latitudePoint = point.split(",")[1].trim();
            try {
            String url = "https://api.mapbox.com/directions/v5/mapbox/walking/" +
                    longitude + "%2C" + latitude + "%3B" +
                    longitudePoint + "%2C" + latitudePoint +
//                    "-74.000213%2C40.726065%3B-74.044936%2C40.738287" +
                    "?alternatives=true&continue_straight=true" +
                    "&geometries=geojson" +
                    "&language=en" +
                    "&overview=full" +
                    "&steps=true" +
                    "&access_token=pk.eyJ1IjoiZWdvcnZpY3Rvcm92aWNoIiwiYSI6ImNsbmE1ZTNsZzAwajcydXAxYnFmdjA1NTkifQ.Oyodp7HaCdLolfU0JI0a9A";
            String data = restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
                System.out.println("получение маршрута не удалось");;
            }
            /*
https://api.mapbox.com/directions/v5/mapbox/walking/-74.000213%2C40.726065%3B-74.044936%2C40.738287?alternatives=true&continue_straight=true&geometries=geojson&language=en&overview=full&steps=true&access_token=YOUR_MAPBOX_ACCESS_TOKEN
https://api.mapbox.com/directions/v5/mapbox/walking/53.697968%2C54.601144%3B  54.800584%2C54.675637?alternatives=true&continue_straight=true&geometries=geojson&language=en&overview=full&steps=true&access_token=pk.eyJ1IjoiZWdvcnZpY3Rvcm92aWNoIiwiYSI6ImNsbmE1ZTNsZzAwajcydXAxYnFmdjA1NTkifQ.Oyodp7HaCdLolfU0JI0a9A

GET https://api.mapbox.com/directions/v5/mapbox/walking/53.697968%2C54.601144%3B54.800584%2C54.675637?alternatives=true&continue_straight=true&geometries=geojson&language=en&overview=full&steps=true&access_token=pk.eyJ1IjoiZWdvcnZpY3Rvcm92aWNoIiwiYSI6ImNsbmE1ZTNsZzAwajcydXAxYnFmdjA1NTkifQ.Oyodp7HaCdLolfU0JI0a9A

 */
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(data);
//            JsonNode features = root.path("features");
            int roureLength = (int) (Math.random() * 1000);
            office.setRoureLength(roureLength);
            list.add(office);
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

