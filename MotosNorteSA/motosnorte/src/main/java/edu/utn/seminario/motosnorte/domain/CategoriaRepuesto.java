package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class CategoriaRepuesto implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="categoriarepuesto_id")
	private Integer id;
	@Column(nullable=false)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoriaRepuesto == null) ? 0 : categoriaRepuesto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CategoriaRepuesto other = (CategoriaRepuesto) obj;
		if (categoriaRepuesto == null) {
			if (other.categoriaRepuesto != null)
				return false;
		} else if (!categoriaRepuesto.equals(other.categoriaRepuesto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return categoriaRepuesto;	
	}
}
