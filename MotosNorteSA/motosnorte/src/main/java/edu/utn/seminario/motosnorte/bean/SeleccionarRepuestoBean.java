package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.service.RepuestosService;

@ManagedBean(name = "seleccionarRepuestoBean")
@ViewScoped
public class SeleccionarRepuestoBean implements Serializable{
	
	private RepuestosService service;
	private List<Repuesto> repuestos;

	@PostConstruct
	public void init() {
		service = new RepuestosService();
		FacesContext context = FacesContext.getCurrentInstance();
		repuestos = service.listar();
	}

	public List<Repuesto> getRepuestos() {
		return repuestos;
	}

	public void setRepuestos(List<Repuesto> repuestos) {
		this.repuestos = repuestos;
	}

//	public String getOperacion() {
//		return operacion;
//	}
//
//	public void setOperacion(String operacion) {
//		this.operacion = operacion;
//	}
//	
//	public Boolean mostrarModificar(){
//		return operacion.equals(Constants.PARAMETRO_MODIFICAR);
//	}
//
//	public Boolean mostrarEliminar(){
//		return operacion.equals(Constants.PARAMETRO_ELIMINAR);
//	}
//	
	public String eliminarRepuesto(Repuesto r){
		try {
			service.eliminar(r);
			return "index.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return "seleccionarRepuesto.xhtml?op=eliminar";
		}
	}
}
