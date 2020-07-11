package com.desafio.assembleia.voto;

import com.desafio.assembleia.pauta.PautaDto;
import com.desafio.assembleia.sessao.SessaoDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoVotacaoDto {
    private final PautaDto pauta;
    private final SessaoDto sessao;
    private final Long sim;
    private final Long nao;
}
