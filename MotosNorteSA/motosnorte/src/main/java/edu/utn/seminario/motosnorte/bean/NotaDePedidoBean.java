package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import edu.utn.seminario.motosnorte.domain.Cliente;
import edu.utn.seminario.motosnorte.domain.DetallePedidoMotos;
import edu.utn.seminario.motosnorte.domain.DetallePedidoRepuestos;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.MovimientoStockMoto;
import edu.utn.seminario.motosnorte.domain.MovimientoStockRepuesto;
import edu.utn.seminario.motosnorte.domain.Pedido;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.exception.ClienteNoEncontradoException;
import edu.utn.seminario.motosnorte.exception.NoHayStockSuficienteException;
import edu.utn.seminario.motosnorte.exception.SucursalNoEncontradaException;
import edu.utn.seminario.motosnorte.exception.UsuarioNoEncontradoException;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.helper.SessionHelper;
import edu.utn.seminario.motosnorte.service.ClienteBackingService;
import edu.utn.seminario.motosnorte.service.ClienteService;
import edu.utn.seminario.motosnorte.service.DetallePedidoMotosService;
import edu.utn.seminario.motosnorte.service.DetallePedidoRepuestosService;
import edu.utn.seminario.motosnorte.service.MotoService;
import edu.utn.seminario.motosnorte.service.MovimientoStockMotoService;
import edu.utn.seminario.motosnorte.service.MovimientoStockRepuestoService;
import edu.utn.seminario.motosnorte.service.PedidosService;
import edu.utn.seminario.motosnorte.service.RepuestosService;
import edu.utn.seminario.motosnorte.service.StockMotosService;
import edu.utn.seminario.motosnorte.service.StockRepuestosService;
import edu.utn.seminario.motosnorte.service.SucursalBackingService;
import edu.utn.seminario.motosnorte.service.UsuarioService;
import edu.utn.seminario.motosnorte.transferobject.RepuestoForNota;

@ManagedBean(name = "notaDePedidoBean")
@ViewScoped
public class NotaDePedidoBean implements Serializable{

	private List<Cliente> clientes;
	private Cliente cliente;
	private List<Sucursal> sucursales;
	private Sucursal sucursal;
	private List<Repuesto> repuestos;
	private List<Moto> motos;
	private Integer stockActualRep;
	private Integer stockActualMoto;
	private Integer precioActualRep;
	private Integer precioActualMoto;
	private Sucursal sucursalPrevia;

	private Boolean desactivar;
	// Detalle repuesto
	private Integer cantidadRepuesto;
	private Repuesto repuesto;
	private static List<DetalleRepuesto> detalleRepuestosList;

	// Detalle Moto
	private Moto moto;
	private Integer cantidadMoto;
	private static List<DetalleMoto> detalleMotosList;

	private UIComponent mensaje;
	private Integer idPedido;
	private Integer clienteId;
	private Integer sucursalId;


	@ManagedProperty("#{clienteBackingService}")
	ClienteBackingService clienteBackingService;

	@ManagedProperty("#{sucursalBackingService}")
	SucursalBackingService sucursalBackingService;

	private RepuestosService repuestoService;
	private UsuarioService usuarioService;
	private DetallePedidoRepuestosService detallePedidoRepuestosService;
	private PedidosService pedidoService;
	private StockRepuestosService stockRepuestosService;
	private MovimientoStockRepuestoService movimientoStockRepuestoService;
	private MotoService motoService;
	private StockMotosService stockMotosService;
	private DetallePedidoMotosService detallePedidoMotosService;
	private MovimientoStockMotoService movimientoStockMotoService;
	private ClienteService clienteService;


