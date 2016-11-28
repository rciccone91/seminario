package edu.utn.seminario.motosnorte.transferobject;

import edu.utn.seminario.motosnorte.domain.Moto;

public class MotosMasVendidas {

	private Moto moto;
	private Integer cantidad;
	private String fechas;
		
	public MotosMasVendidas(Moto moto, Integer cantidad, String fechas) {
		super();
		this.moto = moto;
		this.cantidad = cantidad;
		this.fechas = fechas;
	}
	public Moto getMoto() {
		return moto;
	}
	public void setMoto(Moto moto) {
		this.moto = moto;
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
