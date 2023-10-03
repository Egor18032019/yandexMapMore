package com.moretech.map.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moretech.map.entities.OfficeEntity;

import java.util.List;

public interface RouteLengthSort {
    List<OfficeEntity> giveMeListOfficeWithLengthSort(List<OfficeEntity> officeEntities, String point) throws JsonProcessingException;
}
