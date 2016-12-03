package edu.utn.seminario.motosnorte.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.CategoriaRepuesto;
import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.helper.SessionFactoryHelper;

public class CategoriaRepuestoDao {
	
	private SessionFactory sessionFactory;
	private  Session session;

	public CategoriaRepuestoDao(){
		if(sessionFactory == null){
			sessionFactory = SessionFactoryHelper.getInstance();
			session = sessionFactory.openSession();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CategoriaRepuesto> listar(){
		DataLayer data = new DataLayer();
		return (List<CategoriaRepuesto>)(List<?>)data.list(CategoriaRepuesto.class);
	}


	@SuppressWarnings({ "finally", "unchecked" })
	public CategoriaRepuesto getById(Integer id) {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from CategoriaRepuesto where categoriarepuesto_id = :id");
			query.setInteger("id", id);
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (CategoriaRepuesto)lista.get(0);
		}
	}
}
