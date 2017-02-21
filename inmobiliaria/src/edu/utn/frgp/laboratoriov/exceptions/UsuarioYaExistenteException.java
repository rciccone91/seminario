package edu.utn.frgp.laboratoriov.exceptions;

public class UsuarioYaExistenteException extends Exception {

	public UsuarioYaExistenteException() {
		super();
	}
	
	public UsuarioYaExistenteException(String msg) {
		super(msg);
	}
}
