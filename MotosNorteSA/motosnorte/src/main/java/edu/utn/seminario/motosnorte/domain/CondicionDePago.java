package edu.utn.seminario.motosnorte.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class CondicionDePago implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="condiciondepago_id")
	private Integer id;
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
}
