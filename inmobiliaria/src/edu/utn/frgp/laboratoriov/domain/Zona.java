package edu.utn.frgp.laboratoriov.domain;

public class Zona {
	private Integer id;
	private String descripcion;
	
	public Zona(Integer id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public Zona() {
		super();
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
	
	
}
