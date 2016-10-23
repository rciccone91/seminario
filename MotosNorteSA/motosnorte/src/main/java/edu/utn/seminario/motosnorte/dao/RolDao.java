package edu.utn.seminario.motosnorte.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.Rol;

public class RolDao {
	
	private SessionFactory sessionFactory;
	private  Session session;

	public RolDao()
	{
		if(this.sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			this.session = sessionFactory.openSession();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Rol> listar(){
		DataLayer data = new DataLayer();
		return (List<Rol>)(List<?>)data.list(Rol.class);
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public Rol getById(Integer id){
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Rol where id = :rolId");
			query.setInteger("rolId", id);
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return (Rol)lista.get(0);
		}
	}

}
