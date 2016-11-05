package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import edu.utn.seminario.motosnorte.domain.Cliente;
import edu.utn.seminario.motosnorte.exception.ClienteNoEncontradoException;
import edu.utn.seminario.motosnorte.service.ClienteService;


@SuppressWarnings("serial")
@ManagedBean(name = "modificarClienteBean")
@ViewScoped
public class ModificarClienteBean implements Serializable{

	private ClienteService service;
	private Integer id;
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

	@PostConstruct
	public void init() {
		
		service = new ClienteService();
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String par = paramMap.get("cliente_id");
		try {
			
			Cliente clienteById = service.getById(par);
			id = clienteById.getId();
			nombre = clienteById.getNombre();
			apellido = clienteById.getApellido();
			dni = clienteById.getDni();
			cuiCuit = clienteById.getCuiCuit();
			fechaNacimiento = clienteById.getFechaNacimiento();
			telefono = clienteById.getTelefono();
			direccion = clienteById.getDireccion();
			mail = clienteById.getMail();
			active = clienteById.getActive();
			
		} catch (ClienteNoEncontradoException e) {
			redirectError(e);
		}
	}

	private String redirectError(ClienteNoEncontradoException e) {
		return "error.xhtml?msg="+e.getMessage();
	}

	public String guardar(){
		try {
			service.modificar(armarCliente());
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"",
							"Cliente modificado correctamente"));
			return "index.xhtml";
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
			return "nuevoCliente.xhtml";
		}
	}

	private Cliente armarCliente() {
		Cliente c = new Cliente();
		c.setId(id);//----------------	OJO CON ESTO CHANNNNN----------
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
	
	public Integer getId() {
		return id;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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