package edu.utn.seminario.motosnorte.service;

import edu.utn.seminario.motosnorte.dao.StockMotosDao;
import edu.utn.seminario.motosnorte.dao.StockRepuestosDao;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.domain.StockMotos;
import edu.utn.seminario.motosnorte.domain.StockRepuestos;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.exception.NoSePuedeRegistrarSalidaDeStockException;
import edu.utn.seminario.motosnorte.helper.Constants;

public class StockRepuestosService {

	private StockRepuestosDao dao;

	public StockRepuestosService()
	{
		if(this.dao == null){
			dao = new StockRepuestosDao();
		}
	}
	public Boolean validarSalida(Repuesto repuesto, Sucursal sucursal, String tipoMovimiento) throws NoSePuedeRegistrarSalidaDeStockException, Exception{
		if(!dao.existe(repuesto, sucursal) 
				&& tipoMovimiento.equals(Constants.MOVIMIENTO_STOCK_SALIDA)){
			throw new NoSePuedeRegistrarSalidaDeStockException();
		}
		return true;
	}

	public void actualizar(Repuesto repuesto, Sucursal sucursal, Integer cantidad) throws Exception {
		if(dao.existe(repuesto,sucursal)){
			dao.actualizar(repuesto, sucursal, cantidad);
		}else{
			StockRepuestos stock = new StockRepuestos();
			stock.setCantidad(cantidad);
			stock.setRepuesto(repuesto);
			stock.setSucursal(sucursal);
			dao.guardar(stock);
		}
	}

}
