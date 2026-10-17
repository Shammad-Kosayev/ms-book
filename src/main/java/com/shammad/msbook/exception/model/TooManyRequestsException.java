package com.shammad.msbook.exception.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TooManyRequestsException extends RuntimeException {
    private String message;

    public TooManyRequestsException(String message) {
        super(message);
        this.message = message;
    }
}
