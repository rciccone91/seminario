package edu.utn.seminario.motosnorte.service;

import java.util.Date;
import java.util.List;

import edu.utn.seminario.motosnorte.dao.DetallePedidoMotosDao;
import edu.utn.seminario.motosnorte.domain.CategoriaMoto;
import edu.utn.seminario.motosnorte.domain.DetallePedidoMotos;
import edu.utn.seminario.motosnorte.domain.Pedido;
import edu.utn.seminario.motosnorte.transferobject.MotosMasVendidas;

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

	public List<DetallePedidoMotos> listarFilterByPedidoId(Integer idPed) {
		return dao.listarFilterByPedidoId(idPed);
	}
	
	public List<MotosMasVendidas> getMotosMasVendidas(Date fechaDesde, Date fechaHasta,
			CategoriaMoto categoriaMoto) {
		return dao.getMotosMasVendidas(fechaDesde,fechaHasta,categoriaMoto);
		
	}
}
