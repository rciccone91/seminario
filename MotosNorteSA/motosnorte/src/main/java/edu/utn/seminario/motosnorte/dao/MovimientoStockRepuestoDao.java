package edu.utn.seminario.motosnorte.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.MovimientoStockRepuesto;

public class MovimientoStockRepuestoDao {
	
	private SessionFactory sessionFactory;
	private  Session session;

	public MovimientoStockRepuestoDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}

	public void guardar(MovimientoStockRepuesto mov) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(mov);
	}

}
