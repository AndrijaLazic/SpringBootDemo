package com.example.demo.Domain.Shared.Exceptions;

public class BaseDatabaseException extends RuntimeException {

    public BaseDatabaseException(String message) {
        super(message);
    }
}
