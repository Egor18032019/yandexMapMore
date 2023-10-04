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
    String uri;
    int workload;// 90 => то есть 90%

}
