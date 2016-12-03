package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
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
import edu.utn.seminario.motosnorte.domain.MovimientoStockMoto;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.exception.NoSePuedeRegistrarSalidaDeStockException;
import edu.utn.seminario.motosnorte.exception.UsuarioNoEncontradoException;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.helper.SessionHelper;
import edu.utn.seminario.motosnorte.service.CategoriaMotoBackingService;
import edu.utn.seminario.motosnorte.service.MotoService;
import edu.utn.seminario.motosnorte.service.MovimientoStockMotoService;
import edu.utn.seminario.motosnorte.service.StockMotosService;
import edu.utn.seminario.motosnorte.service.SucursalBackingService;
import edu.utn.seminario.motosnorte.service.UsuarioService;

@SuppressWarnings("serial")
@ManagedBean(name = "movimientoStockMotoBean")
@ViewScoped
public class MovimientoStockMotoBean implements Serializable{

	private Moto moto;
	private String movimiento;
	private Usuario usuario;
	private Integer cantidad;
	private Date fecha;
	private Sucursal sucursal;
	private UIComponent mensaje;
	private MotoService motoService;
	private MovimientoStockMotoService movimientoStockMotoService;
	private StockMotosService stockMotosService;
	private UsuarioService usuarioService;
	private String motoId;
	private Integer stockActual;
	private String remito;
	private Integer motivoSalida;

	private List<Sucursal> sucursales;

	@ManagedProperty("#{sucursalBackingService}")
	SucursalBackingService sucursalBackingService;

	@PostConstruct
	public void init() {

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();

		if(paramMap.get("salida")!= null && paramMap.get("salida").toString().equals("true")){
			RequestContext rqContext = RequestContext.getCurrentInstance();
			rqContext.execute("PF('successDialog').show();");
		}else{

			motoService = new MotoService();
			movimientoStockMotoService = new MovimientoStockMotoService();
			stockMotosService = new StockMotosService();
			usuarioService = new UsuarioService();
			sucursales = sucursalBackingService.listarActivos();
			setMotoId(paramMap.get("id"));
			if(motoId != null){
				moto = motoService.getById(Integer.parseInt(motoId));
			}		
		}
	}

	public List<Moto> completeMoto(String query){
		List<Moto> motos = motoService.listarActivos();
		List<Moto> motosFiltradas = new ArrayList<Moto>();

		for (int i = 0; i < motos.size(); i++) {
			Moto m = motos.get(i);
			if(m.getDescripcion().toLowerCase().contains(query.toLowerCase())) {
				motosFiltradas.add(m);
			}
		}
		return motosFiltradas;
	}

	public Boolean mostrarStockActual(){
		if(sucursal!= null && moto!= null){
			return true;
		}else{
			return false;
		}
	}


	public Boolean requiereMotivo(){
		if(movimiento!=null && movimiento.equals(Constants.MOVIMIENTO_STOCK_SALIDA)){
			return false;
		}else{
			return true;
		}
	}

	public void onMotoSelect(SelectEvent event) {
		//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
		moto = (Moto) event.getObject();
		checkAndUpdateStockActual();
	}

	public void onMotoUnSelect(UnselectEvent event){
		moto = null;
		checkAndUpdateStockActual();
	}

	public void cambioDeSucursalOMoto(){
		checkAndUpdateStockActual();
	}

	private void checkAndUpdateStockActual() {
		if(sucursal!= null && moto!= null){
			try {
				stockActual = stockMotosService.getStockByMotoAndSucursal(moto,sucursal);
				RequestContext.getCurrentInstance().update("mainForm:stockActual");
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						mensaje.getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error",
								e.getMessage()));
			}

		}else{
			stockActual = null;
			RequestContext.getCurrentInstance().update("mainForm:stockActual");
		}
	}

	public void guardar(){
		try {
			if(movimiento.equals(Constants.MOVIMIENTO_STOCK_SALIDA)){
				stockMotosService.validarSalida(moto, sucursal,movimiento);
			}
			//guardo el movimiento
			movimientoStockMotoService.guardar(armarMovimiento());
			//actualizo el stock
			if(movimiento.equals(Constants.MOVIMIENTO_STOCK_SALIDA) && stockMotosService.validarCantidadSalida(moto,sucursal,cantidad)){
				setCantidad(Math.abs(cantidad) * -1);
			}
			stockMotosService.actualizar(moto,sucursal,cantidad);

			if(movimiento.equals(Constants.MOVIMIENTO_STOCK_SALIDA)){
				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
				context.redirect(context.getRequestContextPath() + "/movimientoStockMoto.xhtml?salida=true");
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
			//			return "movimientoStockMoto.xhtml";
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
			//			return "movimientoStockMoto.xhtml";
		}
		//		return "index.xhtml";
	}

	private MovimientoStockMoto armarMovimiento() {
		MovimientoStockMoto mov = new MovimientoStockMoto();
		try {
			mov.setFecha(Calendar.getInstance().getTime());
			if(movimiento.equals(Constants.MOVIMIENTO_STOCK_ENTRADA)){
				mov.setMotivoSalida(null);
				mov.setCantidad(cantidad);
			}else{
				mov.setCantidad(Math.abs(cantidad) * -1);
				mov.setMotivoSalida(motivoSalida);
			}
			mov.setMoto(moto);
			mov.setSucursal(sucursal);
			mov.setRemito(remito);
			mov.setUsuario(usuarioService.getById(SessionHelper.getUserName()));
		} catch (UsuarioNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mov;		
	}

	public UIComponent getMensaje() {
		return mensaje;
	}

	public void setMensaje(UIComponent mensaje) {
		this.mensaje = mensaje;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
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

	public String getMotoId() {
		return motoId;
	}

	public void setMotoId(String motoId) {
		this.motoId = motoId;
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
