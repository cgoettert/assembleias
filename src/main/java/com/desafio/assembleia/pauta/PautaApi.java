package com.desafio.assembleia.pauta;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.*;

@Api(value = "pauta", description = "the pauta API")
@RequestMapping(value = "/api/v1")
public interface PautaApi {

    @ApiOperation(value = "Cadastrar uma pauta para votação", nickname = "cadastrarPauta", notes = "Insere uma nova pauta ao sistema", tags={ "developers" })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "item created"),
        @ApiResponse(code = 400, message = "invalid input, object invalid") })
    @RequestMapping(value = "/pauta",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> cadastrarPauta() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}