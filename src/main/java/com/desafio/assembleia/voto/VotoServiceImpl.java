package com.desafio.assembleia.voto;

import java.util.List;

import com.desafio.assembleia.exception.JaExisteException;
import com.desafio.assembleia.exception.SessaoEncerradaException;
import com.desafio.assembleia.pauta.PautaDto;
import com.desafio.assembleia.pauta.PautaService;
import com.desafio.assembleia.sessao.SessaoDto;
import com.desafio.assembleia.sessao.SessaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final PautaService pautaService;
    private final SessaoService sessaoService;

    @Autowired
    VotoServiceImpl(final VotoRepository votoRepository, final PautaService pautaService, final SessaoService sessaoService) {
        this.votoRepository = votoRepository;
        this.pautaService = pautaService;
        this.sessaoService = sessaoService;
    }

    @Transactional
    @Override
    public void votar(VotoDto votoDto) {
        SessaoDto sessaoDto = sessaoService.buscarSessaoPorIdPauta(votoDto.getIdPauta());
        if (sessaoDto.estaEncerrada()) {
            throw new SessaoEncerradaException(String.format("Sessão para a pauta %s está encerrada.", votoDto.getIdPauta()));
        }

        if (votoRepository.existsByIdPautaAndCpf(votoDto.getIdPauta(), votoDto.getCpf())) {
            throw new JaExisteException(String.format("Membro %s já votou na pauta %s.", votoDto.getCpf(), votoDto.getIdPauta()));
        }
        
        votoRepository.save(votoDto.asEntity());
    }

    @Override
    public ResultadoVotacaoDto buscarResultadoVotacao(Long idPauta) {
        PautaDto pautaDto = pautaService.buscarPorId(idPauta);
        SessaoDto sessaoDto = sessaoService.buscarSessaoPorIdPauta(pautaDto.getId());
        List<Voto> votos = votoRepository.findAllByIdPauta(pautaDto.getId());

        return ResultadoVotacaoDto.builder()
                    .pauta(pautaDto)
                    .sessao(sessaoDto)
                    .sim(votos.stream().filter(voto -> Boolean.TRUE.equals(voto.getVoto())).count())
                    .nao(votos.stream().filter(voto -> Boolean.FALSE.equals(voto.getVoto())).count())
                    .build();
    }

}