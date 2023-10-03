package com.moretech.map.schemas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Point {
    String coord;
    Integer latitude; //56
    Long decLatitude;//800584
    Integer longitude;//60
    Long decLongitude;//675637
}
