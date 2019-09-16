package com.faesp.ssi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Departamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Departamento é obrigatório")
	private String nomeDepartamento;
	
	@NotEmpty
	private String entidade;
	
	@NotEmpty(message = "Chefia é obrigatório")
	private String chefia;
	
	@NotEmpty(message = "Andar é obrigatório")
	private String andar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNomeDepartamento() {
		return nomeDepartamento;
	}
	
	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public String getChefia() {
		return chefia;
	}

	public void setChefia(String chefia) {
		this.chefia = chefia;
	}

	public String getAndar() {
		return andar;
	}

	public void setAndar(String andar) {
		this.andar = andar;
	}
	
	
	
}
