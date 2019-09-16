package com.faesp.ssi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faesp.ssi.model.Reuniao;
import com.faesp.ssi.model.StatusReuniao;
import com.faesp.ssi.repository.Reunioes;
import com.faesp.ssi.repository.filter.ReuniaoFilter;

@Service
public class ReuniaoService {
	
	@Autowired
	private Reunioes reunioes;
	
	public void salvar(Reuniao reuniao) {
		reunioes.save(reuniao);
	}
	
	public void excluir(Long codigo) {
		reunioes.deleteById(codigo);
	}
	
//	public String realizar(Long codigo) {
//		Reuniao reuniao = reunioes.getOne(codigo);
//		reuniao.setStatus(StatusReuniao.REALIZADA);
//		reunioes.save(reuniao);
//				
//		return StatusReuniao.REALIZADA.getDescricao();
// 		
//		ambos os codigos funcionam tanto este com getOne tanto o findById
//	}
	
	public String realizar(Long codigo) {
		Optional<Reuniao> reuniao = reunioes.findById(codigo);
		reuniao.get().setStatus(StatusReuniao.REALIZADA);
		reunioes.save(reuniao.get());
				
		return StatusReuniao.REALIZADA.getDescricao();
	}
	
	public List<Reuniao> filtrar(ReuniaoFilter filtro) {
		String assunto= filtro.getAssuntoFiltro() == null ? "%" : filtro.getAssuntoFiltro();
		return reunioes.findByAssuntoContaining(assunto);
	}
}