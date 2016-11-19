package edu.utn.seminario.motosnorte.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.StockMotos;
import edu.utn.seminario.motosnorte.domain.StockRepuestos;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.exception.NoHayStockSuficienteException;

public class StockMotosDao {

	private SessionFactory sessionFactory;
	private  Session session;

	public StockMotosDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}

	public void guardar(StockMotos s) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(s);
	}

	@SuppressWarnings("unchecked")
	public Boolean existe(Moto m, Sucursal s) throws Exception{
		List<Object> lista = new ArrayList<Object>();
		try {
			Query query = session.createQuery("from StockMotos where moto_id = :motoId"
					+ " and sucursal_id =:sucursalID");
			query.setInteger("motoId", m.getId());
			query.setInteger("sucursalID",s.getId());
			lista = query.list();
			return !lista.isEmpty();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Ocurrió un error, por favor comunicarse con el administrador");
		}
	}


	public void actualizar(Moto m, Sucursal s, int cantidad) throws Exception{
		Transaction tx = session.getTransaction();
		try {
			Query query = session.createSQLQuery("update StockMotos set cantidad=cantidad+:cant where moto_id =:motoId"
					+ " and sucursal_id =:sucursalID");
			query.setInteger("cant", cantidad);
			query.setInteger("motoId", m.getId());
			query.setInteger("sucursalID",s.getId());
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

	@SuppressWarnings("unchecked")
	public List<StockMotos> listar() {
		DataLayer data = new DataLayer();
		return (List<StockMotos>)(List<?>) data.list(StockMotos.class);
	}

	public boolean validarStockMoto(Integer cantidadMoto, Moto moto, Sucursal sucursal) throws NoHayStockSuficienteException,Exception {
		try {
			Query query = session.createQuery("from StockMotos as sm where sm.moto.id = :moto "
					+ "and sm.sucursal.id = :sucursal");
			query.setInteger("moto", moto.getId());
			query.setInteger("sucursal", sucursal.getId());
			StockMotos stock = (StockMotos) query.uniqueResult();

			if(stock == null || stock.getCantidad() < cantidadMoto){
				throw new NoHayStockSuficienteException();
			}
		}catch (NoHayStockSuficienteException e) {
			throw new NoHayStockSuficienteException("No hay stock suficiente de la moto en la sucursal para cubrir la cantidad requerida.");
		} 
		catch (Exception e) {
			throw new Exception("Ocurrió un error al intentar ingresar, por favor comunicarse con el administrador");
		}

		return true;
	}
}
