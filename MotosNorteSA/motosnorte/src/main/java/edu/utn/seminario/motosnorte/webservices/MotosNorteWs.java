package edu.utn.seminario.motosnorte.webservices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import edu.utn.seminario.motosnorte.domain.*;
import edu.utn.seminario.motosnorte.exception.UsuarioOContraseñaIncorrectoException;
import edu.utn.seminario.motosnorte.service.PedidosService;

@WebService
public class MotosNorteWs {
	
	   @WebMethod
	   public List<Pedido> getPedidos(
			   @WebParam(name="user")String user,
			   @WebParam(name="pass")String pass, 
			   @WebParam(name="estado")Integer estado) throws UsuarioOContraseñaIncorrectoException, Exception {
		   PedidosService service = new PedidosService();
		   return service.getPedidosRemotosByEstado(user,pass,estado);
	   }
	   
	   @WebMethod
	   public DetallePedidoDTO getDetallePedido(
			   @WebParam(name="user")String user,
			   @WebParam(name="pass")String pass, 
			   @WebParam(name="estado")Integer estado){
		   PedidosService service = new PedidosService();
//		   return service.getPedidosRemotosByEstado(user,pass,estado);
		   return null;
	   }
}
