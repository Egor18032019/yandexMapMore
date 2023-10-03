package com.moretech.map.schemas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskListRequest {
    //координаты откуда
    String point; //point: [56.800584, 60.675637]}
    // список задач
    Boolean cardIssue;
    Boolean withdrawCash;
    Boolean currencyExchange;

}
