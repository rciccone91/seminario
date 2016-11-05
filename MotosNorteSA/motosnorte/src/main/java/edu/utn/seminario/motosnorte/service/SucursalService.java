package edu.utn.seminario.motosnorte.service;

import java.io.Serializable;
import java.util.List;

import edu.utn.seminario.motosnorte.dao.RolDao;
import edu.utn.seminario.motosnorte.dao.SucursalDao;
import edu.utn.seminario.motosnorte.dao.UsuarioDao;
import edu.utn.seminario.motosnorte.domain.Rol;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.exception.SucursalNoEncontradaException;
import edu.utn.seminario.motosnorte.exception.SucursalYaExistenteException;
import edu.utn.seminario.motosnorte.exception.UsuarioNoEncontradoException;
import edu.utn.seminario.motosnorte.exception.UsuarioOContraseñaIncorrectoException;
import edu.utn.seminario.motosnorte.exception.UsuarioYaExistenteException;

public class SucursalService implements Serializable{
	
	private SucursalDao dao;
	
	public SucursalService()
	{
		if(this.dao == null){
			dao = new SucursalDao();
		}
	}
	
//	@SuppressWarnings({ "finally", "unchecked" })
//	public Object login(String user, String pass) throws UsuarioOContraseñaIncorrectoException, Exception{
//		return dao.login(user, pass);
//	}
	
	public void guardar(Sucursal sucursal) throws SucursalYaExistenteException,Exception{
//		if(dao.existe(sucursal))
//			throw new SucursalYaExistenteException("La sucursal ya existe, por favor ingrese otra.");
		dao.guardar(sucursal);
	}
	
	public List<Sucursal> listar() {
		return dao.listar();
	}
	
	public Sucursal getById(Integer sucursal) throws SucursalNoEncontradaException, Exception{
		try{
			return dao.getById(sucursal);
		}catch(SucursalNoEncontradaException e){
			throw e;
		}
		catch(Exception e){
			throw e;
		}
	}

	public void modificar(Sucursal sucursal) throws Exception{
		dao.modificar(sucursal);
	}

	public void eliminar(Sucursal s) throws Exception {
		dao.eliminar(s);
	}
}
