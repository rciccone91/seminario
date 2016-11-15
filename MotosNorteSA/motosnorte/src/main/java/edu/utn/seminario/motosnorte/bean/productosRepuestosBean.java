package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.service.MarcaBackingService;
import edu.utn.seminario.motosnorte.service.RepuestoBackingService;
import edu.utn.seminario.motosnorte.service.RepuestosService;


@ManagedBean(name = "productosRepuestosBean")
@ViewScoped
public class productosRepuestosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RepuestosService service;
	private List<Repuesto> repuestos;

	@PostConstruct
	public void init() {
		service = new RepuestosService();	
		repuestos = service.listar();	

	}

	public RepuestosService getService() {
		return service;
	}

	public void setService(RepuestosService service) {
		this.service = service;
	}

	public List<Repuesto> getRepuestos() {
		return repuestos;
	}

	public void setRepuestos(List<Repuesto> repuestos) {
		this.repuestos = repuestos;
	}
}
