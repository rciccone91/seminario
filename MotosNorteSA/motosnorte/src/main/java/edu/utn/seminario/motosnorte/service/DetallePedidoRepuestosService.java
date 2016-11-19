package edu.utn.seminario.motosnorte.service;

import java.io.Serializable;

import edu.utn.seminario.motosnorte.dao.ClienteDao;
import edu.utn.seminario.motosnorte.dao.DetallePedidoRepuestosDao;
import edu.utn.seminario.motosnorte.dao.MovimientoStockRepuestoDao;
import edu.utn.seminario.motosnorte.dao.StockRepuestosDao;
import edu.utn.seminario.motosnorte.domain.DetallePedidoRepuestos;

public class DetallePedidoRepuestosService implements Serializable{
	
	private DetallePedidoRepuestosDao dao;
	
	public DetallePedidoRepuestosService()
	{
		if(this.dao == null){
			dao = new DetallePedidoRepuestosDao();
		}
	}
	
	public void guardar(DetallePedidoRepuestos det) throws Exception{
		dao.guardar(det);
	}

}
