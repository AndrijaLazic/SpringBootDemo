package com.example.demo.sweeping.validators.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoAvailableTaskException extends RuntimeException{
    public NoAvailableTaskException(String message) {
        super(message);
    }
}
