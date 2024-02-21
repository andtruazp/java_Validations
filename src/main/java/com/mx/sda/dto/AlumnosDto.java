package com.mx.sda.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AlumnosDto {
	@NotBlank
	private String nombre;
	@NotBlank
	private String NumeroControl;
	@DecimalMin(value="0.01")
	private Float calificacion;
	@Min(value=1)
	private Integer grupo_Id;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumeroControl() {
		return NumeroControl;
	}
	public void setNumeroControl(String numeroControl) {
		NumeroControl = numeroControl;
	}
	public Float getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Float calificacion) {
		this.calificacion = calificacion;
	}
	public Integer getGrupo_Id() {
		return grupo_Id;
	}
	public void setGrupo_Id(Integer grupo_Id) {
		this.grupo_Id = grupo_Id;
	}

}
