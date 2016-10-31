package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class CategoriaMoto implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="categoriamoto_id")
	private Integer id;
	private String categoriaMoto;
	
	public CategoriaMoto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoriaMoto() {
		return categoriaMoto;
	}

	public void setCategoriaMoto(String categoriaMoto) {
		this.categoriaMoto = categoriaMoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoriaMoto == null) ? 0 : categoriaMoto.hashCode());
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
		CategoriaMoto other = (CategoriaMoto) obj;
		if (categoriaMoto == null) {
			if (other.categoriaMoto != null)
				return false;
		} else if (!categoriaMoto.equals(other.categoriaMoto))
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
		return categoriaMoto;
	}
	
	
}
