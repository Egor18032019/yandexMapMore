package com.moretech.map.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moretech.map.schemas.OptimalOfficeResponse;
import com.moretech.map.schemas.TaskListRequest;

public interface SearchOptimalService {
    OptimalOfficeResponse giveOptimalOffice(TaskListRequest request) throws JsonProcessingException;
}
