package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class MovimientoStockRepuesto implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="movimientostockrepuesto_id")
	private Integer id;
	@OneToOne
	@JoinColumn(name="repuesto_id",nullable=false)
	private Repuesto repuesto;
	@Column(nullable=false)
	private int cantidad;
	@OneToOne
	@JoinColumn(name="usuario_id",nullable=false)
	private Usuario usuario;
	@OneToOne
	@JoinColumn(name="sucursal_id",nullable=false)
	private Sucursal sucursal;
	@Column(nullable=false)
	private Date fecha;
	
	public MovimientoStockRepuesto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Repuesto getRepuesto() {
		return repuesto;
	}

	public void setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
}
