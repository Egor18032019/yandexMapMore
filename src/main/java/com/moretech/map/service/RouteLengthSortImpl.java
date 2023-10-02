package com.moretech.map.service;

import com.moretech.map.entities.OfficeEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RouteLengthSortImpl implements RouteLengthSort {
    @Override
    public List<OfficeEntity> giveMeListOfficeWithLengthSort(List<OfficeEntity> officeEntities) {
        List<OfficeEntity> list = new ArrayList<>();
        for (OfficeEntity office : officeEntities) {
            int roureLength = (int) (Math.random() * 1000);
            System.out.println("roureLength " + roureLength);
            office.setRoureLength(roureLength);
            list.add(office);
        }
        return list;
    }
}

