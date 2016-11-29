package edu.utn.seminario.motosnorte.datalayer;

import java.io.Serializable;
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

	public Object guardar(Object t) throws Exception {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.save(t);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			session.close();
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} 
		finally {
			session.close();
		}
		return t;
	}

	public void modificar(Object t) throws Exception {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.update(t);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			session.close();
			throw new Exception(e.getMessage());
		} 
		finally {
			session.close();
		}
	}

	public void eliminar(Object t) throws Exception {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.delete(t);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			session.close();
			throw new Exception(e.getMessage());
		} 
		finally {
			session.close();
		}
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public List<Object> list(Class clazz){
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<Object>();
		try {
			lista = session.createQuery("from edu.utn.seminario.motosnorte.domain."+clazz.getSimpleName()).list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return lista;
		}
	}
}
