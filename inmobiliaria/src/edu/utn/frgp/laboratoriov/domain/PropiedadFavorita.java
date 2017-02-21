package edu.utn.frgp.laboratoriov.domain;

import java.util.Date;

public class PropiedadFavorita {

	private Integer id;
	private Propiedad propiedad;
	private Usuario usuario;
	private Date creationDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Propiedad getPropiedad() {
		return propiedad;
	}
	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public PropiedadFavorita(Integer id, Propiedad propiedad, Usuario usuario, Date creationDate) {
		super();
		this.id = id;
		this.propiedad = propiedad;
		this.usuario = usuario;
		this.creationDate = creationDate;
	}
	
	
	
}
