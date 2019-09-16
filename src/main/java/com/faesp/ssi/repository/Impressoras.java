package com.faesp.ssi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faesp.ssi.model.Impressora;

public interface Impressoras extends JpaRepository<Impressora, Long>{
	public List<Impressora> findByNomeImpressoraContaining(String nomeImpressora);
	


}
