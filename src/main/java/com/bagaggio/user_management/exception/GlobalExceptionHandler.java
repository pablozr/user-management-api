package com.bagaggio.user_management.exception;

import com.bagaggio.user_management.dto.apiResponse.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserJaExistenteException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleUserAlreadyExists(UserJaExistenteException ex){
            ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
