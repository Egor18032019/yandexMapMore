package com.moretech.map.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Кастомный exception
 */
@Getter
@Setter
public class CheckException extends Exception {

    private String message;
    private String details;

    public CheckException(String message, String details) {
        super();
        this.message = message;
        this.details = details;
    }
}
