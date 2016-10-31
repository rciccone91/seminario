package edu.utn.seminario.motosnorte.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import edu.utn.seminario.motosnorte.dao.CilindradaDao;
import edu.utn.seminario.motosnorte.dao.MarcaDao;
import edu.utn.seminario.motosnorte.domain.Cilindrada;
import edu.utn.seminario.motosnorte.domain.Marca;

@ManagedBean(name="cilindradaBackingService", eager = true)
@ApplicationScoped
public class CilindradaBackingService {
	
	private CilindradaDao dao = new CilindradaDao();
	
	@PostConstruct
	public void init() {

	}
	
	public Cilindrada getById(Integer id){
		return dao.getById(id);
	}
	
	public List<Cilindrada> listar() {
		return dao.listar();
	}
}
