package com.sales.crm.exceptions;

public class ResourceNotValidException extends ResourceNotFoundException {
    public ResourceNotValidException(String message) {
        super(message);
    }
}
