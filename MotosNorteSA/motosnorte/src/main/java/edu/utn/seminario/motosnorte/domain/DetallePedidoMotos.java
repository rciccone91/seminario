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
}
