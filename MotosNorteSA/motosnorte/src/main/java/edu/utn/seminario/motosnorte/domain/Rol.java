package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.utn.seminario.motosnorte.helper.Constants;


public class Rol{

	private Integer id;
	private String rol;
	
	public Rol(Integer id, String rol) {
		super();
		this.id = id;
		this.rol = rol;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

	public static List<Rol> getRoles(){
		
		List<Rol> roles = new ArrayList<>();
		roles.add(new Rol(Constants.ADMINISTRADOR_ID,"Administrador"));
		roles.add(new Rol(Constants.VENDEDOR_ID,"Vendedor"));
		roles.add(new Rol(Constants.EMPLEADO_DE_DEPOSITO_ID,"Emp. de depósito"));
		return roles;
	}
	
}