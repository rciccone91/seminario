package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.utn.seminario.motosnorte.domain.DetallePedidoMotos;
import edu.utn.seminario.motosnorte.domain.DetallePedidoRepuestos;
import edu.utn.seminario.motosnorte.domain.Estado;
import edu.utn.seminario.motosnorte.domain.Pedido;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.service.DetallePedidoMotosService;
import edu.utn.seminario.motosnorte.service.DetallePedidoRepuestosService;
import edu.utn.seminario.motosnorte.service.PedidosService;

@ManagedBean(name = "detalleDePedidoBean")
@ViewScoped
public class DetalleDePedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private PedidosService pedidoService;
	private DetallePedidoMotosService detallePedidoMotosService;
	private DetallePedidoRepuestosService detallePedidoRepuestosService;
	private List<Pedido> pedido;
	private Integer idPed;
	private List<DetallePedidoMotos> detallePedidoMotos;
	private List<DetallePedidoRepuestos> detallePedidoRepuestos;
	
	@PostConstruct
	public void init() {
		pedidoService = new PedidosService();
		detallePedidoMotosService = new DetallePedidoMotosService();
		detallePedidoRepuestosService = new DetallePedidoRepuestosService();
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		idPed = Integer.parseInt(paramMap.get("ped"));
		pedido = pedidoService.listarFilterById(idPed);
		detallePedidoMotos = detallePedidoMotosService.listarFilterByPedidoId(idPed);
		detallePedidoRepuestos = detallePedidoRepuestosService.listarFilterByPedidoId(idPed);
	}

	public PedidosService getPedidoService() {
		return pedidoService;
	}

	public void setPedidoService(PedidosService pedidoService) {
		this.pedidoService = pedidoService;
	}

	public DetallePedidoMotosService getDetallePedidoMotosService() {
		return detallePedidoMotosService;
	}

	public void setDetallePedidoMotosService(DetallePedidoMotosService detallePedidoMotosService) {
		this.detallePedidoMotosService = detallePedidoMotosService;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	public List<DetallePedidoMotos> getDetallePedidoMotos() {
		return detallePedidoMotos;
	}

	public void setDetallePedidoMotos(List<DetallePedidoMotos> detallePedidoMotos) {
		this.detallePedidoMotos = detallePedidoMotos;
	}

	public DetallePedidoRepuestosService getDetallePedidoRepuestosService() {
		return detallePedidoRepuestosService;
	}

	public void setDetallePedidoRepuestosService(DetallePedidoRepuestosService detallePedidoRepuestosService) {
		this.detallePedidoRepuestosService = detallePedidoRepuestosService;
	}

	public List<DetallePedidoRepuestos> getDetallePedidoRepuestos() {
		return detallePedidoRepuestos;
	}

	public void setDetallePedidoRepuestos(List<DetallePedidoRepuestos> detallePedidoRepuestos) {
		this.detallePedidoRepuestos = detallePedidoRepuestos;
	}




}
