package com.example.catalog.exception;

import java.io.Serial;

public class InvalidProductException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidProductException(String message) {
        super(message);
    }
}
