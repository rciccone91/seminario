package edu.utn.seminario.motosnorte.service;

import java.io.Serializable;
import java.util.List;

import edu.utn.seminario.motosnorte.dao.RolDao;
import edu.utn.seminario.motosnorte.dao.UsuarioDao;
import edu.utn.seminario.motosnorte.domain.Rol;
import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.exception.UsuarioNoEncontradoException;
import edu.utn.seminario.motosnorte.exception.UsuarioOContraseñaIncorrectoException;
import edu.utn.seminario.motosnorte.exception.UsuarioYaExistenteException;

public class UsuarioService implements Serializable{
	
	private UsuarioDao dao;
	
	public UsuarioService()
	{
		if(this.dao == null){
			dao = new UsuarioDao();
		}
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public Object login(String user, String pass) throws UsuarioOContraseñaIncorrectoException, Exception{
		return dao.login(user, pass);
	}
	
	public void guardar(Usuario usuario) throws UsuarioYaExistenteException,Exception{
		if(dao.existe(usuario))
			throw new UsuarioYaExistenteException("El nombre de usuario ya existe, por favor ingrese otro.");
		dao.guardar(usuario);
	}
	
	public List<Usuario> listar() {
		return dao.listar();
	}
	
	public Usuario getById(String usuario) throws UsuarioNoEncontradoException {
		return dao.getById(usuario);
	}

	public void modificar(Usuario usuario) throws Exception{
		dao.modificar(usuario);
	}

	public void eliminar(Usuario u) throws Exception {
		dao.eliminar(u);
	}
}
