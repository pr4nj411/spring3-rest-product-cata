package com.example.catalog.exception;

import java.io.Serial;

public class ProductNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " was not found.");
    }
}
