package edu.utn.seminario.motosnorte.service;

import java.util.List;

import edu.utn.seminario.motosnorte.dao.MotoDao;
import edu.utn.seminario.motosnorte.domain.Moto;

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

	public void guardar(Moto moto) throws Exception {
		dao.guardar(moto);
	}

	public void modificar(Moto moto) throws Exception {
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

}
