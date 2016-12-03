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
import edu.utn.seminario.motosnorte.domain.StockMotos;
import edu.utn.seminario.motosnorte.domain.StockRepuestos;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.exception.NoHayStockSuficienteException;
import edu.utn.seminario.motosnorte.exception.NoSePuedeRegistrarSalidaDeStockException;
import edu.utn.seminario.motosnorte.exception.UsuarioOContraseñaIncorrectoException;
import edu.utn.seminario.motosnorte.helper.SessionFactoryHelper;

public class StockRepuestosDao {

	private SessionFactory sessionFactory;
	private  Session session;

	public StockRepuestosDao(){
		if(sessionFactory == null){
			sessionFactory = SessionFactoryHelper.getInstance();
			session = sessionFactory.openSession();
		}
	}

	@SuppressWarnings("unchecked")
	public boolean existe(Repuesto repuesto, Sucursal sucursal) throws Exception {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<Object>();
		try {
			Query query = session.createQuery("from StockRepuestos where repuesto_id = :repuestoId"
					+ " and sucursal_id =:sucursalID");
			query.setInteger("repuestoId", repuesto.getId());
			query.setInteger("sucursalID",sucursal.getId());
			lista = query.list();
			session.close();
			return !lista.isEmpty();
		}
		catch (Exception e) {
			e.printStackTrace();
			session.close();
			throw new Exception("Ocurrió un error, por favor comunicarse con el administrador");
		}
	}

	public void actualizar(Repuesto repuesto, Sucursal sucursal, Integer cantidad) throws Exception {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
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
			session.close();
			throw new Exception("Ocurrió un error, por favor comunicarse con el administrador");
		}
		session.close();
	}

	public void guardar(StockRepuestos stock) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(stock);
	}

	@SuppressWarnings("unchecked")
	public List<StockRepuestos> listar() {
		DataLayer data = new DataLayer();
		return (List<StockRepuestos>)(List<?>)data.list(StockRepuestos.class);
	}

	public Boolean validarStockRepuesto(Integer cantidadRepuesto, Repuesto repuesto, Sucursal sucursal) throws NoHayStockSuficienteException,Exception {
		try {
			if(!session.isOpen()){
				session = sessionFactory.openSession();
			}
			Query query = session.createQuery("from StockRepuestos as sr where sr.repuesto.id = :repuesto "
					+ "and sr.sucursal.id = :sucursal");
			query.setInteger("repuesto", repuesto.getId());
			query.setInteger("sucursal", sucursal.getId());
			StockRepuestos stock = (StockRepuestos) query.uniqueResult();

			if(stock == null || stock.getCantidad() < cantidadRepuesto){
				throw new NoHayStockSuficienteException();
			}
		}catch (NoHayStockSuficienteException e) {
			session.close();
			throw new NoHayStockSuficienteException("No hay stock suficiente del repuesto en la sucursal para cubrir la cantidad requerida.");
		} 
		catch (Exception e) {
			session.close();
			throw new Exception("Ocurrió un error al intentar ingresar, por favor comunicarse con el administrador");
		}
		session.close();
		return true;
	}
	
	public Boolean validarCantidadSalida(Integer cantidadRepuesto, Repuesto repuesto, Sucursal sucursal) throws NoSePuedeRegistrarSalidaDeStockException,Exception {
		try {
			if(!session.isOpen()){
				session = sessionFactory.openSession();
			}
			Query query = session.createQuery("from StockRepuestos as sr where sr.repuesto.id = :repuesto "
					+ "and sr.sucursal.id = :sucursal");
			query.setInteger("repuesto", repuesto.getId());
			query.setInteger("sucursal", sucursal.getId());
			StockRepuestos stock = (StockRepuestos) query.uniqueResult();

			if(stock.getCantidad() < cantidadRepuesto){
				throw new NoHayStockSuficienteException();
			}
		}catch (NoHayStockSuficienteException e) {
			session.close();
			throw new NoSePuedeRegistrarSalidaDeStockException("La cantidad requerida a salir es mayor a la cantidad que hay en stock");
		} 
		catch (Exception e) {
			session.close();
			throw new Exception("Ocurrió un error al intentar ingresar, por favor comunicarse con el administrador");
		}
		session.close();
		return true;
	}

	public Integer getStockByRepuestoAndSucursal(Repuesto repuesto, Sucursal sucursal) throws Exception {
		StockRepuestos stock = null;
		try {
			if(!session.isOpen()){
				session = sessionFactory.openSession();
			}
			Query query = session.createQuery("from StockRepuestos as sr where sr.repuesto.id = :repuesto "
					+ "and sr.sucursal.id = :sucursal");
			query.setInteger("repuesto", repuesto.getId());
			query.setInteger("sucursal", sucursal.getId());
			stock = (StockRepuestos) query.uniqueResult();
		}catch (Exception e) {
			session.close();
			e.printStackTrace();
			throw new Exception("Ocurrió un error al intentar consultar el stock, por favor comunicarse con el administrador");
		}
		session.close();
		
		if(stock != null){
			return stock.getCantidad();
		}else{
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<StockRepuestos> getStockByRepuesto(Repuesto rep) throws Exception {
		List<StockRepuestos> stock = null;
		try {
			if(!session.isOpen()){
				session = sessionFactory.openSession();
			}
			Query query = session.createQuery("from StockRepuestos as sr where sr.repuesto.id = :rep");
			query.setInteger("rep", rep.getId());
			stock = (List<StockRepuestos>)(List<?>) query.list();
		}catch (Exception e) {
			session.close();
			e.printStackTrace();
			throw new Exception("Ocurrió un error al intentar consultar el stock, por favor comunicarse con el administrador");
		}
		session.close();
		return stock;
	}
}
