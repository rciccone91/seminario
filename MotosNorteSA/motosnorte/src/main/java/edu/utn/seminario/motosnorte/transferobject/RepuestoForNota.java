package edu.utn.seminario.motosnorte.transferobject;

import java.io.Serializable;

import edu.utn.seminario.motosnorte.domain.Repuesto;

@SuppressWarnings("serial")
public class RepuestoForNota implements Serializable{
	
	private Repuesto repuesto;
	private Integer cantidad;
	
	public RepuestoForNota(Repuesto repuesto, Integer cantidad) {
		super();
		this.repuesto = repuesto;
		this.cantidad = cantidad;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Repuesto getRepuesto() {
		return repuesto;
	}
	public void setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}
}
