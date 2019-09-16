package com.faesp.ssi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faesp.ssi.model.Funcionario;

public interface Funcionarios extends JpaRepository<Funcionario, Long> {
		public List<Funcionario> findByNomeContaining(String nomeFuncionario);
}
