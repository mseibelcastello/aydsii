package com.aydsii.tp2.model;

public record ApiResult<T>(int status, String message, T data) {

    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<>(200, "Operacion realizada con exito", data);
    }

    public static <T> ApiResult<T> error(int status, String message) {
        return new ApiResult<>(status, message, null);
    }

    public static <T> ApiResult<T> error(int status, String message, T data) {
        return new ApiResult<>(status, message, data);
    }

}
