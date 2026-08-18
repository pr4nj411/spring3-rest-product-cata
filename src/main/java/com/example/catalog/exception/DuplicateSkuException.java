package com.example.catalog.exception;

import java.io.Serial;

public class DuplicateSkuException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DuplicateSkuException(String sku) {
        super("Product SKU already exists: " + sku);
    }
}
