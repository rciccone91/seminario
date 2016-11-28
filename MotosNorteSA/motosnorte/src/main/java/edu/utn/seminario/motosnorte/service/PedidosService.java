package edu.utn.seminario.motosnorte.service;

import java.util.List;

import edu.utn.seminario.motosnorte.dao.MotoDao;
import edu.utn.seminario.motosnorte.dao.PedidosDao;
import edu.utn.seminario.motosnorte.dao.UsuarioDao;
import edu.utn.seminario.motosnorte.domain.Pedido;
import edu.utn.seminario.motosnorte.exception.UsuarioOContraseñaIncorrectoException;

public class PedidosService {

	private PedidosDao dao;
	private UsuarioDao dao2;
	
	public PedidosService()
	{
		if(this.dao == null){
			dao = new PedidosDao();
		}
		
		if(this.dao2 == null){
			dao2 = new UsuarioDao();
		}
	}

	public List<Pedido> getPedidosRemotosByEstado(String user, String pass, Integer estado) throws UsuarioOContraseñaIncorrectoException, Exception {
		dao2.login(user, pass);
		return dao.getPedidosRemotosByEstado(estado);
	}
	
	public void guardar(Pedido ped) throws Exception{
		dao.guardar(ped);
	}

	public List<Pedido> listar() {
		return dao.listar();
	}

	public List<Pedido> listarFilterById(Integer idPed) {
		return dao.listarFilterById(idPed);
	}

}
