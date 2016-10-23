package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import edu.utn.seminario.motosnorte.domain.Rol;
import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.exception.UsuarioYaExistenteException;
import edu.utn.seminario.motosnorte.service.RolService;
import edu.utn.seminario.motosnorte.service.UsuarioService;

@ManagedBean(name = "nuevoUsuarioBean")
@ViewScoped
public class NuevoUsuarioBean implements Serializable{


	private UsuarioService service;
	private String usuario;
	private String contrasenia;
	private Integer legajo;
	private Integer rolID;
	private List<Rol> roles;
	private UIComponent mensaje;
	
	@PostConstruct
	public void init() {
			roles = Rol.getRoles();
			service = new UsuarioService();
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	public String guardar(){
		try {
			service.guardar(armarUsuario());
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"",
							"Usuario registrado correctamente"));
			return "index.xhtml";
		}catch (UsuarioYaExistenteException e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Atención",
							e.getMessage()));
			return "nuevoUsuario.xhtml";
		} 
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
			return "nuevoUsuario.xhtml";
		}
	}

	private Usuario armarUsuario() {
		Usuario u = new Usuario();
		u.setActivo(true);
		u.setContrasenia(contrasenia);
		u.setLegajo(legajo);
		u.setRol(rolID);
		u.setUsuario(usuario);
		return u;
	}

	public UsuarioService getService() {
		return service;
	}

	public void setService(UsuarioService service) {
		this.service = service;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Integer getLegajo() {
		return legajo;
	}

	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}

	public Integer getRolID() {
		return rolID;
	}

	public void setRolID(Integer rol) {
		this.rolID = rol;
	}

	public UIComponent getMensaje() {
		return mensaje;
	}

	public void setMensaje(UIComponent mensaje) {
		this.mensaje = mensaje;
	}
}
