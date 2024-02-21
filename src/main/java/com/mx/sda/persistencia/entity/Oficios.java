package com.mx.sda.persistencia.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Oficios {
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Integer cveOficio;
	private String asunto;
	private String dirigido;
	
	@Enumerated(EnumType.STRING)
	private TipoOficio tipoOficio;
	
	private LocalDate fechaRegistro;
	
	public static enum TipoOficio{
		RECIBIDO, ENVIADO
	}

	public Integer getCveOficio() {
		return cveOficio;
	}

	public void setCveOficio(Integer cveOficio) {
		this.cveOficio = cveOficio;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDirigido() {
		return dirigido;
	}

	public void setDirigido(String dirigido) {
		this.dirigido = dirigido;
	}

	public TipoOficio getTipoOficio() {
		return tipoOficio;
	}

	public void setTipoOficio(TipoOficio tipoOficio) {
		this.tipoOficio = tipoOficio;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
	

}
