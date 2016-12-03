package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import edu.utn.seminario.motosnorte.helper.Constants;

@Entity
@Table(name="motos")
public class Moto implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="moto_id")
	private Integer id;
	@Column(nullable=false)
	private String modelo;
	@OneToOne
	@JoinColumn(name="marca_id",nullable=false)
	private Marca marca;
	@Column(nullable=true)
	private Date año;
	@OneToOne
	@JoinColumn(name="cilindrada_id",nullable=true)
	private Cilindrada cilindrada;
	@Column(nullable=true)
	private Integer color;
	@Column(nullable=true)
	private Integer peso;
	@Column(nullable=false)
	private Integer precio;
	@OneToOne
	@JoinColumn(name="categoriamoto_id",nullable=true)
	private CategoriaMoto categoriaMoto;
	@Column(nullable=false)
	private Boolean activo;

	
	public Moto() {
		super();
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
		this.modelo = modelo.replace("'", "");
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Date getAño() {
		return año;
	}

	public void setAño(Date año) {
		this.año = año;
	}

	public Cilindrada getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(Cilindrada cilindrada) {
		this.cilindrada = cilindrada;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public CategoriaMoto getCategoriaMoto() {
		return categoriaMoto;
	}

	public void setCategoriaMoto(CategoriaMoto categoriaMoto) {
		this.categoriaMoto = categoriaMoto;
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
		result = prime * result + ((año == null) ? 0 : año.hashCode());
		result = prime * result + ((categoriaMoto == null) ? 0 : categoriaMoto.hashCode());
		result = prime * result + ((cilindrada == null) ? 0 : cilindrada.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
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
		Moto other = (Moto) obj;
		if (activo == null) {
			if (other.activo != null)
				return false;
		} else if (!activo.equals(other.activo))
			return false;
		if (año == null) {
			if (other.año != null)
				return false;
		} else if (!año.equals(other.año))
			return false;
		if (categoriaMoto == null) {
			if (other.categoriaMoto != null)
				return false;
		} else if (!categoriaMoto.equals(other.categoriaMoto))
			return false;
		if (cilindrada == null) {
			if (other.cilindrada != null)
				return false;
		} else if (!cilindrada.equals(other.cilindrada))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
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
		if (peso == null) {
			if (other.peso != null)
				return false;
		} else if (!peso.equals(other.peso))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		return true;
	}
	
	public String getDescripcion(){
		return this.marca.getMarca() + " - " + this.getModelo();
	}
	
	public String getColorDescripcion(){
		return Constants.getColorDescription(this.color);
	}
}
