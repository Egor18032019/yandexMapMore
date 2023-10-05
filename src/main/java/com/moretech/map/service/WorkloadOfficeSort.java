package com.moretech.map.service;

import com.moretech.map.entities.OfficeEntity;

import java.util.List;

public interface WorkloadOfficeSort {
    /**
     * Обогощает список офисов загруженостью
     *
     * @param officeEntities
     * @return список офисов с загруженостью
     */
    List<OfficeEntity> giveMeWorkloadOfficeSort(List<OfficeEntity> officeEntities);
}
