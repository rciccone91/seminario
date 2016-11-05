package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.utn.seminario.motosnorte.domain.Sucursal;

import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.service.SucursalService;


@ManagedBean(name = "seleccionarSucursalBean")
@ViewScoped
public class SeleccionarSucursalBean implements Serializable{

	private SucursalService service;
	private List<Sucursal> sucursales;
	private String operacion;

	@PostConstruct
	public void init() {
		service = new SucursalService();
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		operacion = paramMap.get("op");
		sucursales = service.listar();
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Boolean mostrarModificar(){
		return operacion.equals(Constants.PARAMETRO_MODIFICAR);
	}

	public Boolean mostrarEliminar(){
		return operacion.equals(Constants.PARAMETRO_ELIMINAR);
	}
	
	public String eliminarSucursal(Sucursal s){
		try {
			System.out.println(s.getDescripcion());
			service.eliminar(s);
			return "index.xhtml";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "seleccionarSucursal.xhtml?op=eliminar";
		}
	}

}
