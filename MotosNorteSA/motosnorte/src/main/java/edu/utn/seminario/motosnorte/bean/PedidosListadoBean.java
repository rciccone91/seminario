package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.utn.seminario.motosnorte.domain.Estado;
import edu.utn.seminario.motosnorte.domain.Pedido;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.service.PedidosService;

@ManagedBean(name = "pedidosListadoBean")
@ViewScoped
public class PedidosListadoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private PedidosService service;
	private List<Pedido> pedidos;
	private List<Estado> estados;
	
	@PostConstruct
	public void init() {
		service = new PedidosService();
		pedidos = service.listar();	
	}

	public PedidosService getService() {
		return service;
	}

	public void setService(PedidosService service) {
		this.service = service;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Estado> getEstados() {

        return Estado.getEstados();
    }
	
	
	
}
