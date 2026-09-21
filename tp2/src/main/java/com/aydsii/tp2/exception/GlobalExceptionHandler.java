package com.aydsii.tp2.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.aydsii.tp2.model.ApiResult;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Pattern POSICION_CAMPO = Pattern.compile("\\[(\\d+)\\]\\.(.+)$");

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult<List<ErrorValidacion>>> handleValidation(MethodArgumentNotValidException ex) {
        List<ErrorValidacion> errores = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError fieldError) {
                String campo = fieldError.getField();
                Integer posicion = null;
                Matcher m = POSICION_CAMPO.matcher(campo);
                if (m.find()) {
                    posicion = Integer.parseInt(m.group(1));
                    campo = m.group(2);
                }
                errores.add(new ErrorValidacion(posicion, campo, fieldError.getDefaultMessage()));
            } else {
                errores.add(new ErrorValidacion(null, error.getObjectName(), error.getDefaultMessage()));
            }
        }
        return ResponseEntity.badRequest().body(ApiResult.error(400, "Error de validacion", errores));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ApiResult<List<ErrorValidacion>>> handleMethodValidation(
            HandlerMethodValidationException ex) {
        List<ErrorValidacion> errores = new ArrayList<>();
        for (var resultado : ex.getParameterValidationResults()) {
            String campo = resultado.getMethodParameter().getParameterName();
            Integer posicion = resultado.getContainerIndex();
            for (MessageSourceResolvable error : resultado.getResolvableErrors()) {
                errores.add(new ErrorValidacion(posicion, campo, error.getDefaultMessage()));
            }
        }
        return ResponseEntity.badRequest().body(ApiResult.error(400, "Error de validacion", errores));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResult<Void>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ApiResult.error(400, ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResult<Void>> handleJsonInvalido(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest()
                .body(ApiResult.error(400, "El cuerpo de la peticion no es un JSON valido o falta el body"));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResult<Void>> handleResponseStatus(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(ApiResult.error(ex.getStatusCode().value(), ex.getReason()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResult<Void>> handleRecursoNoEncontrado(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResult.error(404, "Recurso no encontrado"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<Void>> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResult.error(500, "Error interno del servidor"));
    }
}