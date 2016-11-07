package edu.utn.seminario.motosnorte.service;

import edu.utn.seminario.motosnorte.dao.MovimientoStockRepuestoDao;
import edu.utn.seminario.motosnorte.domain.MovimientoStockRepuesto;

public class MovimientoStockRepuestoService {
	
	private MovimientoStockRepuestoDao dao;

	public MovimientoStockRepuestoService()
	{
		if(this.dao == null){
			dao = new MovimientoStockRepuestoDao();
		}
	}

	public void guardar(MovimientoStockRepuesto mov) throws Exception {
		dao.guardar(mov);
	}

}
