package com.desafio.assembleia.voto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    @Query("SELECT COUNT(v) > 0 FROM Voto v WHERE v.pauta.id = :idPauta AND v.cpf = :cpf")
	boolean existsByIdPautaAndCpf(@Param("idPauta") Long idPauta, @Param("cpf") String cpf);

    @Query("SELECT v FROM Voto v WHERE v.pauta.id = :idPauta")
	List<Voto> findAllByIdPauta(@Param("idPauta") Long idPauta);

}