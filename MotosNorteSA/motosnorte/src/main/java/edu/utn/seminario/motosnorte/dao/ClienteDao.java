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
import edu.utn.seminario.motosnorte.domain.Cliente;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.exception.ClienteNoEncontradoException;
import edu.utn.seminario.motosnorte.exception.UsuarioNoEncontradoException;

public class ClienteDao implements Serializable{

	private SessionFactory sessionFactory;
	private  Session session;

	public ClienteDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}
	
	public void guardar(Cliente cliente) throws Exception{
		DataLayer data = new DataLayer();
		data.guardar(cliente);
	}

	@SuppressWarnings("unchecked")
	public Boolean existe(Cliente cliente) throws Exception{
		List<Object> lista = new ArrayList<Object>();
		try {
			Query query = session.createQuery("from Cliente as c where c.dni = :doc");
			query.setString("doc",cliente.getDni());
			lista = query.list();

			return !lista.isEmpty();
		}
		catch (Exception e) {
			throw new Exception("Ocurri� un error, por favor comunicarse con el administrador");
		}

	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Cliente> listar(){
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Cliente as c order by c.apellido");
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return (List<Cliente>)(List<?>)lista;
		}
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Cliente> listarActivos(){
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Cliente as c where c.active=:true order by c.apellido");
			query.setBoolean("true", Boolean.TRUE);
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return (List<Cliente>)(List<?>)lista;
		}
	}

	@SuppressWarnings("unchecked")
	public Cliente getById(Integer id) throws ClienteNoEncontradoException {
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Cliente as c where c.id = :id");
			query.setInteger("id",id);
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!lista.isEmpty()){
			return (Cliente)lista.get(0);
		}
		else{
			throw new ClienteNoEncontradoException("El Cliente requerido no existe.");
		}

	}

	public void modificar(Cliente cliente) throws Exception {
		DataLayer data = new DataLayer();
		data.modificar(cliente);
	}

	
	public void eliminar(Cliente c) throws Exception {
		Transaction tx = session.getTransaction();
		try {
			Query query = session.createQuery("update Cliente set active =:false "
					+ "where id = :clienteId");
			query.setBoolean("false", Boolean.FALSE);
			query.setString("clienteId", c.getId().toString());
			tx.begin();
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
}
