package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.domain.StockMotos;
import edu.utn.seminario.motosnorte.domain.StockRepuestos;
import edu.utn.seminario.motosnorte.service.MarcaBackingService;
import edu.utn.seminario.motosnorte.service.RepuestoBackingService;
import edu.utn.seminario.motosnorte.service.RepuestosService;
import edu.utn.seminario.motosnorte.service.StockMotosService;
import edu.utn.seminario.motosnorte.service.StockRepuestosService;


@ManagedBean(name = "productosRepuestosBean")
@ViewScoped
public class productosRepuestosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RepuestosService service;
	private List<Repuesto> repuestos;
	private List<StockRepuestos> stockRepuestos;
	private StockRepuestosService stockService;
	private UIComponent mensaje;
	
	@PostConstruct
	public void init() {
		service = new RepuestosService();	
		repuestos = service.listar();	

	}
	
	public void buscarStock(Repuesto rep){
		try {
			stockRepuestos = new ArrayList<StockRepuestos>();
			stockService = new StockRepuestosService();
			stockRepuestos = stockService.getStockByRepuesto(rep);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
		}
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

	public List<StockRepuestos> getStockRepuestos() {
		return stockRepuestos;
	}

	public void setStockRepuestos(List<StockRepuestos> stockRepuestos) {
		this.stockRepuestos = stockRepuestos;
	}

	public UIComponent getMensaje() {
		return mensaje;
	}

	public void setMensaje(UIComponent mensaje) {
		this.mensaje = mensaje;
	}
}
