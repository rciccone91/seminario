package edu.utn.seminario.motosnorte.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.CategoriaMoto;
import edu.utn.seminario.motosnorte.domain.Cliente;
import edu.utn.seminario.motosnorte.domain.DetallePedidoRepuestos;
import edu.utn.seminario.motosnorte.helper.SessionFactoryHelper;
import edu.utn.seminario.motosnorte.transferobject.MotosMasVendidas;

public class DetallePedidoRepuestosDao {
	
	private SessionFactory sessionFactory;
	private  Session session;

	public DetallePedidoRepuestosDao(){
		if(sessionFactory == null){
			sessionFactory = SessionFactoryHelper.getInstance();
			session = sessionFactory.openSession();
		}
	}
	
	public void guardar(DetallePedidoRepuestos det) throws Exception{
		DataLayer data = new DataLayer();
		data.guardar(det);
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public List<DetallePedidoRepuestos> listarFilterByPedidoId(Integer idPed) {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<DetallePedidoRepuestos> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from DetallePedidoRepuestos where pedido_id =:id");
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
