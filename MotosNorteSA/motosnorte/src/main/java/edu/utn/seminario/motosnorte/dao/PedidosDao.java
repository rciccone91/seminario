package edu.utn.seminario.motosnorte.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.CategoriaMoto;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.Pedido;
import edu.utn.seminario.motosnorte.helper.SessionFactoryHelper;

public class PedidosDao {

	private SessionFactory sessionFactory;
	private  Session session;

	public PedidosDao(){
		if(sessionFactory == null){
			sessionFactory = SessionFactoryHelper.getInstance();
			session = sessionFactory.openSession();
		}
	}

	@SuppressWarnings({ "finally", "unchecked" })
	public List<Pedido> getPedidosRemotosByEstado(Integer estado) {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Pedido> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Pedido where estado =:estado");
			query.setInteger("estado", estado);
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return lista;
		}
	}

	public Pedido guardar(Pedido ped) throws Exception {
		DataLayer data = new DataLayer();
		Pedido p = (Pedido)data.guardar(ped);
		return p;
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Pedido> listar() {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Pedido order by pedido_id");
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (List<Pedido>)(List<?>)lista;
		}
	}

	@SuppressWarnings({ "finally", "unchecked" })
	public List<Pedido> listarFilterById(Integer idPed) {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Pedido> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Pedido where pedido_id =:id");
			query.setInteger("id", idPed);
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return lista;
		}
	}
}
