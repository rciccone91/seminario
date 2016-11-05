package edu.utn.seminario.motosnorte.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import edu.utn.seminario.motosnorte.dao.SucursalDao;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.exception.SucursalNoEncontradaException;

@ManagedBean(name="sucursalBackingService", eager = true)
@ApplicationScoped
public class SucursalBackingService {

	private SucursalDao dao = new SucursalDao();
	
	public Object getById(int id) throws SucursalNoEncontradaException {
		try{
			return dao.getById(id);
		}catch(SucursalNoEncontradaException e){
			throw e;
		}
		catch(Exception ex){
			throw ex;
		}
	}

	public List<Sucursal> listarActivos() {
		return dao.listarActivos();
	}

}
