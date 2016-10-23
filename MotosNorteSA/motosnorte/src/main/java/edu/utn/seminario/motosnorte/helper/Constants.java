package edu.utn.seminario.motosnorte.helper;

public class Constants {
	public final static Integer ADMINISTRADOR_ID = 1;
	public final static Integer VENDEDOR_ID = 2;
	public final static Integer EMPLEADO_DE_DEPOSITO_ID = 3;
	public final static String PARAMETRO_ELIMINAR = "eliminar";
	public final static String PARAMETRO_MODIFICAR = "modificar";

	public static String getRolDescription(Integer rol){
		if(rol == Constants.ADMINISTRADOR_ID){
			return "Administrador"; 
		}else if(rol == Constants.VENDEDOR_ID){
			return "Vendedor";
		}else{
			return "Emp. de depósito";
		}
	}
}
