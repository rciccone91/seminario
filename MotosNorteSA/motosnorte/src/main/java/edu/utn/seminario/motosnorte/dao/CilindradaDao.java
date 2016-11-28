package edu.utn.seminario.motosnorte.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.Cilindrada;
import edu.utn.seminario.motosnorte.domain.Marca;


public class CilindradaDao {
	
	private SessionFactory sessionFactory;
	private Session session;

	public CilindradaDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public Cilindrada getById(Integer id) {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Cilindrada where cilindrada_id = :id");
			query.setInteger("id", id);
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (Cilindrada)lista.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cilindrada> listar() {
		DataLayer data = new DataLayer();
		return (List<Cilindrada>)(List<?>)data.list(Cilindrada.class);
	}

}
