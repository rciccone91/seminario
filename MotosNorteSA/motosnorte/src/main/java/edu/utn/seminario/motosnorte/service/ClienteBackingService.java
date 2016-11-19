package edu.utn.seminario.motosnorte.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.utn.seminario.motosnorte.dao.ClienteDao;
import edu.utn.seminario.motosnorte.domain.Cliente;
import edu.utn.seminario.motosnorte.exception.ClienteNoEncontradoException;

@ManagedBean(name="clienteBackingService", eager = true)
@ApplicationScoped
public class ClienteBackingService {

	private ClienteDao dao = new ClienteDao();
	
	@PostConstruct
	public void init() {

	}
	
	public Cliente getById(Integer id) throws ClienteNoEncontradoException{
		return dao.getById(id);
	}
	
	public List<Cliente> listar() {
		return dao.listar();
	}
	
	public List<Cliente> listarActivos() {
		return dao.listarActivos();
	}
}
