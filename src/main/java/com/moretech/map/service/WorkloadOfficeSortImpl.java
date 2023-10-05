package com.moretech.map.service;

import com.moretech.map.entities.OfficeEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkloadOfficeSortImpl implements WorkloadOfficeSort {
    //TODO если загруженость офиса больше 90% исключать из списка ? а если после исключения длина ноль ?
    //todo или мы из этого отсортированого списка забираем несколько самых не загруженных ?
    // а что важнее загрузка или длина ? по опросам длина !
    // отсекание делать позже ?
    @Override
    public List<OfficeEntity> giveMeWorkloadOfficeSort(List<OfficeEntity> officeEntities) {
        List<OfficeEntity> list = new ArrayList<>();
        for (OfficeEntity office : officeEntities) {
            int workLoad = (int) (Math.random() * 100);
            office.setWorkload(workLoad);
            list.add(office);
        }
        list.sort((a, b) -> {
            if (Objects.equals(a.getWorkload(), b.getWorkload())) {
                return 0;
            }
            if (a.getWorkload() > b.getWorkload()) {
                return 1;
            }
            return -1;
        });
        return list;
    }
}
