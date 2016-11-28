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

import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.exception.DescripcionDeSucursalYaExistenteException;
import edu.utn.seminario.motosnorte.exception.DireccionDeSucursalYaExistenteException;
import edu.utn.seminario.motosnorte.exception.SucursalYaExistenteException;
import edu.utn.seminario.motosnorte.service.SucursalService;


@ManagedBean(name = "nuevaSucursalBean")
@ViewScoped
public class NuevaSucursalBean implements Serializable{


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
	}


	public String guardar(){
		try {
			service.guardar(armarSucursal());
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"",
							"Sucursal registrada correctamente"));
			return "index.xhtml";
		}catch (DescripcionDeSucursalYaExistenteException e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Atención",
							e.getMessage()));
			return "nuevaSucursal.xhtml";
		}catch (DireccionDeSucursalYaExistenteException e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Atención",
							e.getMessage()));
			return "nuevaSucursal.xhtml";
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
			return "nuevaSucursal.xhtml";
		}
	}

	private Sucursal armarSucursal() {
		Sucursal s = new Sucursal();
		s.setDescripcion(descripcion);
		s.setDireccion(direccion);
		s.setTelefono(telefono);
		s.setActivo(true);
		return s;
	}

	public SucursalService getService() {
		return service;
	}

	public void setService(SucursalService service) {
		this.service = service;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal	) {
		this.sucursal = sucursal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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

	public UIComponent getMensaje() {
		return mensaje;
	}

	public void setMensaje(UIComponent mensaje) {
		this.mensaje = mensaje;
	}
}

