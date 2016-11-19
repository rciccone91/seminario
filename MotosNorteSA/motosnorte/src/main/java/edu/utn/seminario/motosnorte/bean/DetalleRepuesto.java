package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;

import edu.utn.seminario.motosnorte.domain.Repuesto;

public class DetalleRepuesto implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private Integer cantidad;
    private Repuesto repuesto;
    
	public DetalleRepuesto(Integer cantidad, Repuesto repuesto) {
		super();
		this.cantidad = cantidad;
		this.repuesto = repuesto;
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