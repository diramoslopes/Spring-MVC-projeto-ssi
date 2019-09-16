package com.faesp.ssi.model;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Atendimento{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Solicitante é obrigatório")
	private String solicitante;
	
	@NotEmpty(message = "Motivo é obrigatório")
	private String motivo;
	
	@NotNull(message = "Data de vencimento é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;

//	@NotEmpty(message = "Horario é obrigatorio")
//	private String horario;
	
	Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
	int ano = calendar.get(Calendar.YEAR);
	int mes = calendar.get(Calendar.MONTH); // O mês vai de 0 a 11.
	int semana = calendar.get(Calendar.WEEK_OF_MONTH);
	int dia = calendar.get(Calendar.DAY_OF_MONTH);
	int hora = calendar.get(Calendar.HOUR_OF_DAY);
	int minuto = calendar.get(Calendar.MINUTE);
	int segundo = calendar.get(Calendar.SECOND);
	
	@NotEmpty(message = "Horario é obrigatorio")
	private String horario = "" + hora + ":" + "" + minuto + "";
	
	@Enumerated(EnumType.STRING)
	private StatusAtendimento status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public StatusAtendimento getStatus() {
		return status;
	}

	public void setStatus(StatusAtendimento status) {
		this.status = status;
	}
	
	public boolean isPendente() {
		return StatusAtendimento.PENDENTE.equals(this.status);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atendimento other = (Atendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}