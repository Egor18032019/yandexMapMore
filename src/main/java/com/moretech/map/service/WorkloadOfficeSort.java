package com.moretech.map.service;

import com.moretech.map.entities.OfficeEntity;

import java.util.List;

public interface WorkloadOfficeSort {
    List<OfficeEntity> giveMeWorkloadOfficeSort(List<OfficeEntity> officeEntities);
}
