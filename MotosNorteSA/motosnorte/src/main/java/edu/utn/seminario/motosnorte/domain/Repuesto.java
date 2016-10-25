package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="repuestos")
public class Repuesto implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="repuesto_id")
	private Integer id;
	private String modelo;
	@OneToOne
	@JoinColumn(name="marca_id")
	private Marca marca;
	@OneToOne
	@JoinColumn(name="moto_id")
	private Moto motoCompatible;
	@Column(nullable=true)
	private String descripcion;
	private Integer precio;
	@OneToOne
	@JoinColumn(name="categoriarepuesto_id")
	private CategoriaRepuesto categoriaRepuesto;
	private Boolean activo;
	
	public Repuesto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Moto getMotoCompatible() {
		return motoCompatible;
	}

	public void setMotoCompatible(Moto motoCompatible) {
		this.motoCompatible = motoCompatible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public CategoriaRepuesto getCategoriaMoto() {
		return categoriaRepuesto;
	}

	public void setCategoriaMoto(CategoriaRepuesto categoriaRepuesto) {
		this.categoriaRepuesto = categoriaRepuesto;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
	
	
}
