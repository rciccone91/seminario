package edu.utn.seminario.motosnorte.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.MovimientoStockMoto;
import edu.utn.seminario.motosnorte.helper.SessionFactoryHelper;

public class MovimientoStockMotosDao {

	private SessionFactory sessionFactory;
	private  Session session;

	public MovimientoStockMotosDao(){
		if(sessionFactory == null){
			sessionFactory = SessionFactoryHelper.getInstance();
			session = sessionFactory.openSession();
		}
	}

	public void guardar(MovimientoStockMoto mov) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(mov);
	}

}
