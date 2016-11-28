package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.service.MotoService;

@ManagedBean(name = "seleccionarMotoBean")
@ViewScoped
public class SeleccionarMotoBean implements Serializable{
	
	private MotoService service;
	private List<Moto> motos;
//	private String operacion;

	@PostConstruct
	public void init() {
		service = new MotoService();
		FacesContext context = FacesContext.getCurrentInstance();
//		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
//		operacion = paramMap.get("op");
		motos = service.listar();
	}

	public List<Moto> getMotos() {
		return motos;
	}

	public void setMotos(List<Moto> motos) {
		this.motos = motos;
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
	
	public String eliminarMoto(Moto m){
		try {
			service.eliminar(m);
			return "index.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return "seleccionarMoto.xhtml?op=eliminar";
		}
	}
}
