package com.mx.sda.dto;

import java.time.LocalDate;

import com.mx.sda.persistencia.entity.Oficios.TipoOficio;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class OficiosDto {
	@Size(min = 10, max = 60)
	private String asunto;
	@Size(min = 10, max = 80)
	private String dirigido;
	@PastOrPresent
	private LocalDate fechaRegistro;

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
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	

}
