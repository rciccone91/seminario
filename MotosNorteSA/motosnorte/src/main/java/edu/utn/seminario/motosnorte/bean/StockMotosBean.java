package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.utn.seminario.motosnorte.domain.CategoriaMoto;
import edu.utn.seminario.motosnorte.domain.Cilindrada;
import edu.utn.seminario.motosnorte.domain.Color;
import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.StockMotos;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.service.CategoriaMotoBackingService;
import edu.utn.seminario.motosnorte.service.CategoriaRepuestoBackingService;
import edu.utn.seminario.motosnorte.service.CilindradaBackingService;
import edu.utn.seminario.motosnorte.service.MarcaBackingService;
import edu.utn.seminario.motosnorte.service.MotoBackingService;
import edu.utn.seminario.motosnorte.service.MotoService;
import edu.utn.seminario.motosnorte.service.StockMotosService;


@ManagedBean(name = "stockMotosBean")
@ViewScoped
public class StockMotosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StockMotosService service;
	private List<StockMotos> motos;
	private StockMotos modelo;
	private List<StockMotos> modelos;
//	private StockMotos cantidad;
//	private List<StockMotos> cantidades;
	
//	@ManagedProperty("#{stockMotoBackingService}")
//	StockMotoBackingService stockMotoBackingService;
	
	@PostConstruct
	public void init() {
		service = new StockMotosService();	
		motos = service.listar();
//		modelos = stockMotoBackingService.listar();
	}
	
//	public List<StockMotos> filtroMotos(){
//		motos = service.buscarStockMotos(modelo.getId(),cantidad.getCantidad());
//		return motos;
//	}

	public StockMotosService getService() {
		return service;
	}

	public void setService(StockMotosService service) {
		this.service = service;
	}

	public List<StockMotos> getMotos() {
		return motos;
	}

	public void setMotos(List<StockMotos> motos) {
		this.motos = motos;
	}

	public StockMotos getModelo() {
		return modelo;
	}

	public void setModelo(StockMotos modelo) {
		this.modelo = modelo;
	}

	public List<StockMotos> getModelos() {
		return modelos;
	}

	public void setModelos(List<StockMotos> modelos) {
		this.modelos = modelos;
	}

//	public StockMotos getCantidad() {
//		return cantidad;
//	}
//
//	public void setCantidad(StockMotos cantidad) {
//		this.cantidad = cantidad;
//	}
//
//	public List<StockMotos> getCantidades() {
//		return cantidades;
//	}
//
//	public void setCantidades(List<StockMotos> cantidades) {
//		this.cantidades = cantidades;
//	}

//	public StockMotoBackingService getStockMotoBackingService() {
//		return stockMotoBackingService;
//	}
//
//	public void setStockMotoBackingService(
//			StockMotoBackingService stockMotoBackingService) {
//		this.stockMotoBackingService = stockMotoBackingService;
//	}
//		

}
