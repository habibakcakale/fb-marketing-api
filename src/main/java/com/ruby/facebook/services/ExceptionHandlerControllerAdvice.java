package com.ruby.facebook.services;

import com.facebook.ads.sdk.APIException;
import com.ruby.facebook.models.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var result = new Response<>(false);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().stream().filter(i -> i instanceof FieldError).forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        result.setErrors(errors);
        return result;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(APIException.class)
    public Response<Object> handleValidationExceptions(APIException ex) {
        logger.error("Exception occurred. ", ex);

        var result = new Response<Object>(false);
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        result.setErrors(errors);
        return result;
    }
}
