package com.moretech.map.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moretech.map.schemas.OptimalOfficeRequest;
import com.moretech.map.schemas.TaskListRequest;
import com.moretech.map.service.SearchOptimalServiceImpl;
import com.moretech.map.utils.EndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Контроллер для проверки скобок")
@RestController
@RequestMapping(EndPoint.api)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OptimalController {
    SearchOptimalServiceImpl searchOptimalServiceImpl;

    public OptimalController(SearchOptimalServiceImpl searchOptimalServiceImpl) {
        this.searchOptimalServiceImpl = searchOptimalServiceImpl;
    }

    @Operation(
            summary = "Выдача оптимального отделения",
            description = "Получает список задач клиента\n" +
                    "    Отдает оптимальное отделение"
    )
    @PostMapping(value = EndPoint.check)
    public OptimalOfficeRequest checkBrackets(@RequestBody() TaskListRequest request) throws JsonProcessingException {
        // todo проверка на координату ? похожа ли координата на координату(максимальная/минимальная долгота и широта)

        return searchOptimalServiceImpl.giveOptimalOffice(request);
    }
}
/*
POST http://localhost:8080/api/check
Content-Type: application/json

{
  "point": "56.800584, 60.675637",
  "cardIssue": true,
  "withdrawCash": true,
  "currencyExchange":true
}

 */