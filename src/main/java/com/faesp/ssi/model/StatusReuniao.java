package com.faesp.ssi.model;

public enum StatusReuniao {

	AGENDADA("Agendada"),
	REALIZADA("Realizada");
	
	private String descricao;
	
	StatusReuniao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
