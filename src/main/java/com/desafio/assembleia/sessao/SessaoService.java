package com.desafio.assembleia.sessao;

public interface SessaoService {

    SessaoDto abrir(Long idPauta, Integer duracaoEmMinutos);

	SessaoDto buscarSessaoPorIdPauta(Long idPauta);

}
