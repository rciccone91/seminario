package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"sucursal_id", "repuesto_id"})})
public class StockRepuestos implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@OneToOne
	@JoinColumn(name="repuesto_id",nullable=false)
	private Repuesto repuesto;
	@OneToOne
	@JoinColumn(name="sucursal_id",nullable=false)
	private Sucursal sucursal;
	@Column(nullable=false)
	private Integer cantidad;
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((repuesto == null) ? 0 : repuesto.hashCode());
		result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
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
		StockRepuestos other = (StockRepuestos) obj;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (repuesto == null) {
			if (other.repuesto != null)
				return false;
		} else if (!repuesto.equals(other.repuesto))
			return false;
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		return true;
	}
	
	
	
}
