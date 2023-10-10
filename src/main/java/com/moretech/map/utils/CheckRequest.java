package com.moretech.map.utils;

import com.moretech.map.schemas.Point;
import com.moretech.map.schemas.TaskListRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CheckRequest {

    public boolean isCheckNotPassed(Point request) {
        String coordination = request.getCoordinates();
        if (request.getCoordinates().trim().isEmpty()) {
            return true;
        } else if (!coordination.contains(",")) {
            return true;
        } else return request.getCoordinates().length() < 3;
    }

    public boolean isCheckTaskListRequestNotPassed(TaskListRequest request) {
        String coordination = request.getPointCoordinates();
        if (coordination.trim().isEmpty()) {
            return true;
        } else if (!coordination.contains(",")) {
            return true;
        } else return request.getPointCoordinates().length() < 3;
    }
}
