package com.moretech.map.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Координаты клиента")
public class Point {
    //todo сделать описание, не понятно в чём разница между coordinates и др координатами
    String coordinates;
    Integer latitude; //56
    Long decLatitude;//800584
    Integer longitude;//60
    Long decLongitude;//675637
}
