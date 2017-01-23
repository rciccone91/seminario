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
import edu.utn.seminario.motosnorte.domain.CategoriaRepuesto;
import edu.utn.seminario.motosnorte.domain.DetallePedidoRepuestos;
import edu.utn.seminario.motosnorte.domain.RepuestosMasVendidos;
import edu.utn.seminario.motosnorte.helper.SessionFactoryHelper;

public class DetallePedidoRepuestosDao {
	
	private SessionFactory sessionFactory;
	private  Session session;
	private RepuestosDao repuestoDao;

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

	@SuppressWarnings("finally")
	public List<RepuestosMasVendidos> getRepuestosMasVendidos(Date fechaDesde, Date fechaHasta,
			CategoriaRepuesto categoriaRepuesto) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
		List<RepuestosMasVendidos> masVendidos = new ArrayList<RepuestosMasVendidos>();
		try {
			StringBuilder queryString = new StringBuilder("select sum(dp.cantidad), dp.repuesto_id"+
					" from motosnorte.detallepedidorepuestos as dp"+
					" inner join motosnorte.pedidos as p"+
					" on dp.pedido_id = p.pedido_id and DATE_FORMAT(p.fecha,'%Y/%m/%d') between '"+formatter.format(fechaDesde)+"'"+
					" and '"+formatter.format(fechaHasta)+"'"+
					" inner join motosnorte.repuestos as r"+
					" on dp.repuesto_id = r.repuesto_id"
					);
			
			if(categoriaRepuesto != null){
				queryString.append(" and r.categoriarepuesto_id = "+categoriaRepuesto.getId());
			}
			
			queryString.append(" group by dp.repuesto_id");
					
			
			Query query = session.createSQLQuery(queryString.toString());
			List<Object> lista = query.list();
			Iterator<Object> itr = lista.iterator();
			String entreFechas = formatter2.format(fechaDesde)+ " - " + formatter2.format(fechaHasta);
			
			while(itr.hasNext()){
				Object[] obj = (Object[]) itr.next();
				Integer cantidad = Integer.valueOf(String.valueOf(obj[0]));
				Integer repuestoId =  Integer.valueOf(String.valueOf(obj[1]));
				masVendidos.add(new RepuestosMasVendidos(new RepuestosDao().getById(repuestoId),cantidad,entreFechas));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return masVendidos;
		}
		
	}

	
}
