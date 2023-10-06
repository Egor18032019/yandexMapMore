package com.moretech.map.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moretech.map.exception.CheckException;
import com.moretech.map.schemas.*;
import com.moretech.map.service.SearchOptimalServiceImpl;
import com.moretech.map.utils.EndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Отвечает за обработку запросов, связанных с офисам/отделениями")
@RestController
@RequestMapping(EndPoint.api)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfficeController {
    SearchOptimalServiceImpl searchOptimalServiceImpl;

    public OfficeController(SearchOptimalServiceImpl searchOptimalServiceImpl) {
        this.searchOptimalServiceImpl = searchOptimalServiceImpl;
    }

    @Operation(
            summary = "Выдача оптимального отделения",
            description = "Получает список задач клиента\n" +
                    "    Отдает оптимальное отделение"
    )
    @PostMapping(value = EndPoint.check)
    public OptimalOfficeResponse getMeOptimalOffice(
            @Parameter(schema = @Schema(implementation = TaskListRequest.class))
            @RequestBody() TaskListRequest request) throws JsonProcessingException, CheckException {
        // todo проверка на координату ? похожа ли координата на координату(максимальная/минимальная долгота и широта)
        if (request.getPointCoordinates().trim().isEmpty()) {
            throw new CheckException("Coordinates point not transmitted", "Координаты точки не переданы");
        }
        if (!request.getPointCoordinates().contains(".")) {
            throw new CheckException("Incorrect coordinate format", "Не правильный формат координат");
        }
        return searchOptimalServiceImpl.giveOptimalOffice(request);
    }

    @GetMapping(value = EndPoint.all)
    public OfficesResponse getMeOptimalOffice(
            @Parameter(schema = @Schema(implementation = OfficesResponse.class))
            @RequestBody() Point request) throws JsonProcessingException, CheckException {
        if (request.getCoordinates().trim().isEmpty()) {
            throw new CheckException("Coordinates point not transmitted", "Координаты точки не переданы");
        }
        if (!request.getCoordinates().contains(".")) {
            throw new CheckException("Incorrect coordinate format", "Не правильный формат координат");
        }
        return searchOptimalServiceImpl.getAllOffices(request);
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