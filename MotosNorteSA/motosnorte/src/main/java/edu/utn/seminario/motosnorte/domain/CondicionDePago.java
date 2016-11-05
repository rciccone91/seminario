package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class CondicionDePago implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="condiciondepago_id")
	private Integer id;
	@Column(nullable=false)
	private String condicionDePago;
	
	public CondicionDePago() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCondicionDePago() {
		return condicionDePago;
	}

	public void setCondicionDePago(String condicionDePago) {
		this.condicionDePago = condicionDePago;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((condicionDePago == null) ? 0 : condicionDePago.hashCode());
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
		CondicionDePago other = (CondicionDePago) obj;
		if (condicionDePago == null) {
			if (other.condicionDePago != null)
				return false;
		} else if (!condicionDePago.equals(other.condicionDePago))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
