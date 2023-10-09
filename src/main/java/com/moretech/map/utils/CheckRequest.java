package com.moretech.map.utils;

import com.moretech.map.schemas.Point;
import com.moretech.map.schemas.TaskListRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CheckRequest {

    public boolean checkPoint(Point request) {
        String[] coordination = request.getCoordinates().split(",");

        if (request.getCoordinates() == null || request.getCoordinates().trim().isEmpty()) {

            return true;
        } else if (!coordination[0].contains(".") || !coordination[1].contains(".")) {

            return true;
        } else if (request.getLongitude() <= 0 && request.getDecLongitude() <= 0
                && request.getDecLatitude() <= 0 && request.getLatitude() <= 0) {

            return true;
        } else
            return false;
    }

    public boolean checkTaskListRequest(TaskListRequest request) {
        String[] coordination = request.getPointCoordinates().split(",");

        if (request.getPointCoordinates() == null || request.getPointCoordinates().trim().isEmpty()) {

            return true;
        } else if (!coordination[0].contains(".") || !coordination[1].contains(".")) {

            return true;
        } else
            return false;
    }
}
