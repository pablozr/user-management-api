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
            ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(),HttpStatus.CONFLICT);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleGenericException(Exception ex){
        ApiResponseDTO<Object> response = ApiResponseDTO.error("Ocorreu um erro inesperado.",HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleEmailAlreadyExists(EmailJaCadastradoException ex) {
        ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
