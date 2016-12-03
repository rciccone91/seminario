package edu.utn.seminario.motosnorte.service;

import java.util.List;

import edu.utn.seminario.motosnorte.dao.StockMotosDao;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.StockMotos;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.exception.NoHayStockSuficienteException;
import edu.utn.seminario.motosnorte.exception.NoSePuedeRegistrarSalidaDeStockException;
import edu.utn.seminario.motosnorte.helper.Constants;

public class StockMotosService {
	
	private StockMotosDao dao;

	public StockMotosService()
	{
		if(this.dao == null){
			dao = new StockMotosDao();
		}
	}

	public void actualizar(Moto moto, Sucursal sucursal, Integer cantidad) throws Exception {
		if(dao.existe(moto,sucursal)){
			dao.actualizar(moto, sucursal, cantidad);
		}else{
			StockMotos stock = new StockMotos();
			stock.setCantidad(cantidad);
			stock.setMoto(moto);
			stock.setSucursal(sucursal);
			dao.guardar(stock);
		}
	}
	
	public List<StockMotos> listar() {
		return dao.listar();
	}
	
	public Boolean validarSalida(Moto moto, Sucursal sucursal,String tipoMovimiento) throws NoSePuedeRegistrarSalidaDeStockException, Exception{
		if(!dao.existe(moto, sucursal) 
				&& tipoMovimiento.equals(Constants.MOVIMIENTO_STOCK_SALIDA)){
			throw new NoSePuedeRegistrarSalidaDeStockException();
		}
		return true;
	}

	public boolean validarStockMoto(Integer cantidadMoto, Moto moto, Sucursal sucursal) throws NoHayStockSuficienteException,Exception  {
		return dao.validarStockMoto(cantidadMoto,moto,sucursal);
	}

	public boolean validarCantidadSalida(Moto moto, Sucursal sucursal, Integer cantidad) throws NoHayStockSuficienteException, Exception {
		return dao.validarCantidadSalida(moto,sucursal,cantidad);
	}

	public Integer getStockByMotoAndSucursal(Moto moto, Sucursal sucursal) throws Exception {
		return dao.getStockByMotoAndSucursal(moto,sucursal);
	}

	public List<StockMotos> getStockByMoto(Moto moto) throws Exception {
		return dao.getStockByMoto(moto);
	}

}
