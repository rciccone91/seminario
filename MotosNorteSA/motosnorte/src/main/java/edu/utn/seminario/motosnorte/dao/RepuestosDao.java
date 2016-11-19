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
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.domain.Usuario;

public class RepuestosDao {
	
	private SessionFactory sessionFactory;
	private  Session session;

	public RepuestosDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}
	
	public void modificar(Repuesto repuesto) throws Exception {
		DataLayer data = new DataLayer();
		data.modificar(repuesto);
	}

	public void guardar(Repuesto repuesto) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(repuesto);
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public Repuesto getById(Integer id) {
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Repuesto where repuesto_id = :id");
			query.setInteger("id", id);
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return (Repuesto)lista.get(0);
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Repuesto> listar() {
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Repuesto as r order by r.modelo");
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return (List<Repuesto>)(List<?>)lista;
		}
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Repuesto> listarActivos() {
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Repuesto as r where r.activo=:true order by r.modelo");
			query.setBoolean("true", Boolean.TRUE);
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return (List<Repuesto>)(List<?>)lista;
		}
	}

	public void eliminar(Integer id) throws Exception {
		Transaction tx = session.getTransaction();
		try {
			Query query = session.createQuery("update Repuesto set activo =:false "
					+ "where repuesto_id = :id");
			query.setBoolean("false", Boolean.FALSE);
			query.setInteger("id", id);
			tx.begin();
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new Exception(e.getMessage());
		}
	}

}
