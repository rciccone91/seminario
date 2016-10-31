package edu.utn.seminario.motosnorte.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.utn.seminario.motosnorte.dao.CategoriaRepuestoDao;
import edu.utn.seminario.motosnorte.domain.CategoriaRepuesto;


@ManagedBean(name="categoriaRepuestoBackingService", eager = true)
@ApplicationScoped
public class CategoriaRepuestoBackingService {
	
	private CategoriaRepuestoDao dao = new CategoriaRepuestoDao();
	
	@PostConstruct
	public void init() {

	}
	
	public CategoriaRepuesto getById(Integer id) {
		return dao.getById(id);
	}
	
	public List<CategoriaRepuesto> listar() {
		return dao.listar();
	}
}
