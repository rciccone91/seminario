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
import edu.utn.seminario.motosnorte.exception.NoSePuedeRegistrarSalidaDeStockException;
import edu.utn.seminario.motosnorte.helper.SessionFactoryHelper;

public class StockMotosDao {

	private SessionFactory sessionFactory;
	private  Session session;

	public StockMotosDao(){
		if(sessionFactory == null){
			sessionFactory = SessionFactoryHelper.getInstance();
			session = sessionFactory.openSession();
		}
	}

	public void guardar(StockMotos s) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(s);
	}

	@SuppressWarnings("unchecked")
	public Boolean existe(Moto m, Sucursal s) throws Exception{
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<Object>();
		try {
			Query query = session.createQuery("from StockMotos where moto_id = :motoId"
					+ " and sucursal_id =:sucursalID");
			query.setInteger("motoId", m.getId());
			query.setInteger("sucursalID",s.getId());
			lista = query.list();
			session.close();
			return !lista.isEmpty();
		}
		catch (Exception e) {
			e.printStackTrace();
			session.close();
			throw new Exception("Ocurri� un error, por favor comunicarse con el administrador");
		}
	}


	public void actualizar(Moto m, Sucursal s, int cantidad) throws Exception{
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
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
			session.close();
			throw new Exception("Ocurri� un error, por favor comunicarse con el administrador");
		}
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<StockMotos> listar() {
		DataLayer data = new DataLayer();
		return (List<StockMotos>)(List<?>) data.list(StockMotos.class);
	}

	public boolean validarStockMoto(Integer cantidadMoto, Moto moto, Sucursal sucursal) throws NoHayStockSuficienteException,Exception {
		try {
			if(!session.isOpen()){
				session = sessionFactory.openSession();
			}
			Query query = session.createQuery("from StockMotos as sm where sm.moto.id = :moto "
					+ "and sm.sucursal.id = :sucursal");
			query.setInteger("moto", moto.getId());
			query.setInteger("sucursal", sucursal.getId());
			StockMotos stock = (StockMotos) query.uniqueResult();

			if(stock == null || stock.getCantidad() < cantidadMoto){
				throw new NoHayStockSuficienteException();
			}
		}catch (NoHayStockSuficienteException e) {
			session.close();
			throw new NoHayStockSuficienteException("No hay stock suficiente de la moto en la sucursal para cubrir la cantidad requerida.");
		} 
		catch (Exception e) {
			session.close();
			throw new Exception("Ocurri� un error al intentar ingresar, por favor comunicarse con el administrador");
		}
		session.close();
		return true;
	}

	public boolean validarCantidadSalida(Moto moto, Sucursal sucursal, Integer cantidad) throws NoSePuedeRegistrarSalidaDeStockException, Exception {
		try {
			if(!session.isOpen()){
				session = sessionFactory.openSession();
			}
			Query query = session.createQuery("from StockMotos as sm where sm.moto.id = :moto "
					+ "and sm.sucursal.id = :sucursal");
			query.setInteger("moto", moto.getId());
			query.setInteger("sucursal", sucursal.getId());
			StockMotos stock = (StockMotos) query.uniqueResult();

			if(stock.getCantidad() < cantidad){
				throw new NoHayStockSuficienteException();
			}
		}catch (NoHayStockSuficienteException e) {
			session.close();
			throw new NoSePuedeRegistrarSalidaDeStockException("La cantidad requerida a salir es mayor a la cantidad que hay en stock");
		} 
		catch (Exception e) {
			session.close();
			throw new Exception("Ocurri� un error al intentar ingresar, por favor comunicarse con el administrador");
		}
		session.close();
		return true;
	}

	public Integer getStockByMotoAndSucursal(Moto moto, Sucursal sucursal) throws Exception {
		StockMotos stock = null;
		try {
			if(!session.isOpen()){
				session = sessionFactory.openSession();
			}
			Query query = session.createQuery("from StockMotos as sm where sm.moto.id = :moto "
					+ "and sm.sucursal.id = :sucursal");
			query.setInteger("moto", moto.getId());
			query.setInteger("sucursal", sucursal.getId());
			stock = (StockMotos) query.uniqueResult();
		}catch (Exception e) {
			session.close();
			e.printStackTrace();
			throw new Exception("Ocurri� un error al intentar consultar el stock, por favor comunicarse con el administrador");
		}
		session.close();
		
		if(stock != null){
			return stock.getCantidad();
		}else{
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<StockMotos> getStockByMoto(Moto moto) throws Exception {
		List<StockMotos> stock = null;
		try {
			if(!session.isOpen()){
				session = sessionFactory.openSession();
			}
			Query query = session.createQuery("from StockMotos as sm where sm.moto.id = :moto");
			query.setInteger("moto", moto.getId());
			stock = (List<StockMotos>)(List<?>) query.list();
		}catch (Exception e) {
			session.close();
			e.printStackTrace();
			throw new Exception("Ocurri� un error al intentar consultar el stock, por favor comunicarse con el administrador");
		}
		session.close();
		return stock;
	}
}
