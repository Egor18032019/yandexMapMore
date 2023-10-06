package com.moretech.map.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moretech.map.schemas.OfficesResponse;
import com.moretech.map.schemas.OptimalOfficeResponse;
import com.moretech.map.schemas.Point;
import com.moretech.map.schemas.TaskListRequest;

public interface SearchOptimalService {
    OptimalOfficeResponse giveOptimalOffice(TaskListRequest request) throws JsonProcessingException;
    OfficesResponse getAllOffices(Point request) throws JsonProcessingException;
}
