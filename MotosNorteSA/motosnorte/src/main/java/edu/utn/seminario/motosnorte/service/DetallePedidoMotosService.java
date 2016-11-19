package edu.utn.seminario.motosnorte.service;

import edu.utn.seminario.motosnorte.dao.DetallePedidoMotosDao;
import edu.utn.seminario.motosnorte.domain.DetallePedidoMotos;

public class DetallePedidoMotosService {

	private DetallePedidoMotosDao dao;
	
	public DetallePedidoMotosService()
	{
		if(this.dao == null){
			dao = new DetallePedidoMotosDao();
		}
	}
	
	public void guardar(DetallePedidoMotos det) throws Exception{
		dao.guardar(det);
	}
}
