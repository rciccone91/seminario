package edu.utn.seminario.motosnorte.service;

import edu.utn.seminario.motosnorte.dao.MovimientoStockMotosDao;
import edu.utn.seminario.motosnorte.domain.MovimientoStockMoto;

public class MovimientoStockMotoService {
	
	private MovimientoStockMotosDao dao;

	public MovimientoStockMotoService()
	{
		if(this.dao == null){
			dao = new MovimientoStockMotosDao();
		}
	}

	public void guardar(MovimientoStockMoto mov) throws Exception {
		dao.guardar(mov);
	}

}
