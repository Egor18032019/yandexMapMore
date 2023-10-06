package com.moretech.map.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "координаты офиса и его загрузка")
public class Office {
    @Schema(description = "координаты офиса в виде = [60.523104,56.869266]")
    List<String> coordinates; //[60.523104,56.869266]
    @Schema(description = "Загрузка офиса в виде = 99")
    int workload;
}
