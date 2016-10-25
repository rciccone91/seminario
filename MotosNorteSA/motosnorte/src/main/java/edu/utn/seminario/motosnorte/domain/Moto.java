package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="motos")
public class Moto implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="moto_id")
	private Integer id;
	private String modelo;
	@OneToOne
	@JoinColumn(name="marca_id")
	private Marca marca;
	@Column(nullable=true)
	private Date año;
	@OneToOne
	@JoinColumn(name="cilindrada_id",nullable=true)
	private Cilindrada cilindrada;
	@Column(nullable=true)
	private String color;
	@Column(nullable=true)
	private Integer peso;
	private Integer precio;
	@OneToOne
	@JoinColumn(name="categoriamoto_id",nullable=true)
	private CategoriaMoto categoriaMoto;
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
		this.modelo = modelo;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
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

	public CategoriaMoto getTipoDeMoto() {
		return categoriaMoto;
	}

	public void setTipoDeMoto(CategoriaMoto categoriaMoto) {
		this.categoriaMoto = categoriaMoto;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
	
	
}
