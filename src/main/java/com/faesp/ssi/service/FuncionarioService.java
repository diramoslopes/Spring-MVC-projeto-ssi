package com.faesp.ssi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faesp.ssi.model.Funcionario;
import com.faesp.ssi.repository.Funcionarios;
import com.faesp.ssi.repository.filter.FuncionarioFilter;

@Service
public class FuncionarioService {
	
	@Autowired
	private Funcionarios funcionarios;
	
	public void salvar(Funcionario funcionario) {
		funcionarios.save(funcionario);
	}
	
	public void excluir(Long codigo) {
		funcionarios.deleteById(codigo);
	}

	public List<Funcionario> filtrar(FuncionarioFilter filtro) {
		String nomeFuncionario = filtro.getNomeFiltro() == null ? "%" : filtro.getNomeFiltro();
		return funcionarios.findByNomeContaining(nomeFuncionario);
		
	}
}
