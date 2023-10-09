package com.moretech.map.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Кастомный exception
 */
@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CheckException extends Exception {

    private String message;
    private String details;

    public CheckException(String message, String details) {
        super();
        this.message = message;
        this.details = details;
    }
}
