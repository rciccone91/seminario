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
	
	
	
}
