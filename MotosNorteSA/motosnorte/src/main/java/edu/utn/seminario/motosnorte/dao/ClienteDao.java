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
import edu.utn.seminario.motosnorte.helper.SessionFactoryHelper;

public class ClienteDao implements Serializable{

	private SessionFactory sessionFactory;
	private  Session session;

	public ClienteDao(){
		if(sessionFactory == null){
			sessionFactory = SessionFactoryHelper.getInstance();
			session = sessionFactory.openSession();
		}
	}
	
	public void guardar(Cliente cliente) throws Exception{
		DataLayer data = new DataLayer();
		data.guardar(cliente);
	}

	@SuppressWarnings("unchecked")
	public Boolean existe(Cliente cliente) throws Exception{
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<Object>();
		try {
			Query query = session.createQuery("from Cliente as c where c.dni = :doc");
			query.setString("doc",cliente.getDni());
			lista = query.list();
			session.close();
			return !lista.isEmpty();
		}
		catch (Exception e) {
			session.close();
			throw new Exception("Ocurrió un error, por favor comunicarse con el administrador");
		}

	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Cliente> listar(){
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Cliente as c order by c.apellido");
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (List<Cliente>)(List<?>)lista;
		}
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Cliente> listarActivos(){
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Cliente as c where c.active=:true order by c.apellido");
			query.setBoolean("true", Boolean.TRUE);
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (List<Cliente>)(List<?>)lista;
		}
	}

	@SuppressWarnings("unchecked")
	public Cliente getById(Integer id) throws ClienteNoEncontradoException {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Cliente as c where c.id = :id");
			query.setInteger("id",id);
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		if(!lista.isEmpty()){
			session.close();
			return (Cliente)lista.get(0);
		}
		else{
			session.close();
			throw new ClienteNoEncontradoException("El Cliente requerido no existe.");
		}

	}

	public void modificar(Cliente cliente) throws Exception {
		DataLayer data = new DataLayer();
		data.modificar(cliente);
	}

	
	public void eliminar(Cliente c) throws Exception {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
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
			session.close();
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		session.close();
	}
}
