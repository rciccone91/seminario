package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import edu.utn.seminario.motosnorte.domain.Cliente;
import edu.utn.seminario.motosnorte.exception.ClienteYaExistenteException;
import edu.utn.seminario.motosnorte.service.ClienteService;


@ManagedBean(name = "nuevoClienteBean")
@ViewScoped
public class NuevoClienteBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClienteService service;
//	private Integer id;
	private String nombre;
	private String apellido;
	private String dni;
	private String cuiCuit;
	private Date fechaNacimiento;
	private String telefono;
	private Boolean active;
	private String direccion;
	private String mail;
	private UIComponent mensaje;
	
	 public void onDateSelect(SelectEvent event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	    }
	     
	    public void click() {
	        RequestContext requestContext = RequestContext.getCurrentInstance();
	         
	        requestContext.update("form:display");
	        requestContext.execute("PF('dlg').show()");
	    }
	
	@PostConstruct
	public void init() {
			
			service = new ClienteService();
	}
	
	public void guardar(){
		
		try {
			service.guardar(armarCliente());
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"",
							"Cliente registrado correctamente"));
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('successDialog').show();");
//			return "index.xhtml";
		}catch (ClienteYaExistenteException e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Atención",
							e.getMessage()));
//			return "nuevoCliente.xhtml";
		} 
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
//			return "nuevoCliente.xhtml";
		}
	}

	private Cliente armarCliente() {
		Cliente c = new Cliente();
		c.setActive(true);
		c.setApellido(apellido);
		c.setNombre(nombre);
		c.setDni(dni);
		c.setDireccion(direccion);
		c.setMail(mail);
		c.setTelefono(telefono);
		c.setCuiCuit(cuiCuit);
		c.setFechaNacimiento(fechaNacimiento);
		return c;
	}

	public ClienteService getService() {
		return service;
	}

	public void setService(ClienteService service) {
		this.service = service;
	}

//	public Integer getid() {
//		return id;
//	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCuiCuit() {
		return cuiCuit;
	}

	public void setCuiCuit(String cuiCuit) {
		this.cuiCuit = cuiCuit;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public UIComponent getMensaje() {
		return mensaje;
	}

	public void setMensaje(UIComponent mensaje) {
		this.mensaje = mensaje;
	}
}
