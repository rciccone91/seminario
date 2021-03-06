package edu.utn.seminario.motosnorte.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.CategoriaMoto;

public class CategoriaMotoDao {
	
	private SessionFactory sessionFactory;
	private  Session session;

	public CategoriaMotoDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CategoriaMoto> listar(){
		DataLayer data = new DataLayer();
		return (List<CategoriaMoto>)(List<?>)data.list(CategoriaMoto.class);
	}


	@SuppressWarnings({ "finally", "unchecked" })
	public CategoriaMoto getById(Integer id) {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from CategoriaMoto where categoriamoto_id = :id");
			query.setInteger("id", id);
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (CategoriaMoto)lista.get(0);
		}
	}
}
