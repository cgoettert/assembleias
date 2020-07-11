package com.desafio.assembleia.voto;

import java.util.Objects;

import com.desafio.assembleia.pauta.PautaDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotoDto {
    private final Long id;
    private final String cpf;
    private final Boolean voto;
    private final Long idPauta;

    public Voto asEntity() {
        return Voto.builder()
                .id(this.getId())
                .pauta(PautaDto.builder().id(idPauta).build().asEntity())
                .cpf(this.cpf)
                .voto(this.voto)
                .build();
    }

    public static VotoDto fromEntity(Voto voto) {
        return VotoDto.builder()
                .id(voto.getId())
                .idPauta(Objects.isNull(voto.getPauta()) ? null : voto.getPauta().getId())
                .cpf(voto.getCpf())
                .voto(voto.getVoto())
                .build();
    }

}