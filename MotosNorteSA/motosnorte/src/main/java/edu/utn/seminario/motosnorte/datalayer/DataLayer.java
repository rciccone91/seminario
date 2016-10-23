package edu.utn.seminario.motosnorte.datalayer;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DataLayer {
	

	private SessionFactory sessionFactory;
	private  Session session;
	
	public DataLayer()
	{
		if(this.sessionFactory == null){
			sessionFactory = new Configuration().configure()
						.buildSessionFactory();
				this.session = sessionFactory.openSession();
		}
	}

	public void guardar(Object t) throws Exception {
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.save(t);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new Exception(e.getMessage());
		} 
	}

	public void modificar(Object t) throws Exception {
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.update(t);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new Exception(e.getMessage());
		} 
	}

	public void eliminar(Object t) throws Exception {
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.delete(t);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new Exception(e.getMessage());
		} 
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public List<Object> list(Class clazz){
		List<Object> lista = new ArrayList<Object>();
		try {
			lista = session.createQuery("from edu.utn.seminario.motosnorte.domain."+clazz.getSimpleName()).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return lista;
		}
	}
}
