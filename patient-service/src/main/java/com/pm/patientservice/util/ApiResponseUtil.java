package com.pm.patientservice.util;

import com.pm.patientservice.dto.ApiResponse;

public class ApiResponseUtil {

    public static <T> ApiResponse<T> success(T data) {
        return success("Operation complete", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }
}
