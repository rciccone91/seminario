package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.service.UsuarioService;

@ManagedBean(name = "seleccionarUsuarioBean")
@ViewScoped
public class SeleccionarUsuarioBean implements Serializable{

	private UsuarioService service;
	private List<Usuario> usuarios;
	private String operacion;

	@PostConstruct
	public void init() {
		service = new UsuarioService();
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		operacion = paramMap.get("op");
		usuarios = service.listar();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
	
	public String eliminarUsuario(Usuario u){
		try {
			service.eliminar(u);
			return "index.xhtml";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "seleccionarUsuario.xhtml?op=eliminar";
		}
	}

}
