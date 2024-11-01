package com.fvillegasos.coffeeshop.menu_service.web;

import com.fvillegasos.coffeeshop.menu_service.exception.CustomErrorTypeEnum;
import com.fvillegasos.coffeeshop.menu_service.exception.CustomHttpError;
import com.fvillegasos.coffeeshop.menu_service.exception.CustomHttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomHttpException.class)
    public ResponseEntity<CustomHttpError> handleCustomException(CustomHttpException customHttpException) {
        return getResponseEntityError(customHttpException);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomHttpError> handleException() {
        var genericInternalServerError = CustomHttpException.of(CustomErrorTypeEnum.GENERIC_INTERNAL_SERVER_ERROR);
        return getResponseEntityError(genericInternalServerError);
    }

    private ResponseEntity<CustomHttpError> getResponseEntityError(CustomHttpException customHttpException) {
        return ResponseEntity.status(customHttpException.getHttpStatusCode()).body(buildHttpError(customHttpException));
    }

    private CustomHttpError buildHttpError(CustomHttpException customHttpException) {
        return new CustomHttpError(customHttpException.getHttpStatusCode().value(), customHttpException.getMessage());
    }
}
