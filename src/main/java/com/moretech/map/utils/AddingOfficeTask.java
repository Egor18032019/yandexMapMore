package com.moretech.map.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moretech.map.entities.OfficeEntity;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class AddingOfficeTask {

    public static List<OfficeEntity> giveMeOfficeWithTask(String data) throws JsonProcessingException {
// Считываем json
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(data);
        JsonNode features = root.path("features");
        List<OfficeEntity> list = new ArrayList<>();
        for (int i = 0; i < features.size(); i++) {
            JsonNode geometry = features.get(i).path("geometry");
            JsonNode coordinates = geometry.path("coordinates");
            //[54.101291,54.102436]
            OfficeEntity office = new OfficeEntity();
            office.setCoords(coordinates.toString());
            String foo = String.valueOf(coordinates.get(0));
            String bar = String.valueOf(coordinates.get(1));
            Integer latitude = Integer.valueOf(foo.split("\\.")[0]);
            Long decLatitude = Long.valueOf( foo.split("\\.")[1]); //101291
            Integer longitude = Integer.valueOf(bar.split("\\.")[0]); //54
            Long decLongitude = Long.valueOf(bar.split("\\.")[1]); //102436
            office.setLatitude(latitude);
            office.setDecLatitude(decLatitude);
            office.setLongitude(longitude);
            office.setDecLongitude(decLongitude);
            int roureLength = (int) (Math.random() * 1000);
            office.setRoureLength(roureLength);
            office.setWithdrawCash((int) (Math.random() * 10)%2==0);
            office.setCurrencyExchange((int) (Math.random() * 10)%2==0);
            office.setCardIssue((int) (Math.random() * 10)%2==0);
            list.add(office);

        }

        return list;
    }
}
