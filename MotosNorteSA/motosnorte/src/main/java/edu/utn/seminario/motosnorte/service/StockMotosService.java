package edu.utn.seminario.motosnorte.service;

import edu.utn.seminario.motosnorte.dao.StockMotosDao;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.Sucursal;

public class StockMotosService {
	
	private StockMotosDao dao;

	public StockMotosService()
	{
		if(this.dao == null){
			dao = new StockMotosDao();
		}
	}

	public void actualizar(Moto moto, Sucursal sucursal, Integer cantidad) {
	}

}
