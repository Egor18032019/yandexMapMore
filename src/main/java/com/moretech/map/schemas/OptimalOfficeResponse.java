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
@Schema(description = "Ответ API включает себя координаты оптимального отделения и загрузку этого отделения")
public class OptimalOfficeResponse {
    @Schema(description = "координаты отделения")
    String uri;
    @Schema(description = "загруженность отделения")
    int workload;// 90 => то есть 90%
}
