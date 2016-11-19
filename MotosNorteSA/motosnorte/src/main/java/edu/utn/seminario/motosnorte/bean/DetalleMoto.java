package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;

import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.Repuesto;

public class DetalleMoto implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private Integer cantidad;
    private Moto moto;

	public DetalleMoto(Integer cantidad, Moto moto) {
		super();
		this.cantidad = cantidad;
		this.moto = moto;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Moto getMoto() {
		return moto;
	}
	
	public void setMoto(Moto moto) {
		this.moto = moto;
	}
}