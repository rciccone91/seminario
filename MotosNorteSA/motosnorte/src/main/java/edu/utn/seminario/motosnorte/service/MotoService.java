package edu.utn.seminario.motosnorte.service;

import java.util.List;

import edu.utn.seminario.motosnorte.dao.MotoDao;
import edu.utn.seminario.motosnorte.domain.DetallePedidoMotos;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.exception.MotoYaExistenteException;
import edu.utn.seminario.motosnorte.helper.Constants;

public class MotoService {

	private MotoDao dao;

	public MotoService()
	{
		if(this.dao == null){
			dao = new MotoDao();
		}
	}

	public Moto getById(Integer id) {
		return dao.getById(id);
	}

	public void guardar(Moto moto) throws MotoYaExistenteException, Exception {
		dao.existeMoto(moto, Constants.PARAMETRO_CREAR);
		dao.guardar(moto);
	}

	public void modificar(Moto moto) throws MotoYaExistenteException, Exception {
		dao.existeMoto(moto, Constants.PARAMETRO_MODIFICAR);
		dao.modificar(moto);
	}

	public List<Moto> listar() {
		return dao.listar();
	}

	public void eliminar(Moto m) throws Exception {
		dao.eliminar(m);
	}

	public List<Moto> listarActivos() {
		return dao.listarActivos();
	}
	
	public List<Moto> listarMotosFiltro(Integer marcaID, Integer cilindradaID,
			Integer categoriaID, Integer colorID) {
		return dao.listarMotosFiltro(marcaID, cilindradaID, categoriaID, colorID);
	}

	public List<Moto> buscarProductoMotos(Integer marca, String modelo, Integer precio) {
		return dao.buscarProductoMotos(marca,modelo,precio);
	}

	public List<Moto> mostrarEstadisticas(List<DetallePedidoMotos> dpm) {
		return dao.mostrarEstadisticas(dpm);
	}


}
