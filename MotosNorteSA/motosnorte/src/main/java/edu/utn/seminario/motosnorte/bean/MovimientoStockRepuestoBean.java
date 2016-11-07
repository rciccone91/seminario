package edu.utn.seminario.motosnorte.bean;

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

import edu.utn.seminario.motosnorte.domain.MovimientoStockRepuesto;
import edu.utn.seminario.motosnorte.domain.Repuesto;
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

	private List<Sucursal> sucursales;

	@ManagedProperty("#{sucursalBackingService}")
	SucursalBackingService sucursalBackingService;

	@PostConstruct
	public void init() {
		repuestosService = new RepuestosService();
		movimientoStockRepuestoService = new MovimientoStockRepuestoService();
		stockRepuestosService = new StockRepuestosService();
		usuarioService = new UsuarioService();
		sucursales = sucursalBackingService.listarActivos();
	}
	
	public List<Repuesto> completeRepuesto(String query){
		List<Repuesto> repuestos = repuestosService.listar();
		List<Repuesto> repuestosFiltrados = new ArrayList<Repuesto>();

		for (int i = 0; i < repuestos.size(); i++) {
			Repuesto r = repuestos.get(i);
			if(r.getNombre().toLowerCase().contains(query)) {
				repuestosFiltrados.add(r);
			}
		}
		return repuestosFiltrados;
	}

	public String guardar(){
		try {
			if(movimiento.equals(Constants.MOVIMIENTO_STOCK_SALIDA)){
				stockRepuestosService.validarSalida(repuesto, sucursal,movimiento);
			}
			//guardo el movimiento
			movimientoStockRepuestoService.guardar(armarMovimiento());
			//actualizo el stock
			if(movimiento.equals(Constants.MOVIMIENTO_STOCK_SALIDA)){
				setCantidad(Math.abs(cantidad) * -1);
			}
			stockRepuestosService.actualizar(repuesto,sucursal,cantidad);
		}catch (NoSePuedeRegistrarSalidaDeStockException e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Atención",
							e.getMessage()));
			return "movimientoStockRepuesto.xhtml";
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
			return "movimientoStockRepuesto.xhtml";
		}
		return "index.xhtml";
	}

	private MovimientoStockRepuesto armarMovimiento() {
		MovimientoStockRepuesto mov = new MovimientoStockRepuesto();
		try {
			mov.setFecha(Calendar.getInstance().getTime());
			if(movimiento.equals(Constants.MOVIMIENTO_STOCK_ENTRADA)){
				mov.setCantidad(cantidad);
			}else{
				mov.setCantidad(Math.abs(cantidad) * -1);
			}
			mov.setCantidad(cantidad);
			mov.setRepuesto(repuesto);
			mov.setSucursal(sucursal);
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
}
