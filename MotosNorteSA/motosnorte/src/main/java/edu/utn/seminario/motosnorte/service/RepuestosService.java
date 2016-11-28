package edu.utn.seminario.motosnorte.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.utn.seminario.motosnorte.dao.MotoDao;
import edu.utn.seminario.motosnorte.dao.RepuestosDao;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.exception.RepuestoYaExistenteException;
import edu.utn.seminario.motosnorte.exception.UsuarioYaExistenteException;
import edu.utn.seminario.motosnorte.helper.Constants;

public class RepuestosService {
	
	private RepuestosDao dao;
	
	public RepuestosService()
	{
		if(this.dao == null){
			dao = new RepuestosDao();
		}
	}

	public void modificar(Repuesto repuesto) throws RepuestoYaExistenteException, Exception{
		dao.existeMoto(repuesto, Constants.PARAMETRO_MODIFICAR);
		dao.modificar(repuesto);
	} 
	
	public void guardar(Repuesto repuesto) throws RepuestoYaExistenteException, Exception{
		dao.existeMoto(repuesto, Constants.PARAMETRO_CREAR);
		dao.guardar(repuesto);
	}

	public Repuesto getById(Integer id) {
		return dao.getById(id);
	}

	public List<Repuesto> listar() {
		return dao.listar();
	}

	public void eliminar(Repuesto r) throws Exception {
		dao.eliminar(r.getId());
	}

	public List<Repuesto> listarActivos() {
		return dao.listarActivos();
	}
}
