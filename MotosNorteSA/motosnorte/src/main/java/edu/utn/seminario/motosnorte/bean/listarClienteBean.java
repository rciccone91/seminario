package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import edu.utn.seminario.motosnorte.domain.Cliente;
import edu.utn.seminario.motosnorte.service.ClienteService;


@ManagedBean(name = "listarClienteBean")
@ViewScoped
public class listarClienteBean implements Serializable{

	private ClienteService service;
	private List<Cliente> clientes;

	@PostConstruct
	public void init() {
		service = new ClienteService();	
		clientes = service.listar();	
	}

	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}
