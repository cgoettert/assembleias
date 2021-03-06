package com.desafio.assembleia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class JaExisteException extends RuntimeException {

    private static final long serialVersionUID = -8721314372147264911L;

    public JaExisteException(String message) {
        super(message);
    }

}
