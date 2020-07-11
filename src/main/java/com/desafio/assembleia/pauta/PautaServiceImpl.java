package com.desafio.assembleia.pauta;

import java.util.Optional;

import com.desafio.assembleia.exception.NaoEncontradoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;

    @Autowired
    PautaServiceImpl(final PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public PautaDto cadastrar() {
        return PautaDto.fromEntity(pautaRepository.save(new Pauta()));
    }

    @Override
    public PautaDto buscarPorId(Long id) {
        Optional<Pauta> pautaOptional = pautaRepository.findById(id);
        if (!pautaOptional.isPresent()) {
            throw new NaoEncontradoException(String.format("Pauta %s não encontrada", id));
        }
        return PautaDto.fromEntity(pautaOptional.get());
    }


}