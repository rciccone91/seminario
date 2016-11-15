package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import edu.utn.seminario.motosnorte.domain.Cliente;
import edu.utn.seminario.motosnorte.domain.DetallePedidoMotos;
import edu.utn.seminario.motosnorte.domain.DetallePedidoRepuestos;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.Pedido;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.service.ClienteBackingService;
import edu.utn.seminario.motosnorte.service.RepuestoBackingService;
import edu.utn.seminario.motosnorte.service.SucursalBackingService;

@ManagedBean(name = "notaDePedidoBean")
@ViewScoped
public class NotaDePedidoBean implements Serializable{

	private List<Cliente> clientes;
	private Cliente cliente;
	private List<Sucursal> sucursales;
	private Sucursal sucursal;
	private Moto moto;
	private Repuesto repuesto;
	private DetallePedidoRepuestos detalleRepuesto;
	private DetallePedidoMotos detalleMoto;
	private List<DetallePedidoRepuestos> detalleRepuestos;
	private List<DetallePedidoMotos> detalleMotos;
	private Integer cantidadRepuesto;
	private Integer cantidadMoto;
	private UIComponent mensaje;
	private Pedido pedido;
	
	@ManagedProperty("#{clienteBackingService}")
	ClienteBackingService clienteBackingService;
	
	@ManagedProperty("#{sucursalBackingService}")
	SucursalBackingService sucursalBackingService;
	
	@ManagedProperty("#{repuestoBackingService}")
	RepuestoBackingService repuestoBackingService;

	@PostConstruct
	public void init() {
		clientes = clienteBackingService.listar();
		sucursales = sucursalBackingService.listarActivos();
		if(pedido == null){
			pedido = new Pedido();
		}
		detalleRepuesto = new DetallePedidoRepuestos();
		detalleRepuestos = new ArrayList<DetallePedidoRepuestos>();
		
	}

	public void guardar(){
		
	}
	
	public void createNew() {
		if(detalleRepuestos.contains(detalleRepuesto)){
			 FacesMessage msg = new FacesMessage("Duplicado", "Este repuesto ya fue agregado");
			 FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else
		{
			detalleRepuesto = new DetallePedidoRepuestos();
			detalleRepuesto.setCantidad(cantidadRepuesto);
			detalleRepuesto.setRepuesto(repuesto);
			detalleRepuesto.setNotaPedido(pedido);
			detalleRepuestos.add(detalleRepuesto);
			detalleRepuesto = new DetallePedidoRepuestos();
		}
	}

	public String reinit() {
		detalleRepuesto = new DetallePedidoRepuestos();
		return null;
	}


	public List<Repuesto> completeRepuesto(String query){
		List<Repuesto> repuestos = repuestoBackingService.listar();
		List<Repuesto> repuestosFiltrados = new ArrayList<Repuesto>();

		for (int i = 0; i < repuestos.size(); i++) {
			Repuesto r = repuestos.get(i);
			if(r.getNombre().toLowerCase().contains(query)) {
				repuestosFiltrados.add(r);
			}
		}
		return repuestosFiltrados;
	}

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
	public List<DetallePedidoRepuestos> getDetalleRepuestos() {
		return detalleRepuestos;
	}
	public void setDetalleRepuestos(List<DetallePedidoRepuestos> detalleRepuestos) {
		this.detalleRepuestos = detalleRepuestos;
	}
	public List<DetallePedidoMotos> getDetalleMotos() {
		return detalleMotos;
	}
	public void setDetalleMotos(List<DetallePedidoMotos> detalleMotos) {
		this.detalleMotos = detalleMotos;
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

	public DetallePedidoRepuestos getDetalleRepuesto() {
		return detalleRepuesto;
	}

	public void setDetalleRepuesto(DetallePedidoRepuestos detalleRepuesto) {
		this.detalleRepuesto = detalleRepuesto;
	}

	public DetallePedidoMotos getDetalleMoto() {
		return detalleMoto;
	}

	public void setDetalleMoto(DetallePedidoMotos detalleMoto) {
		this.detalleMoto = detalleMoto;
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

	public RepuestoBackingService getRepuestoBackingService() {
		return repuestoBackingService;
	}

	public void setRepuestoBackingService(RepuestoBackingService repuestoBackingService) {
		this.repuestoBackingService = repuestoBackingService;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
