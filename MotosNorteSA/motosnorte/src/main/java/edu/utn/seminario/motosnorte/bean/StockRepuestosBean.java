package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import edu.utn.seminario.motosnorte.domain.StockRepuestos;
import edu.utn.seminario.motosnorte.service.StockRepuestosService;


@ManagedBean(name = "stockRepuestosBean")
@ViewScoped
public class StockRepuestosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StockRepuestosService service;
	private List<StockRepuestos> repuestos;
//	private StockRepuestos descripcion;
//	private List<StockRepuestos> descripciones;
//	private StockRepuestos cantidad;
//	private List<StockRepuestos> cantidades;
	
	@PostConstruct
	public void init() {
		service = new StockRepuestosService();	
		repuestos = service.listar();
//		FacesContext context = FacesContext.getCurrentInstance();
//		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
//		setRepuestoId(paramMap.get("id"));
//		if(repuestoId != null){
//			RequestContext contextRq = RequestContext.getCurrentInstance();
//			contextRq.execute(" PF('stockRep').filter();");
//		}	
//		
	}
	
//	public List<StockRepuestos> filtroRepuestos(){
//		repuestos = service.buscarStockRepuestos(descripcion.getId(),cantidad.getCantidad());
//		return repuestos;
//	}

	public StockRepuestosService getService() {
		return service;
	}

	public void setService(StockRepuestosService service) {
		this.service = service;
	}

	public List<StockRepuestos> getRepuestos() {
		return repuestos;
	}

	public void setRepuestos(List<StockRepuestos> repuestos) {
		this.repuestos = repuestos;
	}

//	public String getRepuestoId() {
//		return repuestoId;
//	}
//
//	public void setRepuestoId(String repuestoId) {
//		this.repuestoId = repuestoId;
//	}

//	public StockRepuestos getDescripcion() {
//		return descripcion;
//	}
//
//	public void setDescripcion(StockRepuestos descripcion) {
//		this.descripcion = descripcion;
//	}
//
//	public List<StockRepuestos> getDescripciones() {
//		return descripciones;
//	}
//
//	public void setDescripciones(List<StockRepuestos> descripciones) {
//		this.descripciones = descripciones;
//	}
//
//	public StockRepuestos getCantidad() {
//		return cantidad;
//	}
//
//	public void setCantidad(StockRepuestos cantidad) {
//		this.cantidad = cantidad;
//	}
//
//	public List<StockRepuestos> getCantidades() {
//		return cantidades;
//	}
//
//	public void setCantidades(List<StockRepuestos> cantidades) {
//		this.cantidades = cantidades;
//	}

//	public StockRepuestoBackingService getStockRepuestoBackingService() {
//		return stockRepuestoBackingService;
//	}
//
//	public void setStockRepuestoBackingService(
//			StockRepuestoBackingService stockRepuestoBackingService) {
//		this.stockRepuestoBackingService = stockRepuestoBackingService;
//	}
	
	
}
