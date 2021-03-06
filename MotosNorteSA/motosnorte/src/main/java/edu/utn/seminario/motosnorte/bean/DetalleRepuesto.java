package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;

import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.domain.Sucursal;

public class DetalleRepuesto implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private Integer cantidad;
    private Repuesto repuesto;
    private Sucursal sucursal;
    
	public DetalleRepuesto(Integer cantidad, Repuesto repuesto, Sucursal sucursal) {
		super();
		this.cantidad = cantidad;
		this.repuesto = repuesto;
		this.sucursal = sucursal;
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

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}    
}