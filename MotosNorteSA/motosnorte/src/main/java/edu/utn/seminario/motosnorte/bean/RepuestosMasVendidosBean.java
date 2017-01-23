package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import edu.utn.seminario.motosnorte.domain.CategoriaRepuesto;
import edu.utn.seminario.motosnorte.domain.RepuestosMasVendidos;
import edu.utn.seminario.motosnorte.helper.CommonHelper;
import edu.utn.seminario.motosnorte.service.CategoriaRepuestoBackingService;
import edu.utn.seminario.motosnorte.service.DetallePedidoRepuestosService;

@ManagedBean(name = "repuestosMasVendidosBean")
@ViewScoped
public class RepuestosMasVendidosBean implements Serializable{
	
	private Date fechaDesde;
	private Date fechaHasta;
	private CategoriaRepuesto categoriaRepuesto;
	private List<CategoriaRepuesto> categorias;
	private List<RepuestosMasVendidos> lista;
	private Date maxDate;
	
	@ManagedProperty("#{categoriaRepuestoBackingService}")
	CategoriaRepuestoBackingService categoriaRepuestoBackingService;
	
	private DetallePedidoRepuestosService detalleService;

	
	@PostConstruct
	public void init() {
		categorias = categoriaRepuestoBackingService.listar();
		detalleService = new DetallePedidoRepuestosService();
		maxDate = CommonHelper.getTodayDate();
	}
	
	public void buscar(){
		 lista = detalleService.getRepuestosMasVendidos(fechaDesde,fechaHasta,categoriaRepuesto);
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
	public CategoriaRepuesto getCategoriaRepuesto() {
		return categoriaRepuesto;
	}
	public void setCategoriaRepuesto(CategoriaRepuesto categoriaRepuesto) {
		this.categoriaRepuesto = categoriaRepuesto;
	}
	public List<CategoriaRepuesto> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<CategoriaRepuesto> categorias) {
		this.categorias = categorias;
	}
	public List<RepuestosMasVendidos> getLista() {
		return lista;
	}
	public void setLista(List<RepuestosMasVendidos> lista) {
		this.lista = lista;
	}

	public CategoriaRepuestoBackingService getCategoriaRepuestoBackingService() {
		return categoriaRepuestoBackingService;
	}

	public void setCategoriaRepuestoBackingService(CategoriaRepuestoBackingService categoriaRepuestoBackingService) {
		this.categoriaRepuestoBackingService = categoriaRepuestoBackingService;
	}

	public DetallePedidoRepuestosService getDetalleService() {
		return detalleService;
	}

	public void setDetalleService(DetallePedidoRepuestosService detalleService) {
		this.detalleService = detalleService;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	
	
}
