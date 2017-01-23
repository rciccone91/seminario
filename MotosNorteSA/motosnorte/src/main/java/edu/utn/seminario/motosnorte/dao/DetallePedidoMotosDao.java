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
import edu.utn.seminario.motosnorte.domain.DetallePedidoMotos;
import edu.utn.seminario.motosnorte.domain.Pedido;
import edu.utn.seminario.motosnorte.helper.SessionFactoryHelper;
import edu.utn.seminario.motosnorte.transferobject.MotosMasVendidas;

public class DetallePedidoMotosDao {
	
	private SessionFactory sessionFactory;
	private  Session session;
	private MotoDao motoDao;

	
	public DetallePedidoMotosDao(){
		motoDao = new MotoDao();
		if(sessionFactory == null){
			sessionFactory = SessionFactoryHelper.getInstance();
			session = sessionFactory.openSession();
		}
	}

	public void guardar(DetallePedidoMotos det) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(det);
	}

	@SuppressWarnings({ "finally", "unchecked" })
	public List<DetallePedidoMotos> listarFilterByPedidoId(Integer idPed) {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<DetallePedidoMotos> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from DetallePedidoMotos where pedido_id =:id");
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
	public List<MotosMasVendidas> getMotosMasVendidas(Date fechaDesde, Date fechaHasta, CategoriaMoto categoriaMoto) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
		List<MotosMasVendidas> masVendidas = new ArrayList<MotosMasVendidas>();
		try {
			StringBuilder queryString = new StringBuilder("select sum(dp.cantidad), dp.moto_id"+
					" from motosnorte.detallepedidomotos as dp"+
					" inner join motosnorte.pedidos as p"+
					" on dp.pedido_id = p.pedido_id and DATE_FORMAT(p.fecha,'%Y/%m/%d') between '"+formatter.format(fechaDesde)+"'"+
					" and '"+formatter.format(fechaHasta)+"'"+
					" inner join motosnorte.motos as m"+
					" on dp.moto_id = m.moto_id"
					);
			
			if(categoriaMoto != null){
				queryString.append(" and m.categoriamoto_id = "+categoriaMoto.getId());
			}
			
			queryString.append(" group by dp.moto_id");
					
			
			Query query = session.createSQLQuery(queryString.toString());
			List<Object> lista = query.list();
			Iterator<Object> itr = lista.iterator();
			String entreFechas = formatter2.format(fechaDesde)+ " - " + formatter2.format(fechaHasta);
			
			while(itr.hasNext()){
				Object[] obj = (Object[]) itr.next();
				Integer cantidad = Integer.valueOf(String.valueOf(obj[0]));
				Integer motoId =  Integer.valueOf(String.valueOf(obj[1]));
				masVendidas.add(new MotosMasVendidas(new MotoDao().getById(motoId),cantidad,entreFechas));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return masVendidas;
		}
	}

}
