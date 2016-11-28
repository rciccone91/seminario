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
			return "Emp. de depósito";
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
			break;
		case  2:
			color = "Blanco";
			break;
		case  3:
			color = "Azul";
			break;
		case  4:
			color = "Negro";
			break;
		case  5:
			color = "Amarillo";
			break;
		case  6:
			color = "Verde";
			break;
		default:
			color = "No informado";
			break;
		}
		return color;
	}
	
	public final static String MOVIMIENTO_STOCK_ENTRADA = "Entrada";
	public final static String MOVIMIENTO_STOCK_SALIDA = "Salida";

	public final static Integer ESTADO_PEND_FACT = 1;
	public final static Integer ESTADO_FACTURADO = 2;
	public final static Integer ESTADO_CANCELADO = 3;
	public final static Integer ESTADO_REINTEGRADO = 4;
	
	public static String getEstadoDescription(Integer id){
		String estado ="N/A";
		switch(id){
		case 1:
			estado = "Pendiente Facturación";
			break;
		case 2:
			estado = "Facturado";
			break;
		case 3:
			estado = "Cancelado";
			break;
		case 4:
			estado = "Reintegrado";
			break;
		default:
			estado = "No informado";
			break;
		}
		return estado;
	}
	

}
