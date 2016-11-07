package edu.utn.seminario.motosnorte.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.domain.StockRepuestos;
import edu.utn.seminario.motosnorte.domain.Sucursal;

public class StockRepuestosDao {

	private SessionFactory sessionFactory;
	private  Session session;

	public StockRepuestosDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean existe(Repuesto repuesto, Sucursal sucursal) throws Exception {
		List<Object> lista = new ArrayList<Object>();
		try {
			Query query = session.createQuery("from StockRepuestos where repuesto_id = :repuestoId"
					+ " and sucursal_id =:sucursalID");
			query.setInteger("repuestoId", repuesto.getId());
			query.setInteger("sucursalID",sucursal.getId());
			lista = query.list();
			return !lista.isEmpty();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Ocurrió un error, por favor comunicarse con el administrador");
		}
	}

	public void actualizar(Repuesto repuesto, Sucursal sucursal, Integer cantidad) throws Exception {
		Transaction tx = session.getTransaction();
		try {
			Query query = session.createSQLQuery("update StockRepuestos set cantidad=cantidad+:cant where repuesto_id =:repuestoId"
					+ " and sucursal_id =:sucursalID");
			query.setInteger("cant", cantidad);
			query.setInteger("repuestoId", repuesto.getId());
			query.setInteger("sucursalID",sucursal.getId());
			tx.begin();
			query.executeUpdate();
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new Exception("Ocurrió un error, por favor comunicarse con el administrador");
		}
	}

	public void guardar(StockRepuestos stock) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(stock);
	}

}
