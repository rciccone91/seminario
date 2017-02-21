package edu.utn.frgp.laboratoriov.domain;

import java.util.Calendar;
import java.util.Date;

public class Reservas {

	private Integer id;
	private Usuario usuario;
	private Date fechaReserva;
	private Date vencimiento;
	private Propiedad propiedad;
	private Boolean vencida;
	
	public Reservas(Integer id, Usuario usuario, Date fechaReserva, Date vencimiento, Propiedad propiedad, Boolean vencida) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.fechaReserva = fechaReserva;
		this.vencimiento = vencimiento;
		this.propiedad = propiedad;
		this.vencida = vencida;
	}
	public Reservas() {
		super();
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
	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public Date getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(Date vencimiento) {
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
