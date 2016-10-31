package edu.utn.seminario.motosnorte.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import edu.utn.seminario.motosnorte.dao.CategoriaMotoDao;
import edu.utn.seminario.motosnorte.domain.CategoriaMoto;

@ManagedBean(name="categoriaMotoBackingService", eager = true)
@ApplicationScoped
public class CategoriaMotoBackingService {

	private CategoriaMotoDao dao = new CategoriaMotoDao();

	@PostConstruct
	public void init() {

	}

	public CategoriaMoto getById(Integer id) {
		return dao.getById(id);
	}

	public List<CategoriaMoto> listar() {
		return dao.listar();
	}
}
