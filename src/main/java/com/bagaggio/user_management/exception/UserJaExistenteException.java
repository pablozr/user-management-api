package com.bagaggio.user_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserJaExistenteException extends RuntimeException {
    public UserJaExistenteException(String message) {
        super(message);
    }
}
