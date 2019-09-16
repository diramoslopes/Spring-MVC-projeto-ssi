package com.faesp.ssi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faesp.ssi.model.Departamento;

public interface Departamentos extends JpaRepository<Departamento, Long>{
	
	public List<Departamento> findByNomeDepartamentoContaining(String nomeDepartamento);
}
