package edu.utn.seminario.motosnorte.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import edu.utn.seminario.motosnorte.dao.RepuestosDao;
import edu.utn.seminario.motosnorte.domain.Repuesto;

@ManagedBean(name="repuestoBackingService", eager = true)
@ApplicationScoped
public class RepuestoBackingService {

	private RepuestosDao dao = new RepuestosDao();

	@PostConstruct
	public void init() {
	}	
	
	public Repuesto getById(Integer id) {
		return dao.getById(id);
	}
	
	public List<Repuesto> listar() {
		return dao.listar();
	}
}
