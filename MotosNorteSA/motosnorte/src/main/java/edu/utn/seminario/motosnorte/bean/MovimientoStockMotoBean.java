package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

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

	private List<Sucursal> sucursales;

	@ManagedProperty("#{sucursalBackingService}")
	SucursalBackingService sucursalBackingService;

	@PostConstruct
	public void init() {
		motoService = new MotoService();
		movimientoStockMotoService = new MovimientoStockMotoService();
		stockMotosService = new StockMotosService();
		usuarioService = new UsuarioService();
		sucursales = sucursalBackingService.listarActivos();
	}

	public List<Moto> completeMoto(String query){
		List<Moto> motos = motoService.listarActivos();
		List<Moto> motosFiltradas = new ArrayList<Moto>();

		for (int i = 0; i < motos.size(); i++) {
			Moto m = motos.get(i);
			if(m.getDescripcion().toLowerCase().contains(query)) {
				motosFiltradas.add(m);
			}
		}
		return motosFiltradas;
	}

	public String guardar(){
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
		}catch (NoSePuedeRegistrarSalidaDeStockException e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Atención",
							e.getMessage()));
			return "movimientoStockMoto.xhtml";
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
			return "movimientoStockMoto.xhtml";
		}
		return "index.xhtml";
	}

	private MovimientoStockMoto armarMovimiento() {
		MovimientoStockMoto mov = new MovimientoStockMoto();
		try {
			mov.setFecha(Calendar.getInstance().getTime());
			if(movimiento.equals(Constants.MOVIMIENTO_STOCK_ENTRADA)){
				mov.setCantidad(cantidad);
			}else{
				mov.setCantidad(Math.abs(cantidad) * -1);
			}
			mov.setMoto(moto);
			mov.setSucursal(sucursal);
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



}
