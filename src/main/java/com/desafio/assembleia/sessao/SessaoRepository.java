package com.desafio.assembleia.sessao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    @Query("SELECT p.sessao FROM Pauta p WHERE p.id = :idPauta")
	Optional<Sessao> findByIdPauta(@Param("idPauta") Long idPauta);

}