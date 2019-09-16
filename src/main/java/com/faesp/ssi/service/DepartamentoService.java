package com.faesp.ssi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faesp.ssi.model.Departamento;
import com.faesp.ssi.repository.Departamentos;
import com.faesp.ssi.repository.filter.DepartamentoFilter;

@Service
public class DepartamentoService {
	
	@Autowired
	Departamentos departamentos;
	
	public void salvar(Departamento departamento) {
		departamentos.save(departamento);
	}
	
	public void excluir(Long codigo) {
		departamentos.deleteById(codigo);
	}

	public List<Departamento> filtrar(DepartamentoFilter filtro) {
		String nomeDepartamento = filtro.getDepartamentoFiltro() == null ? "%" : filtro.getDepartamentoFiltro();
		return departamentos.findByNomeDepartamentoContaining(nomeDepartamento);
	}
}
