package com.example.demo.ordering.validators.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExcelException extends RuntimeException {

    public ExcelException(String message) {
        super(message);
    }
}
