package com.bagaggio.user_management.exception;

import com.bagaggio.user_management.dto.apiResponse.ApiResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserJaExistenteException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleUserAlreadyExists(UserJaExistenteException ex){
            log.error("Usuário já existente: ", ex);
            ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(),HttpStatus.CONFLICT);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleUsernameNotFound(UsernameNotFoundException ex) {
        log.error("Usuário não encontrado: ", ex);
        ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleEmailAlreadyExists(EmailJaCadastradoException ex) {
        log.error("Email já cadastrado: ", ex);
        ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleIllegalArgumentException(IllegalArgumentException ex){
        log.error("Argumento ilegal: ", ex);
        ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleExpiredJwtException(ExpiredJwtException ex){
        log.error("Token JWT expirado: ", ex);
        ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleAuthorizationDeniedException(AuthorizationDeniedException ex){
        log.error("Acesso negado: ", ex);
        ApiResponseDTO<Object> response = ApiResponseDTO.error(ex.getMessage(),HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleBadCredentialsException(org.springframework.security.authentication.BadCredentialsException ex){
        log.error("Credenciais inválidas: ", ex);
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
