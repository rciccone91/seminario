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
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.exception.DescripcionDeSucursalYaExistenteException;
import edu.utn.seminario.motosnorte.exception.DireccionDeSucursalYaExistenteException;
import edu.utn.seminario.motosnorte.exception.SucursalNoEncontradaException;

import edu.utn.seminario.motosnorte.service.SucursalService;


@SuppressWarnings("serial")
@ManagedBean(name = "modificarSucursalBean")
@ViewScoped
public class ModificarSucursalBean implements Serializable{

	private SucursalService service;

	private Integer id;
	private String descripcion;
	private String telefono;
	private String direccion;
	private boolean activo;
	private boolean sucursalLocal;
	private String sucursal;
	private UIComponent mensaje;


	@PostConstruct
	public void init() {

		service = new SucursalService();
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String par = paramMap.get("id");
		try {
			if(par!=null){
				Sucursal sucById = service.getById(Integer.parseInt(par));
				id    = sucById.getId();
				descripcion = sucById.getDescripcion();
				telefono = sucById.getTelefono();
				direccion = sucById.getDireccion();
				activo = sucById.getActivo();
			}
		} catch (SucursalNoEncontradaException e) {
			redirectError(e);
		}
		catch (Exception e) {
			redirectError(e);
		}

	}

	private String redirectError(Exception e) {
		return "error.xhtml?msg="+e.getMessage();
	}


	public String guardar(){
		try {
			System.out.println("Ingresa a guardar");
			service.modificar(armarSucursal());
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"",
							"Sucursal modificada correctamente"));
			return "index.xhtml";
		}catch (DescripcionDeSucursalYaExistenteException|DireccionDeSucursalYaExistenteException e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Atención",
							e.getMessage()));
			return "modificarSucursal.xhtml?id=#{id}";
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
			return "modificarSucursal.xhtml";
		}
	}

	private Sucursal armarSucursal() {
		System.out.println("Arma sucursal");
		Sucursal s = new Sucursal();
		s.setDescripcion(descripcion);
		s.setDireccion(direccion);
		s.setTelefono(telefono);
		s.setId(id);
		s.setActivo(activo);
		return s;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean isSucursalLocal() {
		return sucursalLocal;
	}

	public void setSucursalLocal(boolean sucursalLocal) {
		this.sucursalLocal = sucursalLocal;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public void setService(SucursalService service) {
		this.service = service;
	}


	public UIComponent getMensaje() {
		return mensaje;
	}

	public void setMensaje(UIComponent mensaje) {
		this.mensaje = mensaje;
	}
}