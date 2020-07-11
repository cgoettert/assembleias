package com.desafio.assembleia.pauta;

public interface PautaService {

    PautaDto cadastrar();

    PautaDto buscarPorId(Long id);

}
