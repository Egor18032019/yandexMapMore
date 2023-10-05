package com.moretech.map.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moretech.map.entities.OfficeEntity;

import java.util.List;

public interface RouteLengthSort {
    /**
     * Расчитывает длину и обогащает список офисов длиной до клиента.
     * + сортирует полученный список
     *
     * @param officeEntities
     * @param point
     * @return
     * @throws JsonProcessingException
     */
    List<OfficeEntity> giveMeListOfficeWithLengthSort(List<OfficeEntity> officeEntities, String point) throws JsonProcessingException;
}
