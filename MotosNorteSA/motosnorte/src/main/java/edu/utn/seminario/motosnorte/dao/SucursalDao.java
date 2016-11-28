package edu.utn.seminario.motosnorte.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.exception.SucursalNoEncontradaException;

public class SucursalDao implements Serializable{
	private SessionFactory sessionFactory;
	private Session session;

	public SucursalDao() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
		}
	}


	public void guardar(Sucursal sucursal) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(sucursal);
	}

	@SuppressWarnings("unchecked")
	public Boolean existe(Sucursal sucursal) throws Exception {
		List<Object> lista = new ArrayList<Object>();
		try {
			Query query = session
					.createQuery("from Sucursal as S where S.id = :sucursal_id");
			query.setString("sucursal_id", Integer.toString(sucursal.getId()));
			lista = query.list();
			return !lista.isEmpty();
		} catch (Exception e) {
			throw new Exception(
					"Ocurrió un error, por favor comunicarse con el administrador");
		}

	}
	
	@SuppressWarnings("unchecked")
	public Boolean existeOtraSucursalConMismaDescripcion(Sucursal unaSucursal) throws Exception {
		List<Object> lista = new ArrayList<Object>();
		try {
			Query query = session
					.createQuery("from Sucursal where descripcion = :desc and sucursal_id != :idSuc");
			query.setString("desc", unaSucursal.getDescripcion());
			query.setInteger("idSuc", unaSucursal.getId());
			lista = query.list();
			return !lista.isEmpty();
		} catch (Exception e) {
			throw new Exception(
					"Ocurrió un error, por favor comunicarse con el administrador");
		}

	}
	
	@SuppressWarnings("unchecked")
	public Boolean existeOtraSucursalConMismaDireccion(Sucursal unaSucursal) throws Exception {
		List<Object> lista = new ArrayList<Object>();
		try {
			Query query = session
					.createQuery("from Sucursal where direccion = :dir and sucursal_id != :idSuc");
			query.setString("dir", unaSucursal.getDireccion());
			query.setInteger("idSuc", unaSucursal.getId());
			lista = query.list();
			return !lista.isEmpty();
		} catch (Exception e) {
			throw new Exception(
					"Ocurrió un error, por favor comunicarse con el administrador");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Sucursal> listar() {
		DataLayer data = new DataLayer();
		return (List<Sucursal>) (List<?>) data.list(Sucursal.class);
	}

	@SuppressWarnings("unchecked")
	public Sucursal getById(Integer id) throws SucursalNoEncontradaException {

		List<Object> lista = new ArrayList<>();
		try {
			Query query = session
					.createQuery("from Sucursal where id = :sucursal_id");
			query.setInteger("sucursal_id", id);
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!lista.isEmpty()) {
			return (Sucursal) lista.get(0);
		} else {
			throw new SucursalNoEncontradaException(
					"La Sucursal requerida no existe.");
		}

	}

	public void modificar(Sucursal sucursal) throws Exception {
		DataLayer data = new DataLayer();
		data.modificar(sucursal);
	}

	public void eliminar(Sucursal s) throws Exception {
		Transaction tx = session.getTransaction();
		try {
			Query query = session.createQuery("update Sucursal set activo =:false "
					+ "where sucursal_id = :sucursalId");
			query.setBoolean("false", Boolean.FALSE);
			query.setInteger("sucursalId", s.getId());
			tx.begin();
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new Exception(e.getMessage());
		}
	}


	@SuppressWarnings({ "unchecked", "finally" })
	public List<Sucursal> listarActivos() {
		List<Object> lista = new ArrayList<Object>();
		try {
			Query query = session.createQuery("from Sucursal where activo =:true order by descripcion");
			query.setBoolean("true", Boolean.TRUE);
			lista = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return (List<Sucursal>)(List<?>)lista;
		}	
	}

}
