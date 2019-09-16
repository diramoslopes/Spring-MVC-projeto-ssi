package com.faesp.ssi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Impressora {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome da Impressora é obrigatório")
	private String nomeImpressora;
	
	@NotEmpty(message = "Marca é obrigatório")
	private String marca;
	
//	/* @NotEmpty(message = "IP é obrigatório") */
	@Size(max = 15, message = "Tamanho maximo do IP é 15 caracteres")
	@Size(min = 1, message = "Tamanho minimo do IP é 11 caracteres")
	private String ip;
	
	@NotEmpty(message = "Andar é obrigatório")
	@Size(max=13, message = "Tamanho max é 13 caracteres")
	private String andar;
	
	@NotEmpty(message = "Patrimônio é obrigatório")
	@Size(max=10, message="Tamanho máximo é 10 caracteres")
	private String patrimonio;
	
	@NotNull(message = "Código Maquina é obrigatório")
	private Integer codigoMaquina;
	
	@OneToOne
	Funcionario funcionario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeImpressora() {
		return nomeImpressora;
	}
	
	public void setNomeImpressora(String nomeImpressora) {
		this.nomeImpressora = nomeImpressora;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAndar() {
		return andar;
	}

	public void setAndar(String andar) {
		this.andar = andar;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public Integer getCodigoMaquina() {
		return codigoMaquina;
	}
	
	public void setCodigoMaquina(Integer codigoMaquina) {
		this.codigoMaquina = codigoMaquina;
	}

}