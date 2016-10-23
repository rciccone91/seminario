package edu.utn.seminario.motosnorte.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.Rol;
import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.exception.UsuarioNoEncontradoException;
import edu.utn.seminario.motosnorte.exception.UsuarioOContraseñaIncorrectoException;

public class UsuarioDao implements Serializable{

	private SessionFactory sessionFactory;
	private  Session session;

	public UsuarioDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}

	@SuppressWarnings({ "finally", "unchecked" })
	public Object login(String user, String pass) throws UsuarioOContraseñaIncorrectoException,Exception{
		List<Object> lista = new ArrayList<Object>();
		try {
			Query query = session.createQuery("from Usuario as U where U.usuario = :user "
					+ "and U.contrasenia = :pass and activo = :activo");
			query.setString("user",user);
			query.setString("pass",pass);
			query.setBoolean("activo", true);
			lista = query.list();

			if(lista.isEmpty()){
				throw new UsuarioOContraseñaIncorrectoException();
			}
			else{
				return lista.get(0);
			}
		}catch (UsuarioOContraseñaIncorrectoException e) {
			throw new UsuarioOContraseñaIncorrectoException("El usuario o "
					+ "la contraseña es incorrecta, por favor ingrese las credenciales correctas");
		} 
		catch (Exception e) {
			throw new Exception("Ocurrió un error al intentar ingresar, por favor comunicarse con el administrador");
		}
	}

	public void guardar(Usuario usuario) throws Exception{
		DataLayer data = new DataLayer();
		data.guardar(usuario);
	}

	@SuppressWarnings("unchecked")
	public Boolean existe(Usuario usuario) throws Exception{
		List<Object> lista = new ArrayList<Object>();
		try {
			Query query = session.createQuery("from Usuario as U where U.usuario = :user");
			query.setString("user",usuario.getUsuario());
			lista = query.list();

			return !lista.isEmpty();
		}
		catch (Exception e) {
			throw new Exception("Ocurrió un error, por favor comunicarse con el administrador");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listar(){
		DataLayer data = new DataLayer();
		return (List<Usuario>)(List<?>)data.list(Usuario.class);
	}

	public Usuario getById(String id) throws UsuarioNoEncontradoException {
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Usuario where usuario = :usuarioId");
			query.setString("usuarioId", id);
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!lista.isEmpty()){
			return (Usuario)lista.get(0);
		}
		else{
			throw new UsuarioNoEncontradoException("El usuario requerido no existe.");
		}

	}

	public void modificar(Usuario usuario) throws Exception {
		DataLayer data = new DataLayer();
		data.modificar(usuario);
	}

	public void eliminar(Usuario u) throws Exception {
		Transaction tx = session.getTransaction();
		try {
			Query query = session.createQuery("update Usuario set activo =:false "
					+ "where usuario_id = :usuarioId");
			query.setBoolean("false", Boolean.FALSE);
			query.setString("usuarioId", u.getUsuario());
			tx.begin();
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new Exception(e.getMessage());
		}
	}

}
