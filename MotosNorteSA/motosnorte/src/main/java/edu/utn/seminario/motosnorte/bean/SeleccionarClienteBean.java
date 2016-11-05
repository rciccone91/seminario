package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.utn.seminario.motosnorte.domain.Cliente;
import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.service.ClienteService;
import edu.utn.seminario.motosnorte.service.UsuarioService;

@ManagedBean(name = "seleccionarClienteBean")
@ViewScoped
public class SeleccionarClienteBean implements Serializable{

	private ClienteService service;
	private List<Cliente> clientes;
	private String operacion;

	@PostConstruct
	public void init() {
		service = new ClienteService();
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		operacion = paramMap.get("op");
		clientes = service.listar();
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
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
	
	public String eliminarCliente(Cliente c){
		try {
			service.eliminar(c);
			return "index.xhtml";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "seleccionarCliente.xhtml?op=eliminar";
		}
	}

}
