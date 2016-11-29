package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import edu.utn.seminario.motosnorte.domain.Rol;
import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.exception.UsuarioNoEncontradoException;
import edu.utn.seminario.motosnorte.exception.UsuarioYaExistenteException;
import edu.utn.seminario.motosnorte.service.UsuarioService;

@SuppressWarnings("serial")
@ManagedBean(name = "modificarUsuarioBean")
@ViewScoped
public class ModificarUsuarioBean implements Serializable{

	private UsuarioService service;
	private String usuario;
	private String contrasenia;
	private Integer legajo;
	private Integer rolID;
	private List<Rol> roles;
	private UIComponent mensaje;

	@PostConstruct
	public void init() {
		
		service = new UsuarioService();
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String par = paramMap.get("usuario");
		try {
			Usuario userById = service.getById(par);
			usuario = userById.getUsuario();
			contrasenia= userById.getContrasenia();
			legajo= userById.getLegajo();
			rolID= userById.getRol();
		} catch (UsuarioNoEncontradoException e) {
			redirectError(e);
		}
		roles = Rol.getRoles();
	}

	private String redirectError(UsuarioNoEncontradoException e) {
		return "error.xhtml?msg="+e.getMessage();
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public void guardar(){
		try {
			service.modificar(armarUsuario());
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"",
							"Usuario modificado correctamente"));
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('successDialog').show();");
//			return "index.xhtml";
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
//			return "nuevoUsuario.xhtml";
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