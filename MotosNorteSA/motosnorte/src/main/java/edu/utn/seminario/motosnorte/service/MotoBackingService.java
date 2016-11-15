package edu.utn.seminario.motosnorte.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import edu.utn.seminario.motosnorte.dao.MotoDao;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.Usuario;

@ManagedBean(name="motoBackingService", eager = true)
@ApplicationScoped
public class MotoBackingService {
	
	private MotoDao dao = new MotoDao();

	@PostConstruct
	public void init() {
	}	
	
	public Moto getById(Integer id) {
		return dao.getById(id);
	}
	
	public List<Moto> listar() {
		return dao.listar();
	}
}
