package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames={"pedido_id", "moto_id"}),
		})
public class DetallePedidoMotos implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="detallepedidomotos_id")
	private Integer id;
	@OneToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	@OneToOne
	@JoinColumn(name="moto_id")
	private Moto moto;
	private Integer cantidad;
	
	public DetallePedidoMotos() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((moto == null) ? 0 : moto.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
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
		DetallePedidoMotos other = (DetallePedidoMotos) obj;
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
		if (moto == null) {
			if (other.moto != null)
				return false;
		} else if (!moto.equals(other.moto))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		return true;
	}
	
	
}
