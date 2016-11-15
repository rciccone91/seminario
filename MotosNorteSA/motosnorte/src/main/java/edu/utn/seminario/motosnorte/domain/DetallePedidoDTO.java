package edu.utn.seminario.motosnorte.domain;

import java.util.List;

public class DetallePedidoDTO {
	
	private List<DetallePedidoMotos> motos;
	private List<DetallePedidoRepuestos> repuestos;
	
	
	public List<DetallePedidoMotos> getMotos() {
		return motos;
	}
	public void setMotos(List<DetallePedidoMotos> motos) {
		this.motos = motos;
	}
	public List<DetallePedidoRepuestos> getRepuestos() {
		return repuestos;
	}
	public void setRepuestos(List<DetallePedidoRepuestos> repuestos) {
		this.repuestos = repuestos;
	}
}
