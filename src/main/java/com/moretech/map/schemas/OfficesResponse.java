package com.moretech.map.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Координаты отделений с загрузкой")
public class OfficesResponse {

    @Schema(description = "Список координат отделений с загрузкой")
    List<Office> offices = new ArrayList<>();

}
