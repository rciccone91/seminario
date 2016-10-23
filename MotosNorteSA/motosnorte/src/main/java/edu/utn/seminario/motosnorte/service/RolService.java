package edu.utn.seminario.motosnorte.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import edu.utn.seminario.motosnorte.dao.RolDao;
import edu.utn.seminario.motosnorte.domain.Rol;

@ManagedBean(name="rolService", eager=true)
@ApplicationScoped
public class RolService {
	
	private RolDao dao;
	
	public RolService()
	{
		if(this.dao == null){
			dao = new RolDao();
		}
	}

	public List<Rol> listar() {
		return RolDao.listar();
	}
	
	public Rol getRolById(Integer id){
		return dao.getById(id);
	}

}
