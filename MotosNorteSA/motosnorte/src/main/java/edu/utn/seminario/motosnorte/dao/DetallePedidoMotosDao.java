package edu.utn.seminario.motosnorte.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.DetallePedidoMotos;

public class DetallePedidoMotosDao {
	
	private SessionFactory sessionFactory;
	private  Session session;

	public DetallePedidoMotosDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}

	public void guardar(DetallePedidoMotos det) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(det);
	}

}
