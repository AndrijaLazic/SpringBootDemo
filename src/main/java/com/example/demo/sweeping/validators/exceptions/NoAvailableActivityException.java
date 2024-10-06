package com.example.demo.sweeping.validators.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoAvailableActivityException extends RuntimeException {

    public NoAvailableActivityException(String message) {
        super(message);
    }
}
