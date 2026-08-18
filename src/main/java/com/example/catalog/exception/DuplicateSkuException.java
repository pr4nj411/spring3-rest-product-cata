package com.example.catalog.exception;

public class DuplicateSkuException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateSkuException(String sku) {
        super("Product SKU already exists: " + sku);
    }
}
