package com.desafio.assembleia.pauta;

import java.util.Objects;

import com.desafio.assembleia.sessao.SessaoDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaDto {
    private final Long id;
    private final Long idSessao;

    public Pauta asEntity() {
        return Pauta.builder()
                .id(this.getId())
                .sessao(SessaoDto.builder().id(idSessao).build().asEntity())
                .build();
    }

    public static PautaDto fromEntity(Pauta pauta) {
        return PautaDto.builder()
                .id(pauta.getId())
                .idSessao(Objects.isNull(pauta.getSessao()) ? null : pauta.getSessao().getId())
                .build();
    }

}