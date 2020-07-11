package com.desafio.assembleia.voto;

public interface VotoService {

    void votar(VotoDto votoDto);

    ResultadoVotacaoDto buscarResultadoVotacao(Long idPauta);

}
