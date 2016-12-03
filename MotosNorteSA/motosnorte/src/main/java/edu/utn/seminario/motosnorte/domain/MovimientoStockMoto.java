package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class MovimientoStockMoto implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="movimientostockmoto_id")
	private Integer id;
	@OneToOne
	@JoinColumn(name="moto_id",nullable=false)
	private Moto moto;
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
	@Column(name="remito",nullable=false)
	private String remito;
	@Column(name="motivo_salida")
	private Integer motivoSalida;
	
	public MovimientoStockMoto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getRemito() {
		return remito;
	}

	public void setRemito(String remito) {
		this.remito = remito;
	}

	public Integer getMotivoSalida() {
		return motivoSalida;
	}

	public void setMotivoSalida(Integer motivoSalida) {
		this.motivoSalida = motivoSalida;
	}

	public int getCantidad() {
		return cantidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motivoSalida == null) ? 0 : motivoSalida.hashCode());
		result = prime * result + ((moto == null) ? 0 : moto.hashCode());
		result = prime * result + ((remito == null) ? 0 : remito.hashCode());
		result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimientoStockMoto other = (MovimientoStockMoto) obj;
		if (cantidad != other.cantidad)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motivoSalida == null) {
			if (other.motivoSalida != null)
				return false;
		} else if (!motivoSalida.equals(other.motivoSalida))
			return false;
		if (moto == null) {
			if (other.moto != null)
				return false;
		} else if (!moto.equals(other.moto))
			return false;
		if (remito == null) {
			if (other.remito != null)
				return false;
		} else if (!remito.equals(other.remito))
			return false;
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