	@PostConstruct
	public void init() {
		if(!FacesContext.getCurrentInstance().isPostback()){
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
			repuestoService = new RepuestosService();
			usuarioService = new UsuarioService();
			motoService = new MotoService();
			clienteService = new ClienteService();
			repuestos = repuestoService.listarActivos();
			clientes = clienteService.listarActivos();
			sucursales = sucursalBackingService.listarActivos();
			motos = motoService.listarActivos();
			detalleMotosList = new ArrayList<DetalleMoto>();
			detalleRepuestosList = new ArrayList<DetalleRepuesto>();
			sucursalPrevia = null;
			desactivar = true;
			
			if(paramMap.get("cli")!= null && paramMap.get("suc")!=null){
				try {
					cliente = clienteService.getById(Integer.parseInt(paramMap.get("cliente")));
					sucursal = (Sucursal) sucursalBackingService.getById(Integer.parseInt(paramMap.get("sucursal")));
				} catch (Exception e) {
					e.printStackTrace();
					FacesContext.getCurrentInstance().addMessage(
							mensaje.getClientId(),
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error",
									"Ha ocurrido un error al consultar los datos. Por favor contactarse con el Administrador"));
				}
			}
		}
	}
	
	public void cambioDeSucursal(){
		if(detalleRepuestosList != null && !detalleRepuestosList.isEmpty() && sucursal!=null){
			for (DetalleRepuesto dr : detalleRepuestosList) {
				if(dr.getSucursal() != sucursal){
					updateActualesrepuestps();
					RequestContext.getCurrentInstance().execute("PF('sucursalDialog').show();");
				}
			}
		}
		else{
			repuesto=null;
			updateActualesrepuestps();
		}
		if(detalleMotosList != null && !detalleMotosList.isEmpty() && sucursal!=null){
			for (DetalleMoto dm : detalleMotosList) {
				if(dm.getSucursal() != sucursal){
					updateActualesMotos();
					RequestContext.getCurrentInstance().execute("PF('sucursalDialog').show();");
				}
			}
		}
		else{
			moto=null;
			updateActualesMotos();
		}
	}

	private void updateActualesrepuestps() {
		stockActualRep = null;
		precioActualRep = null;
		RequestContext.getCurrentInstance().update("mainForm:tabPedido:cantAct");
		RequestContext.getCurrentInstance().update("mainForm:tabPedido:precioAct");
	}
	
	private void updateActualesMotos() {
		stockActualMoto = null;
		precioActualMoto = null;
		RequestContext.getCurrentInstance().update("mainForm:tabPedido:cantActMoto");
		RequestContext.getCurrentInstance().update("mainForm:tabPedido:precioActMoto");
	}

	public Boolean sucursalSeleccionada(){
		if(sucursal == null){
			return true;
		}else{
			return false;
		}
	}
	
	public void cambioDeRepuesto(Repuesto r){
		stockRepuestosService = new StockRepuestosService();
		if(repuesto!=null){
			try {
				precioActualRep = r.getPrecio();
				stockActualRep = stockRepuestosService.getStockByRepuestoAndSucursal(r, sucursal);
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(
						mensaje.getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error",
								"Ha ocurrido un error al consultar el stock. Por favor contactarse con el Administrador"));
			}
		}else{
			precioActualRep = null;
			stockActualRep = null;
		}

	}
	
	public void cambioDeMoto(Moto m){
		stockMotosService = new StockMotosService();
		if(moto!=null){
			try {
				precioActualMoto = m.getPrecio();
				stockActualMoto = stockMotosService.getStockByMotoAndSucursal(m, sucursal);
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(
						mensaje.getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error",
								"Ha ocurrido un error al consultar el stock. Por favor contactarse con el Administrador"));
			}
		}else{
			precioActualMoto = null;
			stockActualMoto = null;
		}

	}
	
	public void guardar(){
		pedidoService = new PedidosService();

		detallePedidoRepuestosService = new DetallePedidoRepuestosService();
		movimientoStockRepuestoService = new MovimientoStockRepuestoService();
		stockRepuestosService = new StockRepuestosService();

		detallePedidoMotosService = new DetallePedidoMotosService();
		movimientoStockMotoService = new MovimientoStockMotoService();
		stockMotosService = new StockMotosService();

		try {
			Pedido pedido = armarPedido();
			Pedido p = pedidoService.guardar(pedido);

			if(!detalleRepuestosList.isEmpty()){
				List<DetallePedidoRepuestos> detalleRepuestos = armarDetallePedidoRepuestos(pedido);
				for (DetallePedidoRepuestos det : detalleRepuestos) {
					detallePedidoRepuestosService.guardar(det);

					MovimientoStockRepuesto mov = new MovimientoStockRepuesto();
					mov.setCantidad(det.getCantidad()*-1);
					mov.setFecha(Calendar.getInstance().getTime());
					mov.setRepuesto(det.getRepuesto());
					mov.setSucursal(sucursal);
					mov.setUsuario(pedido.getUsuario());
					movimientoStockRepuestoService.guardar(mov);
					stockRepuestosService.actualizar(det.getRepuesto(), sucursal, det.getCantidad()*-1);
				}
			}

			if(!detalleMotosList.isEmpty()){
				List<DetallePedidoMotos> detalleMotos = armarDetallePedidoMotos(pedido);
				for (DetallePedidoMotos det : detalleMotos) {
					detallePedidoMotosService.guardar(det);

					MovimientoStockMoto mov = new MovimientoStockMoto();
					mov.setCantidad(det.getCantidad()*-1);
					mov.setFecha(Calendar.getInstance().getTime());
					mov.setMoto(det.getMoto());
					mov.setSucursal(sucursal);
					mov.setUsuario(pedido.getUsuario());
					movimientoStockMotoService.guardar(mov);
					stockMotosService.actualizar(det.getMoto(), sucursal, det.getCantidad()*-1);
				}
			}	
			idPedido = p.getId();
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('successDialog').show();");
		} catch (UsuarioNoEncontradoException e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							"Ha ocurrido un error al guardar la nota de pedido. Por favor contactarse con el Administrador"));
		}
		//		/return "index.xhtml";
	}


	private List<DetallePedidoMotos> armarDetallePedidoMotos(Pedido pedido) {
		List<DetallePedidoMotos> lista = new ArrayList<DetallePedidoMotos>();
		for (DetalleMoto dr : detalleMotosList) {
			DetallePedidoMotos detalle = new DetallePedidoMotos();
			detalle.setCantidad(dr.getCantidad());
			detalle.setPedido(pedido);
			detalle.setMoto(moto);
			lista.add(detalle);
		}
		return lista;
	}

	public void validarStockEnSucursal(){

	}

	private List<DetallePedidoRepuestos> armarDetallePedidoRepuestos(Pedido pedido) {
		List<DetallePedidoRepuestos> lista = new ArrayList<DetallePedidoRepuestos>();
		for (DetalleRepuesto dr : detalleRepuestosList) {
			DetallePedidoRepuestos detalle = new DetallePedidoRepuestos();
			detalle.setCantidad(dr.getCantidad());
			detalle.setNotaPedido(pedido);
			detalle.setRepuesto(dr.getRepuesto());
			lista.add(detalle);
		}
		return lista;
	}

	private Pedido armarPedido() throws UsuarioNoEncontradoException {
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setFecha(Calendar.getInstance().getTime());
		pedido.setEstado(Constants.ESTADO_PEND_FACT);
		pedido.setSucursal(sucursal);
		pedido.setUsuario(usuarioService.getById(SessionHelper.getUserName()));

		return pedido;
	}

	public void chequearHabilitado(){
		if(detalleMotosList.isEmpty() && detalleRepuestosList.isEmpty()){
			desactivar=true;
		}else{
			desactivar=false;
		}
	}



	public String addAction() {
		stockRepuestosService = new StockRepuestosService();
		try {
			if(!repuestoYaExistente(repuesto.getId())){
				if(stockRepuestosService.validarStockRepuesto(cantidadRepuesto,repuesto,sucursal)){
					DetalleRepuesto detRep = new DetalleRepuesto(cantidadRepuesto,repuesto,sucursal);
					detalleRepuestosList.add(detRep);
				}
			}else{
				FacesContext.getCurrentInstance().addMessage(
						mensaje.getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Atención",
								"El producto requerido ya fue agregado. Elimínelo y vuelva a cargarlo."));
			}
			chequearHabilitado();
		} catch (NoHayStockSuficienteException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Atención",
							e.getMessage()));

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							"Ha ocurrido un error al agregar el producto. Por favor comunicarse con el administrador"));
		}
		return null;
	}

	public String addActionMoto() {
		setStockMotosService(new StockMotosService());
		try {
			if(!motoYaExistente(moto.getId())){
				if(stockMotosService.validarStockMoto(cantidadMoto,moto,sucursal)){
					DetalleMoto detMot = new DetalleMoto(cantidadMoto,moto,sucursal);
					detalleMotosList.add(detMot);
				}
			}else{
				FacesContext.getCurrentInstance().addMessage(
						mensaje.getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Atención",
								"El producto requerido ya fue agregado. Modifique el existente, o elimínelo y vuelva a cargarlo."));
			}	
			chequearHabilitado();
		} catch (NoHayStockSuficienteException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Atención",
							e.getMessage()));

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							"Ha ocurrido un error al agregar el producto. Por favor comunicarse con el administrador"));
		}
		return null;
	}

	private boolean motoYaExistente(Integer id) {
		for (DetalleMoto detMot : detalleMotosList) {
			if(detMot.getMoto().getId() == id){
				return true;
			}
		}
		return false;
	}

	private boolean repuestoYaExistente(Integer id) {
		for (DetalleRepuesto detRep : detalleRepuestosList) {
			if(detRep.getRepuesto().getId() == id){
				return true;
			}
		}
		return false;
	}

	public String eliminarFila(DetalleRepuesto det) {
		detalleRepuestosList.remove(det);
		chequearHabilitado();
		return null;
	}

	public String eliminarFilaMoto(DetalleMoto det) {
		detalleMotosList.remove(det);
		chequearHabilitado();
		return null;
	}
	//    public void onEdit(RowEditEvent event) {  
	//		try {
	//			if(stockMotosService.validarStockMoto(cantidadMoto,moto,sucursal)){
	//				DetalleMoto detMot = new DetalleMoto(cantidadRepuesto,moto);
	//				detalleMotosList.add(detMot);
	//			}
	//		} catch (NoHayStockSuficienteException e) {
	//			e.printStackTrace();
	//			FacesContext.getCurrentInstance().addMessage(
	//					mensaje.getClientId(),
	//					new FacesMessage(FacesMessage.SEVERITY_INFO,
	//							"Atención",
	//							e.getMessage()));
	//
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			FacesContext.getCurrentInstance().addMessage(
	//					mensaje.getClientId(),
	//					new FacesMessage(FacesMessage.SEVERITY_ERROR,
	//							"Error",
	//							"Ha ocurrido un error al agregar el producto. Por favor comunicarse con el administrador"));
	//		}
	//    }  
	//    
	//	public void onCancel(RowEditEvent event) {  
	//		FacesMessage msg = new FacesMessage("Repuesto eliminado del pedido");   
	//		FacesContext.getCurrentInstance().addMessage(null, msg); 
	//		detalleRepuestosList.remove((DetalleRepuesto) event.getObject());
	//	}
	//
	//	public void onCancelMoto(RowEditEvent event) {  
	//		FacesMessage msg = new FacesMessage("Moto eliminada del pedido");   
	//		FacesContext.getCurrentInstance().addMessage(null, msg); 
	//		detalleMotosList.remove((DetalleMoto) event.getObject());
	//	}

	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Sucursal> getSucursales() {
		return sucursales;
	}
	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Moto getMoto() {
		return moto;
	}
	public void setMoto(Moto moto) {
		this.moto = moto;
	}
	public Repuesto getRepuesto() {
		return repuesto;
	}
	public void setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}
	public Integer getCantidadRepuesto() {
		return cantidadRepuesto;
	}
	public void setCantidadRepuesto(Integer cantidadRepuesto) {
		this.cantidadRepuesto = cantidadRepuesto;
	}
	public Integer getCantidadMoto() {
		return cantidadMoto;
	}
	public void setCantidadMoto(Integer cantidadMoto) {
		this.cantidadMoto = cantidadMoto;
	}

	public UIComponent getMensaje() {
		return mensaje;
	}

	public void setMensaje(UIComponent mensaje) {
		this.mensaje = mensaje;
	}

	public ClienteBackingService getClienteBackingService() {
		return clienteBackingService;
	}

	public void setClienteBackingService(ClienteBackingService clienteBackingService) {
		this.clienteBackingService = clienteBackingService;
	}

	public SucursalBackingService getSucursalBackingService() {
		return sucursalBackingService;
	}

	public void setSucursalBackingService(SucursalBackingService sucursalBackingService) {
		this.sucursalBackingService = sucursalBackingService;
	}

	public List<Repuesto> getRepuestos() {
		return repuestos;
	}

	public void setRepuestos(List<Repuesto> repuestos) {
		this.repuestos = repuestos;
	}

	public DetallePedidoRepuestosService getDetallePedidoRepuestosService() {
		return detallePedidoRepuestosService;
	}

	public void setDetallePedidoRepuestosService(DetallePedidoRepuestosService detallePedidoRepuestosService) {
		this.detallePedidoRepuestosService = detallePedidoRepuestosService;
	}

	public List<DetalleRepuesto> getDetalleRepuestosList() {
		return detalleRepuestosList;
	}

	public List<DetalleMoto> getDetalleMotosList() {
		return detalleMotosList;
	}

	public List<Moto> getMotos() {
		return motos;
	}

	public void setMotos(List<Moto> motos) {
		this.motos = motos;
	}

	public MotoService getMotoService() {
		return motoService;
	}

	public void setMotoService(MotoService motoService) {
		this.motoService = motoService;
	}

	public StockMotosService getStockMotosService() {
		return stockMotosService;
	}

	public void setStockMotosService(StockMotosService stockMotosService) {
		this.stockMotosService = stockMotosService;
	}

	public DetallePedidoMotosService getDetallePedidoMotosService() {
		return detallePedidoMotosService;
	}

	public void setDetallePedidoMotosService(DetallePedidoMotosService detallePedidoMotosService) {
		this.detallePedidoMotosService = detallePedidoMotosService;
	}

	public MovimientoStockMotoService getMovimientoStockMotoService() {
		return movimientoStockMotoService;
	}

	public void setMovimientoStockMotoService(MovimientoStockMotoService movimientoStockMotoService) {
		this.movimientoStockMotoService = movimientoStockMotoService;
	}

	public Boolean getDesactivar() {
		return desactivar;
	}

	public void setDesactivar(Boolean desactivar) {
		this.desactivar = desactivar;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getStockActualRep() {
		return stockActualRep;
	}

	public void setStockActualRep(Integer stockActualRep) {
		this.stockActualRep = stockActualRep;
	}

	public Integer getStockActualMoto() {
		return stockActualMoto;
	}

	public void setStockActualMoto(Integer stockActualMoto) {
		this.stockActualMoto = stockActualMoto;
	}

	public Integer getPrecioActualRep() {
		return precioActualRep;
	}

	public void setPrecioActualRep(Integer precioActualRep) {
		this.precioActualRep = precioActualRep;
	}

	public Integer getPrecioActualMoto() {
		return precioActualMoto;
	}

	public void setPrecioActualMoto(Integer precioActualMoto) {
		this.precioActualMoto = precioActualMoto;
	}

	public Sucursal getSucursalPrevia() {
		return sucursalPrevia;
	}

	public void setSucursalPrevia(Sucursal sucursalPrevia) {
		this.sucursalPrevia = sucursalPrevia;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}
	
}
