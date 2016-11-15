package edu.utn.seminario.motosnorte.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import edu.utn.seminario.motosnorte.dao.MarcaDao;
import edu.utn.seminario.motosnorte.domain.Marca;

@ManagedBean(name="marcaBackingService", eager = true)
@ApplicationScoped
public class MarcaBackingService {

	private MarcaDao dao = new MarcaDao();
	
	public MarcaBackingService(){
		super();
	}
	
	@PostConstruct
	public void init() {

	}
	
	public Marca getById(Integer id) {
		return dao.getById(id);
	}
	
	public List<Marca> listar() {
		return dao.listar();
	}
}
