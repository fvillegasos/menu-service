package com.fvillegasos.coffeeshop.menu_service.exception;

import org.springframework.http.HttpStatusCode;

public class CustomHttpException extends RuntimeException {

    private HttpStatusCode httpStatusCode;

    private CustomHttpException(String message, HttpStatusCode httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public static CustomHttpException of(CustomErrorTypeEnum customErrorTypeEnum) {
        return new CustomHttpException(customErrorTypeEnum.getMessage(), customErrorTypeEnum.getStatusCode());
    }
}
