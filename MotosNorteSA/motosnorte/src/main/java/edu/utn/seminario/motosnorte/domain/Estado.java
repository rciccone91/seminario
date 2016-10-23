package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


public class Estado implements Serializable{
	
	private String estado;
	private String descripcion;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
