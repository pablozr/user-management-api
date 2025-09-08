package com.bagaggio.user_management.dto.apiResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResponseDTO<T> {
    private final int status;
    private final String message;
    private final T data;

    private ApiResponseDTO(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponseDTO<T> sucess(T data, String message){
        return new ApiResponseDTO<>(200, message, data);
    }

    public static <T> ApiResponseDTO<T> sucess(String message){
        return new ApiResponseDTO<>(200, message, null);
    }

    public static <T> ApiResponseDTO<T> created(T data, String message){
        return new ApiResponseDTO<>(201, message, data);
    }

    public static <T> ApiResponseDTO<T> error(String message){
        return new ApiResponseDTO<>(200, message, null);
    }
}
