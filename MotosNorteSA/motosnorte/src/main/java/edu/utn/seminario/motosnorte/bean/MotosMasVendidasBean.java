package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.utn.seminario.motosnorte.domain.CategoriaMoto;
import edu.utn.seminario.motosnorte.domain.Color;
import edu.utn.seminario.motosnorte.helper.CommonHelper;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.service.CategoriaMotoBackingService;
import edu.utn.seminario.motosnorte.service.DetallePedidoMotosService;
import edu.utn.seminario.motosnorte.service.DetallePedidoRepuestosService;
import edu.utn.seminario.motosnorte.service.MotoService;
import edu.utn.seminario.motosnorte.transferobject.MotosMasVendidas;

@ManagedBean(name = "motosMasVendidasBean")
@ViewScoped
public class MotosMasVendidasBean implements Serializable{
	
	private Date fechaDesde;
	private Date fechaHasta;
	private CategoriaMoto categoriaMoto;
	private List<CategoriaMoto> categorias;
	private List<MotosMasVendidas> lista;
	private Date maxDate;
	
	@ManagedProperty("#{categoriaMotoBackingService}")
	CategoriaMotoBackingService categoriaMotoBackingService;
	
	private DetallePedidoMotosService detalleService;

	
	@PostConstruct
	public void init() {
		categorias = categoriaMotoBackingService.listar();
		detalleService = new DetallePedidoMotosService();
		maxDate = CommonHelper.getTodayDate();
	}
	
	public void buscar(){
		 lista = detalleService.getMotosMasVendidas(fechaDesde,fechaHasta,categoriaMoto);
	}
	
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public CategoriaMoto getCategoriaMoto() {
		return categoriaMoto;
	}
	public void setCategoriaMoto(CategoriaMoto categoriaMoto) {
		this.categoriaMoto = categoriaMoto;
	}
	public List<CategoriaMoto> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<CategoriaMoto> categorias) {
		this.categorias = categorias;
	}
	public List<MotosMasVendidas> getLista() {
		return lista;
	}
	public void setLista(List<MotosMasVendidas> lista) {
		this.lista = lista;
	}

	public CategoriaMotoBackingService getCategoriaMotoBackingService() {
		return categoriaMotoBackingService;
	}

	public void setCategoriaMotoBackingService(CategoriaMotoBackingService categoriaMotoBackingService) {
		this.categoriaMotoBackingService = categoriaMotoBackingService;
	}

	public DetallePedidoMotosService getDetalleService() {
		return detalleService;
	}

	public void setDetalleService(DetallePedidoMotosService detalleService) {
		this.detalleService = detalleService;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	

	
}
