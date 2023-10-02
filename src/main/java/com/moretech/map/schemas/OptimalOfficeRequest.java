package com.moretech.map.schemas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptimalOfficeRequest {
    String uri; //"yandexmaps://maps.yandex.ru/?ll=37.62,55.75&z=12"
    int workload;// 90 => то есть 90%

}
