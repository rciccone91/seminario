package edu.utn.seminario.motosnorte.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.utn.seminario.motosnorte.datalayer.DataLayer;
import edu.utn.seminario.motosnorte.domain.DetallePedidoMotos;
import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.exception.MotoYaExistenteException;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.helper.SessionFactoryHelper;

public class MotoDao {

	private SessionFactory sessionFactory;
	private  Session session;

	public MotoDao(){
		if(sessionFactory == null){
			sessionFactory = SessionFactoryHelper.getInstance();
			session = sessionFactory.openSession();
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Moto> listar(){
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Moto as m order by m.modelo");
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (List<Moto>)(List<?>)lista;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Moto> listarActivos(){
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Moto as m where m.activo =:true order by m.modelo");
			query.setBoolean("true", Boolean.TRUE);
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (List<Moto>)(List<?>)lista;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public Moto getById(Integer id) {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Object> lista = new ArrayList<>();
		try {
			Query query = session.createQuery("from Moto where moto_id = :id");
			query.setInteger("id", id);
			lista = query.list();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return (Moto)lista.get(0);
		}
	}

	public void guardar(Moto moto) throws Exception {
		DataLayer data = new DataLayer();
		data.guardar(moto);
	}

	public void modificar(Moto moto) throws Exception {
		DataLayer data = new DataLayer();
		data.modificar(moto);
	}

	public void eliminar(Moto m) throws Exception {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		Transaction tx = session.getTransaction();
		try {
			Query query = session.createQuery("update Moto set activo =:false "
					+ "where moto_id = :id");
			query.setBoolean("false", Boolean.FALSE);
			query.setInteger("id", m.getId());
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

	@SuppressWarnings({ "finally", "unchecked" })
	public List<Moto> listarMotosFiltro(Integer marcaID, Integer categoriaID,
			Integer cilindradaID, Integer colorID) {
		List<Moto> listaFiltrada = new ArrayList<Moto>();
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		try {
			Query query = session.createQuery("from Moto where marca = :marcaID and cilindrada = :cilindradaID"
					+ " and categoriaMoto = :categoriaID and color = :colorID");
			query.setInteger("marcaID", marcaID);
			query.setInteger("cilindradaID", cilindradaID);
			query.setInteger("categoriaID", categoriaID);
			query.setInteger("colorID", colorID);
			List<Object> lista = new ArrayList<Object>();
			lista = query.list();
			listaFiltrada = (List<Moto>)(List<?>) lista;
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return listaFiltrada;
		}
	}

	@SuppressWarnings({ "finally", "unchecked" })
	public List<Moto> buscarProductoMotos(Integer marca, String modelo,
			Integer precio) {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		List<Moto> listaFiltrada = new ArrayList<Moto>();
		try {
			Query query = session.createQuery("from Moto where marca = :marcaID and modelo = :modelo"
					+ " and precio = :precio");
			query.setInteger("marcaID", marca);
			query.setString("modelo", modelo);
			query.setInteger("precio", precio);
			List<Object> lista = new ArrayList<Object>();
			lista = query.list();
			listaFiltrada = (List<Moto>)(List<?>) lista;
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
			return listaFiltrada;
		}
	}


	@SuppressWarnings("null")
	public List<Moto> mostrarEstadisticas(List<DetallePedidoMotos> dpm) {
		DetallePedidoMotos lista = new DetallePedidoMotos();
		for (DetallePedidoMotos detallemotos : dpm) {
			System.out.println("esta es de rober"+detallemotos.getMoto().getId());
		}
		//		for(int i=0; i<dpm.size(); i++){
		//			System.out.println("size() de moto"+dpm.size());
		//			DetallePedidoMotos motosMasPedidas = (DetallePedidoMotos)dpm.get(i);
		//			System.out.println("este es el valor del id:"+motosMasPedidas.getMoto().getId() );
		//			//motos=getById(dpm.get(i));
		//			lista.add(i, motos);
		//		}
		return null;
	}

	public void existeMoto(Moto moto, String accion) throws MotoYaExistenteException, Exception {
		if(!session.isOpen()){
			session = sessionFactory.openSession();
		}
		try {
			Query query = session.createQuery("from Moto where upper(modelo) like '"+moto.getModelo().toUpperCase().trim()+"'");
			List<Object> lista = new ArrayList<Object>();
			lista = query.list();
			if(accion.equals(Constants.PARAMETRO_MODIFICAR)){
				Moto motoEncontrada = (Moto)lista.get(0);
				if(motoEncontrada.getId() != moto.getId()){
					session.close();
					throw new MotoYaExistenteException("La moto que se ingresó ya existe. Por favor verifique los datos.");
				}
			}else{
				if(!lista.isEmpty()){
					session.close();
					throw new MotoYaExistenteException("La moto que se ingresó ya existe. Por favor verifique los datos.");
				}
			}
		}catch(MotoYaExistenteException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
