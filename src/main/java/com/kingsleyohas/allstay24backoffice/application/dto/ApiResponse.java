package com.kingsleyohas.allstay24backoffice.application.dto;

public record ApiResponse<T>(T data, String message, int statusCode) {
    public static <T> ApiResponse<T> success(T data, String message, int statusCode) {
        return new ApiResponse<>(data, message, statusCode);
    }

    public static <T> ApiResponse<T> error(String message, int statusCode) {
        return new ApiResponse<>(null, message, statusCode);
    }
}
