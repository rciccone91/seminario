package edu.utn.seminario.motosnorte.exception;

public class NoSePuedeRegistrarSalidaDeStockException extends Exception {
	
	public NoSePuedeRegistrarSalidaDeStockException(){
		super("No se puede registrar la salida de stock debido a que el producto"
				+ " no posee stock previo registrado.");
	}
	
	public NoSePuedeRegistrarSalidaDeStockException(String msg){
		super(msg);
	}
}
