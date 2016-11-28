package edu.utn.seminario.motosnorte.service;

import java.io.Serializable;
import java.util.List;

import edu.utn.seminario.motosnorte.dao.RolDao;
import edu.utn.seminario.motosnorte.dao.SucursalDao;
import edu.utn.seminario.motosnorte.dao.UsuarioDao;
import edu.utn.seminario.motosnorte.domain.Rol;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.exception.DescripcionDeSucursalYaExistenteException;
import edu.utn.seminario.motosnorte.exception.DireccionDeSucursalYaExistenteException;
import edu.utn.seminario.motosnorte.exception.SucursalNoEncontradaException;
import edu.utn.seminario.motosnorte.exception.SucursalYaExistenteException;
import edu.utn.seminario.motosnorte.exception.UsuarioNoEncontradoException;
import edu.utn.seminario.motosnorte.exception.UsuarioOContrase�aIncorrectoException;
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
//	public Object login(String user, String pass) throws UsuarioOContrase�aIncorrectoException, Exception{
//		return dao.login(user, pass);
//	}
	
	public void guardar(Sucursal sucursal) throws Exception{
//		if(dao.existe(sucursal))
//			throw new SucursalYaExistenteException("La sucursal ya existe, por favor ingrese otra.");
		if(dao.existeOtraSucursalConMismaDescripcion(sucursal))
			throw new DescripcionDeSucursalYaExistenteException("Ya existe otra sucursal con la descripci�n: "+sucursal.getDescripcion()+", por favor ingrese otra.");
		if(dao.existeOtraSucursalConMismaDireccion(sucursal))
			throw new DireccionDeSucursalYaExistenteException("Ya existe otra sucursal con la direcci�n: "+sucursal.getDireccion()+", por favor ingrese otra.");
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
		if(dao.existeOtraSucursalConMismaDescripcion(sucursal))
			throw new DescripcionDeSucursalYaExistenteException("Ya existe otra sucursal con la descripci�n: "+sucursal.getDescripcion()+", por favor ingrese otra.");
		if(dao.existeOtraSucursalConMismaDireccion(sucursal))
			throw new DireccionDeSucursalYaExistenteException("Ya existe otra sucursal con la direcci�n: "+sucursal.getDireccion()+", por favor ingrese otra.");
		dao.modificar(sucursal);
	}

	public void eliminar(Sucursal s) throws Exception {
		dao.eliminar(s);
	}
}
