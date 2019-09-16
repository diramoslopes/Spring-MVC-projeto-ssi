package com.faesp.ssi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faesp.ssi.model.Atendimento;

public interface Atendimentos extends JpaRepository<Atendimento, Long> {
	
	public List<Atendimento> findBySolicitanteContaining(String solicitante);
	
}
