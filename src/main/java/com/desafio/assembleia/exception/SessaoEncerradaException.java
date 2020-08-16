package com.desafio.assembleia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class SessaoEncerradaException extends RuntimeException {

    private static final long serialVersionUID = 6803731884029993739L;

    public SessaoEncerradaException(String message) {
        super(message);
    }

}
