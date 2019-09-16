package com.faesp.ssi.model;

public enum StatusAtendimento {
	
	PENDENTE("Pendente"),
	RESOLVIDO("Resolvido");
	
	private String descricao;
	
	StatusAtendimento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
