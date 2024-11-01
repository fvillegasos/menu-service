package com.fvillegasos.coffeeshop.menu_service.exception;


import java.time.OffsetDateTime;

public class CustomHttpError {

    private final OffsetDateTime dateOfError;
    private final Integer status;
    private final String message;

    public CustomHttpError(Integer status, String message) {
        this.dateOfError = OffsetDateTime.now();
        this.status = status;
        this.message = message;
    }

    public OffsetDateTime getDateOfError() {
        return dateOfError;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
