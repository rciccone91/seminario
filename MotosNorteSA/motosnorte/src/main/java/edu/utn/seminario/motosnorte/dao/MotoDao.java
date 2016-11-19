package edu.utn.seminario.motosnorte.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.domain.Moto;

public class MotoDao {
	
	private SessionFactory sessionFactory;
	private  Session session;

	public MotoDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Moto> listar(){
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Moto as m order by m.modelo");
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return (List<Moto>)(List<?>)lista;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Moto> listarActivos(){
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Moto as m where m.activo =:true order by m.modelo");
			query.setBoolean("true", Boolean.TRUE);
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return (List<Moto>)(List<?>)lista;
		}
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public Moto getById(Integer id) {
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Moto where moto_id = :id");
			query.setInteger("id", id);
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return (Moto)lista.get(0);
		}
	}

	public void guardar(Moto moto) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(moto);
	}

	public void modificar(Moto moto) throws Exception {
		DataLayer data = new DataLayer();
		data.modificar(moto);
	}

	public void eliminar(Moto m) throws Exception {
		Transaction tx = session.getTransaction();
		try {
			Query query = session.createQuery("update Moto set activo =:false "
					+ "where moto_id = :id");
			query.setBoolean("false", Boolean.FALSE);
			query.setInteger("id", m.getId());
			tx.begin();
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new Exception(e.getMessage());
		}
	}

}
