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
			throw new Exception("Ocurrió un error, por favor comunicarse con el administrador");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listar(){
		DataLayer data = new DataLayer();
		return (List<Cliente>)(List<?>)data.list(Cliente.class);
	}

	@SuppressWarnings("unchecked")
	public Cliente getById(String id) throws ClienteNoEncontradoException {
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Cliente as c where c.id = :id");
			query.setInteger("id",Integer.parseInt(id));
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
			Query query = session.createQuery("update Cliente as c set active =:false "
					+ "where c.cliente_id = :clienteId");
			query.setBoolean("false", Boolean.FALSE);
			query.setString("clienteId", c.getId().toString());
			tx.begin();
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new Exception(e.getMessage());
		}
	}
}
