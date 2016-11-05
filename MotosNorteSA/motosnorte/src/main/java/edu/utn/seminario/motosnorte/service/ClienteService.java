package edu.utn.seminario.motosnorte.service;

import java.io.Serializable;
import java.util.List;
import edu.utn.seminario.motosnorte.dao.ClienteDao;
import edu.utn.seminario.motosnorte.domain.Cliente;
import edu.utn.seminario.motosnorte.exception.ClienteNoEncontradoException;
import edu.utn.seminario.motosnorte.exception.ClienteYaExistenteException;


public class ClienteService implements Serializable{
	
private ClienteDao dao;
	
	public ClienteService()
	{
		if(this.dao == null){
			dao = new ClienteDao();
		}
	}
	

	public void guardar(Cliente cliente) throws ClienteYaExistenteException,Exception{
		if(dao.existe(cliente))
			throw new ClienteYaExistenteException("El Cliente ya existe, por favor ingrese otro.");
		dao.guardar(cliente);
	}
	
	public List<Cliente> listar() {
		return dao.listar();
	}
	
	public Cliente getById(String id) throws ClienteNoEncontradoException {
		return dao.getById(id);
	}

	public void modificar(Cliente cliente) throws Exception{
		dao.modificar(cliente);
	}

	public void eliminar(Cliente cliente) throws Exception {
		dao.eliminar(cliente);
	}

}
