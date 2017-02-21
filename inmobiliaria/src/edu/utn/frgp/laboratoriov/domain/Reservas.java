package edu.utn.frgp.laboratoriov.domain;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Reservas {

	private Integer id;
	private Usuario usuario;
	private Timestamp fechaReserva;
	private Timestamp vencimiento;
	private Propiedad propiedad;
	private Boolean vencida;
	
	public Reservas(Integer id, Usuario usuario, Timestamp fechaReserva, Timestamp vencimiento, Propiedad propiedad, Boolean vencida) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.fechaReserva = fechaReserva;
		this.vencimiento = vencimiento;
		this.propiedad = propiedad;
		this.vencida = vencida;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Timestamp getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Timestamp fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public Timestamp getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(Timestamp vencimiento) {
		this.vencimiento = vencimiento;
	}
	public Propiedad getPropiedad() {
		return propiedad;
	}
	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}
	public Boolean getVencida() {
		return vencida;
	}
	public void setVencida(Boolean vencida) {
		this.vencida = vencida;
	}
	
	
}
