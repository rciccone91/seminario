package edu.utn.seminario.motosnorte.service;

import java.util.List;

import edu.utn.seminario.motosnorte.dao.StockMotosDao;
import edu.utn.seminario.motosnorte.dao.StockRepuestosDao;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.domain.StockMotos;
import edu.utn.seminario.motosnorte.domain.StockRepuestos;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.exception.NoHayStockSuficienteException;
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
	
	public List<StockRepuestos> listar() {
		return dao.listar();
	}
	public Boolean validarStockRepuesto(Integer cantidadRepuesto, Repuesto repuesto, Sucursal sucursal) throws NoHayStockSuficienteException, Exception {
		return dao.validarStockRepuesto(cantidadRepuesto,repuesto,sucursal);
	}
	public boolean validarCantidadSalida(Repuesto repuesto, Sucursal sucursal, Integer cantidad) throws NoSePuedeRegistrarSalidaDeStockException, Exception {
		return dao.validarCantidadSalida(cantidad, repuesto, sucursal);
	}
	public Integer getStockByRepuestoAndSucursal(Repuesto repuesto, Sucursal sucursal) throws Exception {
		return dao.getStockByRepuestoAndSucursal(repuesto,sucursal);
	}
	public List<StockRepuestos> getStockByRepuesto(Repuesto rep) throws Exception {
		return dao.getStockByRepuesto(rep);
	}
}
