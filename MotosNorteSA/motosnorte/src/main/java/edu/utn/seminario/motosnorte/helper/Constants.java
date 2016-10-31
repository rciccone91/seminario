package edu.utn.seminario.motosnorte.helper;

public class Constants {
	public final static Integer ADMINISTRADOR_ID = 1;
	public final static Integer VENDEDOR_ID = 2;
	public final static Integer EMPLEADO_DE_DEPOSITO_ID = 3;
	public final static String PARAMETRO_ELIMINAR = "eliminar";
	public final static String PARAMETRO_MODIFICAR = "modificar";
	public final static String PARAMETRO_CREAR = "crear";

	public static String getRolDescription(Integer rol){
		if(rol == Constants.ADMINISTRADOR_ID){
			return "Administrador"; 
		}else if(rol == Constants.VENDEDOR_ID){
			return "Vendedor";
		}else{
			return "Emp. de dep�sito";
		}
	}
	
	public final static Integer COLOR_ROJO =1;
	public final static Integer COLOR_BLANCO =2;
	public final static Integer COLOR_AZUL =3;
	public final static Integer COLOR_NEGRO =4;
	public final static Integer COLOR_AMARILLO =5;
	public final static Integer COLOR_VERDE =6;
	
	public static String getColorDescription(Integer id){
		String color ="N/A";
		switch(id){
		case  1:
			color = "Rojo";
		case  2:
			color = "Blanco";
		case  3:
			color = "Azul";
		case  4:
			color = "Negro";
		case  5:
			color = "Amarillo";
		case  6:
			color = "Verde";
		}
		return color;
	}
}
