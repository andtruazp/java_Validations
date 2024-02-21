package com.mx.sda.persistencia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class Alumnos {
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String numeroControl;
	private Float calificacion;
	
	@Enumerated(EnumType.STRING)
	private AlumnoEstatus estatus;
	
	
	public static enum AlumnoEstatus{
		HABILITADO, DESHABILITADO, REGULAR, BAJA_TEMPORAL
	}
	
	@ManyToOne
	@JoinColumn(name = "grupo_Id")
	private Grupo grupo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroControl() {
		return numeroControl;
	}

	public void setNumeroControl(String numeroControl) {
		this.numeroControl = numeroControl;
	}

	public Float getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Float calificacion) {
		this.calificacion = calificacion;
	}

	public AlumnoEstatus getEstatus() {
		return estatus;
	}

	public void setEstatus(AlumnoEstatus estatus) {
		this.estatus = estatus;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
		
	}
	
	
}
