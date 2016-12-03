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
	@Column(nullable=false)
	private String modelo;
	@OneToOne
	@JoinColumn(name="marca_id",nullable=false)
	private Marca marca;
	@OneToOne
	@JoinColumn(name="moto_id",nullable=false)
	private Moto motoCompatible;
	@Column(nullable=true)
	private String descripcion;
	@Column(nullable=false)
	private Integer precio;
	@OneToOne
	@JoinColumn(name="categoriarepuesto_id",nullable=false)
	private CategoriaRepuesto categoriaRepuesto;
	@Column(nullable=false)
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
		this.modelo = modelo.replace("'", "");;
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
		this.descripcion = descripcion.replace("'", "");;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public CategoriaRepuesto getCategoriaRepuesto() {
		return categoriaRepuesto;
	}

	public void setCategoriaRepuesto(CategoriaRepuesto categoriaRepuesto) {
		this.categoriaRepuesto = categoriaRepuesto;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activo == null) ? 0 : activo.hashCode());
		result = prime * result + ((categoriaRepuesto == null) ? 0 : categoriaRepuesto.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((motoCompatible == null) ? 0 : motoCompatible.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Repuesto other = (Repuesto) obj;
		if (activo == null) {
			if (other.activo != null)
				return false;
		} else if (!activo.equals(other.activo))
			return false;
		if (categoriaRepuesto == null) {
			if (other.categoriaRepuesto != null)
				return false;
		} else if (!categoriaRepuesto.equals(other.categoriaRepuesto))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (motoCompatible == null) {
			if (other.motoCompatible != null)
				return false;
		} else if (!motoCompatible.equals(other.motoCompatible))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		return true;
	}
	
	public String getNombre(){
		return this.marca.getMarca() + " - " + this.getModelo();
	}
	
	public String getDescripcionForNotaDePedido(String stock){
		if(stock==null) stock="0";
		return this.modelo + " ("+stock+")"+ " - $" + this.precio;
	}
}
