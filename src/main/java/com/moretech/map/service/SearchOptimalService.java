package com.moretech.map.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moretech.map.schemas.OptimalOfficeRequest;
import com.moretech.map.schemas.TaskListRequest;

public interface SearchOptimalService {
    OptimalOfficeRequest giveOptimalOffice(TaskListRequest request) throws JsonProcessingException;
}
