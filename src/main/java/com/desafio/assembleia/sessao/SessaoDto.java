package com.desafio.assembleia.sessao;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessaoDto {
    private final Long id;
    private final LocalDateTime encerramento;
    private final Boolean aberta;

    public Sessao asEntity() {
        return Sessao.builder()
                .id(this.getId())
                .encerramento(this.encerramento)
                .aberta(this.aberta)
                .build();
    }

    public static SessaoDto fromEntity(Sessao sessao) {
        return SessaoDto.builder()
                .id(sessao.getId())
                .encerramento(sessao.getEncerramento())
                .aberta(sessao.getAberta())
                .build();
    }

    public boolean estaEncerrada() {
        return Boolean.FALSE.equals(this.aberta);
    }

}