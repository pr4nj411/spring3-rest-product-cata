package com.example.catalog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.example.catalog.exception.DuplicateSkuException;
import com.example.catalog.exception.InvalidProductException;
import com.example.catalog.exception.ProductNotFoundException;
import com.example.catalog.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFound(ProductNotFoundException ex) {
        return new ErrorResponse("PRODUCT_NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(DuplicateSkuException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleDuplicate(DuplicateSkuException ex) {
        return new ErrorResponse("DUPLICATE_SKU", ex.getMessage());
    }

    @ExceptionHandler(InvalidProductException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalid(InvalidProductException ex) {
        return new ErrorResponse("INVALID_PRODUCT", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleUnexpected(Exception ex) {
        return new ErrorResponse("INTERNAL_ERROR", "Unexpected server error.");
    }
}
