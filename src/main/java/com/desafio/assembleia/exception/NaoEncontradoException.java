package com.desafio.assembleia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = -8854753308232319967L;

    public NaoEncontradoException(String message) {
        super(message);
    }

}
