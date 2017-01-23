package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;

public class RepuestosMasVendidos implements Serializable{

	private Repuesto repuesto;
	private Integer cantidad;
	private String fechas;

	
	public RepuestosMasVendidos(Repuesto repuesto, Integer cantidad, String fechas) {
		super();
		this.repuesto = repuesto;
		this.cantidad = cantidad;
		this.fechas = fechas;
	}
	
	public Repuesto getRepuesto() {
		return repuesto;
	}
	public void setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getFechas() {
		return fechas;
	}
	public void setFechas(String fechas) {
		this.fechas = fechas;
	}
	
}
