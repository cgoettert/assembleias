package com.desafio.assembleia.sessao;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import com.desafio.assembleia.exception.JaExisteException;
import com.desafio.assembleia.exception.NaoEncontradoException;
import com.desafio.assembleia.pauta.PautaDto;
import com.desafio.assembleia.pauta.PautaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessaoServiceImpl implements SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaService pautaService;

    @Autowired
    SessaoServiceImpl(final SessaoRepository sessaoRepository, final PautaService pautaService) {
        this.sessaoRepository = sessaoRepository;
        this.pautaService = pautaService;
    }

    @Override
    @Transactional
    public SessaoDto abrir(Long idPauta, Integer duracaoEmMinutos) {
        PautaDto pautaDto = pautaService.buscarPorId(idPauta);
        if (Objects.nonNull(pautaDto.getIdSessao())) {
            throw new JaExisteException(String.format("Sessão para a pauta %s já existe", idPauta));
        }

        Sessao novaSessao = Sessao.builder()
                    .encerramento(calcularEncerramentoSessao(duracaoEmMinutos))
                    .build();
        
        return SessaoDto.fromEntity(sessaoRepository.save(novaSessao));
    }

    private LocalDateTime calcularEncerramentoSessao(Integer duracaoEmMinutos) {
        if (Objects.nonNull(duracaoEmMinutos) && duracaoEmMinutos > 0) {
            return LocalDateTime.now().plusMinutes(duracaoEmMinutos);
        } else {
            return LocalDateTime.now().plusMinutes(1);
        }

    }

    @Override
    public SessaoDto buscarSessaoPorIdPauta(Long idPauta) {
        Optional<Sessao> sessaoOptional = sessaoRepository.findByIdPauta(idPauta);
        if (!sessaoOptional.isPresent()) {
            throw new NaoEncontradoException(String.format("Sessão não encontrada para pauta %s.", idPauta));
        }
        return SessaoDto.fromEntity(sessaoOptional.get());
    }

}