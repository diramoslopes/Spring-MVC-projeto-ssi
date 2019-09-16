package com.faesp.ssi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faesp.ssi.model.Atendimento;
import com.faesp.ssi.model.StatusAtendimento;
import com.faesp.ssi.repository.Atendimentos;
import com.faesp.ssi.repository.filter.AtendimentoFilter;

@Service
public class AtendimentoService {
	
	@Autowired
	private Atendimentos atendimentos;
	
	public void salvar(Atendimento atendimento) {
		atendimentos.save(atendimento);
	}
	
	public void excluir(Long codigo) {
		atendimentos.deleteById(codigo);
	}

//	public String resolver(Long codigo) {
//		Atendimento atendimento = atendimentos.getOne(codigo) ;
//		atendimento.setStatus(StatusAtendimento.RESOLVIDO);
//		atendimentos.save(atendimento);
//		
//		return StatusAtendimento.RESOLVIDO.getDescricao();
//	}
	
	public String resolver(Long codigo) {
		Optional<Atendimento> atendimento = atendimentos.findById(codigo);
		atendimento.get().setStatus(StatusAtendimento.RESOLVIDO);
		atendimentos.save(atendimento.get());
		
		return StatusAtendimento.RESOLVIDO.getDescricao();
	}

	public List<Atendimento> filtrar(AtendimentoFilter filtro) {
		String solicitante = filtro.getSolicitanteFiltro() == null ? "%" : filtro.getSolicitanteFiltro();
		return atendimentos.findBySolicitanteContaining(solicitante);
	}
}