package edu.utn.seminario.motosnorte.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.domain.Rol;

public class MarcaDao {
	
	private SessionFactory sessionFactory;
	private  Session session;

	public MarcaDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Marca> listar(){
		DataLayer data = new DataLayer();
		return (List<Marca>)(List<?>)data.list(Marca.class);
	}

	@SuppressWarnings({ "finally", "unchecked" })
	public Marca getById(int id) {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Marca where marca_id = :marcaID");
			query.setInteger("marcaID", id);
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (Marca)lista.get(0);
		}
	}
}
