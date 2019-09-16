package com.faesp.ssi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faesp.ssi.model.Impressora;
import com.faesp.ssi.repository.Impressoras;
import com.faesp.ssi.repository.filter.ImpressoraFilter;

@Service	
public class ImpressoraService {
	
	@Autowired
	private Impressoras impressoras;
	
	public void salvar(Impressora impressora) {
		impressoras.save(impressora);
	}
	
	public void excluir(Long codigo) {
		impressoras.deleteById(codigo);
	}

	public List<Impressora> filtrar(ImpressoraFilter filtro) {
		String nomeImpressora = filtro.getImpressoraFiltro() == null ? "%" : filtro.getImpressoraFiltro();
		return impressoras.findByNomeImpressoraContaining(nomeImpressora);
	}
	
}
