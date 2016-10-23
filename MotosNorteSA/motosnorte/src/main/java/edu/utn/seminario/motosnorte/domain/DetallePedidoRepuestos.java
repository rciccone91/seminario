package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames={"pedido_id", "repuesto_id"}),
		})
public class DetallePedidoRepuestos implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="detallepedidorepuestos_id")
	private Integer id;
	@OneToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	@OneToOne
	@JoinColumn(name="repuesto_id")
	private Repuesto repuesto;
	private Integer cantidad;
	
	public DetallePedidoRepuestos() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pedido getNotaPedido() {
		return pedido;
	}

	public void setNotaPedido(Pedido pedido) {
		this.pedido = pedido;
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
}
