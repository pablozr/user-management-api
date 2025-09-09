package com.bagaggio.user_management.exception;

import com.bagaggio.user_management.dto.apiResponse.ApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserJaExistenteException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleUserAlreadyExists(UserJaExistenteException ex){
            ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(),HttpStatus.CONFLICT);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleUsernameNotFound(UsernameNotFoundException ex) {
        ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleEmailAlreadyExists(EmailJaCadastradoException ex) {
        ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponseDTO<Object>> handleIllegalArgumentException(IllegalArgumentException ex){
        ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponseDTO<Object>> handleAuthorizationDeniedException(AuthorizationDeniedException ex){
        ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(),HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponseDTO<Object>> handleBadCredentialsException(org.springframework.security.authentication.BadCredentialsException ex){
        ApiResponseDTO<Object> response = ApiResponseDTO.error("Credenciais inválidas.",HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleGenericException(Exception ex){
        log.error("Erro inesperado: ", ex);
        ApiResponseDTO<Object> response = ApiResponseDTO.error("Ocorreu um erro inesperado.",HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
