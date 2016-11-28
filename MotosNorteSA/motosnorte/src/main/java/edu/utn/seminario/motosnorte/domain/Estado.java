package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import edu.utn.seminario.motosnorte.helper.Constants;


public class Estado implements Serializable{
	
	private Integer id;
	private String estado;

	
	public Estado(Integer id, String estado) {
		super();
		this.id = id;
		this.estado = estado;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public static List<Estado> getEstados(){
		
		List<Estado> estados = new ArrayList<>();
		estados.add(new Estado(Constants.ESTADO_PEND_FACT,Constants.getEstadoDescription(Constants.ESTADO_PEND_FACT)));
		estados.add(new Estado(Constants.ESTADO_FACTURADO,Constants.getEstadoDescription(Constants.ESTADO_FACTURADO)));
		estados.add(new Estado(Constants.ESTADO_CANCELADO,Constants.getEstadoDescription(Constants.ESTADO_CANCELADO)));
		estados.add(new Estado(Constants.ESTADO_REINTEGRADO,Constants.getEstadoDescription(Constants.ESTADO_REINTEGRADO)));
		return estados;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getEstado();
	}
	
}
