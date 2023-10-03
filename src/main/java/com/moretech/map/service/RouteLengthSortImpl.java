package com.moretech.map.service;

import com.moretech.map.entities.OfficeEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class RouteLengthSortImpl implements RouteLengthSort {
    //TODO заглушка .. Найти как яндекс апи отдает маршрут и от туда получать длину
    @Override
    public List<OfficeEntity> giveMeListOfficeWithLengthSort(List<OfficeEntity> officeEntities) {
        List<OfficeEntity> list = new ArrayList<>();
        for (OfficeEntity office : officeEntities) {
            int roureLength = (int) (Math.random() * 1000);
            office.setRoureLength(roureLength);
            list.add(office);
        }

        list.sort((a, b) -> {
            if (Objects.equals(a.getRoureLength(), b.getRoureLength())) {
                return 0;
            }
            if (a.getRoureLength() > b.getRoureLength()) {
                return 1;
            }
            return -1;
        });
        return list;
    }
}

