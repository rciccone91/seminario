package edu.utn.seminario.motosnorte.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.exception.MotoYaExistenteException;
import edu.utn.seminario.motosnorte.exception.RepuestoYaExistenteException;
import edu.utn.seminario.motosnorte.helper.Constants;

public class RepuestosDao {
	
	private SessionFactory sessionFactory;
	private  Session session;

	public RepuestosDao(){
		if(sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}
	
	public void modificar(Repuesto repuesto) throws Exception {
		DataLayer data = new DataLayer();
		data.modificar(repuesto);
	}

	public void guardar(Repuesto repuesto) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(repuesto);
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public Repuesto getById(Integer id) {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Repuesto where repuesto_id = :id");
			query.setInteger("id", id);
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (Repuesto)lista.get(0);
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Repuesto> listar() {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Repuesto as r order by r.modelo");
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (List<Repuesto>)(List<?>)lista;
		}
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Repuesto> listarActivos() {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Repuesto as r where r.activo=:true order by r.modelo");
			query.setBoolean("true", Boolean.TRUE);
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (List<Repuesto>)(List<?>)lista;
		}
	}

	public void eliminar(Integer id) throws Exception {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		Transaction tx = session.getTransaction();
		try {
			Query query = session.createQuery("update Repuesto set activo =:false "
					+ "where repuesto_id = :id");
			query.setBoolean("false", Boolean.FALSE);
			query.setInteger("id", id);
			tx.begin();
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			session.close();
			throw new Exception(e.getMessage());
		}
		session.close();
	}
	
	public void existeMoto(Repuesto rep, String accion) throws RepuestoYaExistenteException, Exception {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		try {
			Query query = session.createQuery("from Repuesto where upper(modelo) like '"+rep.getModelo().toUpperCase().trim()+"'");
			List<Object> lista = new ArrayList<Object>();
			lista = query.list();
			if(accion.equals(Constants.PARAMETRO_MODIFICAR)){
				Repuesto encontrado = (Repuesto)lista.get(0);
				if(encontrado.getId() != rep.getId()){
					session.close();
					throw new RepuestoYaExistenteException("El repuesto que se ingresó ya existe. Por favor verifique los datos.");
				}
			}else{
				if(!lista.isEmpty()){
					session.close();
					throw new RepuestoYaExistenteException("El repuesto que se ingresó ya existe. Por favor verifique los datos.");
				}
			}
		}catch(RepuestoYaExistenteException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

}
