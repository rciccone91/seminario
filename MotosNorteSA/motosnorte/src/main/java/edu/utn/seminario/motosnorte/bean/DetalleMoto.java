package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;

import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.domain.Sucursal;

public class DetalleMoto implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private Integer cantidad;
    private Moto moto;
    private Sucursal sucursal;

	public DetalleMoto(Integer cantidad, Moto moto, Sucursal sucursal) {
		super();
		this.cantidad = cantidad;
		this.moto = moto;
		this.sucursal = sucursal;
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

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
}