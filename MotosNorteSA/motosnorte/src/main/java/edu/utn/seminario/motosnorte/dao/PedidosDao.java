package edu.utn.seminario.motosnorte.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.CategoriaMoto;
import edu.utn.seminario.motosnorte.domain.Pedido;

public class PedidosDao {

	private SessionFactory sessionFactory;
	private  Session session;

	public PedidosDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}

	@SuppressWarnings({ "finally", "unchecked" })
	public List<Pedido> getPedidosRemotosByEstado(Integer estado) {
		List<Pedido> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Pedido where estado =:estado");
			query.setInteger("estado", estado);
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return lista;
		}
	}

	public void guardar(Pedido ped) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(ped);
	}
}
