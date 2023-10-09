package com.moretech.map.utils;

import com.moretech.map.schemas.Point;
import com.moretech.map.schemas.TaskListRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CheckRequest {

    public boolean isCheckPassed(Point request) {
//        String[] coordination = request.getCoordinates().split(",");

        // минимум может быть 6,5
        if (request.getCoordinates().trim().isEmpty()) {

            return false;
//        } else if (!coordination[0].contains(".") || !coordination[1].contains(".")) { // минимум может быть 6,5
//
//            return true;
        } else return request.getCoordinates().length() > 3;
    }

    public boolean isCheckTaskListRequestPassed(TaskListRequest request) {
        if (!request.getPointCoordinates().trim().isEmpty()) {
            return true;
        } else return request.getPointCoordinates().length() > 3;
    }
}
