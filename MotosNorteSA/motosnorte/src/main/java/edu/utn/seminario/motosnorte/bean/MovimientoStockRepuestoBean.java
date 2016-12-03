package edu.utn.seminario.motosnorte.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.MovimientoStockRepuesto;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.domain.StockRepuestos;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.exception.NoSePuedeRegistrarSalidaDeStockException;
import edu.utn.seminario.motosnorte.exception.UsuarioNoEncontradoException;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.helper.SessionHelper;
import edu.utn.seminario.motosnorte.service.MovimientoStockRepuestoService;
import edu.utn.seminario.motosnorte.service.RepuestosService;
import edu.utn.seminario.motosnorte.service.StockRepuestosService;
import edu.utn.seminario.motosnorte.service.SucursalBackingService;
import edu.utn.seminario.motosnorte.service.UsuarioService;

@ManagedBean(name = "movimientoStockRepuestoBean")
@ViewScoped
public class MovimientoStockRepuestoBean {

	private Repuesto repuesto;
	private String movimiento;
	private Usuario usuario;
	private Integer cantidad;
	private Date fecha;
	private Sucursal sucursal;
	private UIComponent mensaje;
	private RepuestosService repuestosService;
	private MovimientoStockRepuestoService movimientoStockRepuestoService;
	private StockRepuestosService stockRepuestosService;
	private UsuarioService usuarioService;
	private String repuestoId;
	private String remito;
	private Integer motivoSalida;

	private List<Sucursal> sucursales;

	@ManagedProperty("#{sucursalBackingService}")
	SucursalBackingService sucursalBackingService;
	private Integer stockActual;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();

		if(paramMap.get("salida")!= null && paramMap.get("salida").toString().equals("true")){
			RequestContext rqContext = RequestContext.getCurrentInstance();
			rqContext.execute("PF('successDialog').show();");
		}else{

			repuestosService = new RepuestosService();
			movimientoStockRepuestoService = new MovimientoStockRepuestoService();
			stockRepuestosService = new StockRepuestosService();
			usuarioService = new UsuarioService();
			sucursales = sucursalBackingService.listarActivos();
			setRepuestoId(paramMap.get("id"));
			if(repuestoId != null){
				repuesto = repuestosService.getById(Integer.parseInt(repuestoId));
			}	
		}
	}

	public Boolean requiereMotivo(){
		if(movimiento!=null && movimiento.equals(Constants.MOVIMIENTO_STOCK_SALIDA)){
			return false;
		}else{
			return true;
		}
	}

	public List<Repuesto> completeRepuesto(String query){
		List<Repuesto> repuestos = repuestosService.listarActivos();
		List<Repuesto> repuestosFiltrados = new ArrayList<Repuesto>();

		for (int i = 0; i < repuestos.size(); i++) {
			Repuesto r = repuestos.get(i);
			if(r.getNombre().toLowerCase().contains(query.toLowerCase())) {
				repuestosFiltrados.add(r);
			}
		}
		return repuestosFiltrados;
	}

	public void onRepuestoSelect(SelectEvent event) {
		repuesto = (Repuesto) event.getObject();
		checkAndUpdateStockActual();
	}

	public void onRepuestoUnSelect(UnselectEvent event){
		repuesto = null;
		checkAndUpdateStockActual();
	}

	public void cambioDeSucursalORepuesto(){
		checkAndUpdateStockActual();
	}

	private void checkAndUpdateStockActual() {
		if(sucursal!= null && repuesto!= null){
			try {
				stockActual = stockRepuestosService.getStockByRepuestoAndSucursal(repuesto,sucursal);
				RequestContext.getCurrentInstance().update("mainForm:stockActual");
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						mensaje.getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error",
								e.getMessage()));
			}

		}else{
			setStockActual(null);
			RequestContext.getCurrentInstance().update("mainForm:stockActual");
		}
	}

	public void guardar(){
		try {
			if(movimiento.equals(Constants.MOVIMIENTO_STOCK_SALIDA)){
				stockRepuestosService.validarSalida(repuesto, sucursal,movimiento);
			}
			//guardo el movimiento
			movimientoStockRepuestoService.guardar(armarMovimiento());

			//actualizo el stock
			if(movimiento.equals(Constants.MOVIMIENTO_STOCK_SALIDA) && stockRepuestosService.validarCantidadSalida(repuesto,sucursal,cantidad) ){
				setCantidad(Math.abs(cantidad) * -1);
			}

			stockRepuestosService.actualizar(repuesto,sucursal,cantidad);

			if(movimiento.equals(Constants.MOVIMIENTO_STOCK_SALIDA)){
				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
				context.redirect(context.getRequestContextPath() + "/movimientoStockRepuesto.xhtml?salida=true");
			}else{
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('successDialog').show();");
			}
		}catch (NoSePuedeRegistrarSalidaDeStockException e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Atención",
							e.getMessage()));
			//			return "movimientoStockRepuesto.xhtml";
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
			//			return "movimientoStockRepuesto.xhtml";
		}
		//		return "index.xhtml";
	}

	private MovimientoStockRepuesto armarMovimiento() {
		MovimientoStockRepuesto mov = new MovimientoStockRepuesto();
		try {
			mov.setFecha(Calendar.getInstance().getTime());
			if(movimiento.equals(Constants.MOVIMIENTO_STOCK_ENTRADA)){
				mov.setMotivoSalida(null);
				mov.setCantidad(cantidad);
			}else{
				mov.setMotivoSalida(motivoSalida);
				mov.setCantidad(Math.abs(cantidad) * -1);
			}
			mov.setRepuesto(repuesto);
			mov.setSucursal(sucursal);
			mov.setRemito(remito);
			mov.setUsuario(usuarioService.getById(SessionHelper.getUserName()));
		} catch (UsuarioNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mov;		
	}


	public Repuesto getRepuesto() {
		return repuesto;
	}

	public void setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public UIComponent getMensaje() {
		return mensaje;
	}

	public void setMensaje(UIComponent mensaje) {
		this.mensaje = mensaje;
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public SucursalBackingService getSucursalBackingService() {
		return sucursalBackingService;
	}

	public void setSucursalBackingService(SucursalBackingService sucursalBackingService) {
		this.sucursalBackingService = sucursalBackingService;
	}

	public String getRepuestoId() {
		return repuestoId;
	}

	public void setRepuestoId(String repuestoId) {
		this.repuestoId = repuestoId;
	}

	public Integer getStockActual() {
		return stockActual;
	}

	public void setStockActual(Integer stockActual) {
		this.stockActual = stockActual;
	}

	public String getRemito() {
		return remito;
	}

	public void setRemito(String remito) {
		this.remito = remito;
	}

	public Integer getMotivoSalida() {
		return motivoSalida;
	}

	public void setMotivoSalida(Integer motivoSalida) {
		this.motivoSalida = motivoSalida;
	}
}
