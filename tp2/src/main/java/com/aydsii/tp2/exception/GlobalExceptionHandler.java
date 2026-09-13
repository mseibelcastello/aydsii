package com.aydsii.tp2.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        for (var error : ex.getBindingResult().getAllErrors()) {

            if (error instanceof FieldError fieldError) {

                errores.put(
                        fieldError.getObjectName()
                                + " - "
                                + fieldError.getField(),
                        fieldError.getDefaultMessage()
                );

            } else {

                errores.put(
                        "lista",
                        error.getDefaultMessage()
                );
            }
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errores);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleMethodValidation(
            HandlerMethodValidationException ex) {

        Map<String, String> errores = new HashMap<>();

        for (var resultado : ex.getParameterValidationResults()) {

            String parametro = resultado.getMethodParameter().getParameterName();
            if (resultado.getContainerIndex() != null) {
                parametro = parametro + "[" + resultado.getContainerIndex() + "]";
            }

            for (MessageSourceResolvable error : resultado.getResolvableErrors()) {
                errores.put(parametro, error.getDefaultMessage());
            }
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errores);
    }
}