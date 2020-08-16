package com.desafio.assembleia.pauta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PautaController implements PautaApi {

    private final PautaService service;

    @Autowired
    PautaController(final PautaService service) {
        this.service = service;
    }

    ResponseEntity<Void> cadastrar() {
        service.cadastrar();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}