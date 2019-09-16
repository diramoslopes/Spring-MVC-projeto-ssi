package com.faesp.ssi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faesp.ssi.model.Reuniao;

public interface Reunioes extends JpaRepository<Reuniao, Long>{
	
	public List<Reuniao> findByAssuntoContaining(String assunto);

}
