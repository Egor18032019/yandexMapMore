package com.moretech.map.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входящий запрос с координатами клиента и со списком задач")
public class TaskListRequest {
    @Schema(description = "координаты клиента в формате 60.497874,56.926760 ")
    String pointCoordinates; //point: [56.800584, 60.675637]}
    // список задач
    @Schema(description = "true/false выпуск карт ")
    Boolean cardIssue;
    @Schema(description = "true/false снятие наличных ")
    Boolean withdrawCash;
    @Schema(description = "true/false обмен валют")
    Boolean currencyExchange;

}
