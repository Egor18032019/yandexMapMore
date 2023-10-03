package com.moretech.map.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class OfficeEntity {
    Long id;
    Integer numberOffice;
    // ид не может являться номером отделения так как номер отделения не уникален
    Boolean withdrawCash;
    Boolean currencyExchange;
    Boolean cardIssue;

    List<String> coords; //point: [56.800584, 60.675637]} координаты отделения

    Integer latitude; //56
    Long decLatitude;//800584
    Integer longitude;//60
    Long decLongitude;//675637

    Integer workload;
    Integer roureLength;

    public OfficeEntity(Long id, Integer numberOffice, Boolean withdrawCash, Boolean currencyExchange, Boolean cardIssue,
                        List<String> coords, Integer latitude, Long decLatitude, Integer longitude, Long decLongitude) {
        this.id = id;
        this.numberOffice = numberOffice;
        this.withdrawCash = withdrawCash;
        this.currencyExchange = currencyExchange;
        this.cardIssue = cardIssue;
        this.coords = coords;
        this.latitude = latitude;
        this.decLatitude = decLatitude;
        this.longitude = longitude;
        this.decLongitude = decLongitude;
    }

    @Override
    public String toString() {
        return "OfficeEntity{" +
                "id=" + id +
                ", numberOffice=" + numberOffice +
                ", withdrawCash=" + withdrawCash +
                ", currencyExchange=" + currencyExchange +
                ", cardIssue=" + cardIssue +
                ", coords='" + coords + '\'' +
                ", latitude=" + latitude +
                ", decLatitude=" + decLatitude +
                ", longitude=" + longitude +
                ", decLongitude=" + decLongitude +
                ", workload=" + workload +
                ", roureLength=" + roureLength +
                '}';
    }
}
