package edu.utn.frgp.laboratoriov.domain;

public class Imagen {

	private Integer id;
	private String archivo;
	private Boolean activa;
	
	public Imagen() {
		super();
	}
		
	public Imagen(Integer id, String archivo, Integer propiedadId,
			Boolean activa) {
		super();
		this.id = id;
		this.archivo = archivo;
		this.activa = activa;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public Boolean getActiva() {
		return activa;
	}
	public void setActiva(Boolean activa) {
		this.activa = activa;
	}
	
	
}
