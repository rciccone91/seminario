package edu.utn.seminario.motosnorte.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.helper.SessionHelper;

@ManagedBean(name = "menuBean", eager = true)
@ApplicationScoped
public class MenuBean {
	
	public Boolean mostrarUsuario(){
		return SessionHelper.getRolId().equals(Constants.ADMINISTRADOR_ID);
	}
	
	public Boolean mostrarProductos(){
		return SessionHelper.getRolId().equals(Constants.ADMINISTRADOR_ID)
				|| SessionHelper.getRolId().equals(Constants.EMPLEADO_DE_DEPOSITO_ID);
	}
	
	public Boolean mostrarCliente(){
		return SessionHelper.getRolId().equals(Constants.ADMINISTRADOR_ID)
				|| SessionHelper.getRolId().equals(Constants.VENDEDOR_ID);
	}
	
	public Boolean mostrarSucursal(){
		return SessionHelper.getRolId().equals(Constants.ADMINISTRADOR_ID);
	}

}
