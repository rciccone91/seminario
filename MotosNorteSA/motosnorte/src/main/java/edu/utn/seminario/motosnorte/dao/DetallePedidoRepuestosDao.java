package edu.utn.seminario.motosnorte.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.Cliente;
import edu.utn.seminario.motosnorte.domain.DetallePedidoRepuestos;

public class DetallePedidoRepuestosDao {
	
	private SessionFactory sessionFactory;
	private  Session session;

	public DetallePedidoRepuestosDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}
	
	public void guardar(DetallePedidoRepuestos det) throws Exception{
		DataLayer data = new DataLayer();
		data.guardar(det);
	}
}
