package com.faesp.ssi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity	
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome é obrigatório")
	private String nome;
	
	@NotEmpty (message = "Email é obrigatório")
	private String email;
	
	@NotEmpty(message = "Ramal é obrigatório")
	@Size(max=4, message="Tamanho maximo de ramal é 4")
	@Size(min=4, message="Tamanho minimo de ramal é 4")
	private String ramal;
	
	@NotEmpty(message = "Sistema Operacional é obrigatório")
	private String sistemaOperacional;
	
	@NotEmpty(message = "Versão é obrigatório")
	private String versao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(String sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}
	
	
}
