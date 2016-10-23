package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class CategoriaRepuesto implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="categoriarepuesto_id")
	private Integer id;
	private String categoriaRepuesto;
	
	public CategoriaRepuesto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoriaRepuesto() {
		return categoriaRepuesto;
	}

	public void setCategoriaRepuesto(String categoriaRepuesto) {
		this.categoriaRepuesto = categoriaRepuesto;
	}
}
