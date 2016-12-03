package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
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

import edu.utn.seminario.motosnorte.domain.CategoriaRepuesto;
import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.exception.RepuestoYaExistenteException;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.service.CategoriaRepuestoBackingService;
import edu.utn.seminario.motosnorte.service.MarcaBackingService;
import edu.utn.seminario.motosnorte.service.MotoBackingService;
import edu.utn.seminario.motosnorte.service.RepuestosService;


@ManagedBean(name = "datosRepuestoBean")
@ViewScoped
public class DatosRepuestoBean implements Serializable{

	private Integer id;
	private String modelo;
	private Marca marca;
	private Moto motoCompatible;
	private String descripcion;
	private Integer precio;
	private CategoriaRepuesto categoriaRepuesto;
	private Boolean activo;
	private UIComponent mensaje;
	private List<CategoriaRepuesto> categorias;
	private List<Marca> marcas;
	private List<Moto> motos;
	private String operacion;
	private RepuestosService service;

	@ManagedProperty("#{categoriaRepuestoBackingService}")
	CategoriaRepuestoBackingService categoriaRepuestoBackingService;

	@ManagedProperty("#{marcaBackingService}")
	MarcaBackingService marcaBackingService;

	@ManagedProperty("#{motoBackingService}")
	MotoBackingService motoBackingService;

	@PostConstruct
	public void init() {
		service = new RepuestosService();
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		operacion = paramMap.get("op");
		if(operacion != null && operacion.equals(Constants.PARAMETRO_MODIFICAR)){
			id = Integer.parseInt(paramMap.get("id"));
			popularFormulario(service.getById(id));
		}
		categorias = categoriaRepuestoBackingService.listar();
		motos = motoBackingService.listar();
		marcas = marcaBackingService.listar();
	}

	private void popularFormulario(Repuesto rep) {
		modelo = rep.getModelo();
		marca = rep.getMarca();
		motoCompatible = rep.getMotoCompatible();
		descripcion = rep.getDescripcion();
		precio = rep.getPrecio();
		categoriaRepuesto = rep.getCategoriaRepuesto();
	}

	public void guardar(){
		try {
			if(operacion.equals(Constants.PARAMETRO_CREAR)){
				service.guardar(armarRepuesto());
			}
			if(operacion.equals(Constants.PARAMETRO_MODIFICAR)){
				service.modificar(armarRepuesto());
			}
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('successDialog').show();");
		}catch (RepuestoYaExistenteException e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Atención",
							e.getMessage()));
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
			//			return "datosRepuesto.xhtml";
		}
	}

	private Repuesto armarRepuesto() {
		Repuesto r = new Repuesto();
		r.setId(id);
		r.setActivo(true);
		r.setCategoriaRepuesto(categoriaRepuesto);
		r.setDescripcion(descripcion);
		r.setMarca(marca);
		r.setModelo(modelo);
		r.setMotoCompatible(motoCompatible);
		r.setPrecio(precio);
		return r;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Moto getMotoCompatible() {
		return motoCompatible;
	}

	public void setMotoCompatible(Moto motoCompatible) {
		this.motoCompatible = motoCompatible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public CategoriaRepuesto getCategoriaRepuesto() {
		return categoriaRepuesto;
	}

	public void setCategoriaRepuesto(CategoriaRepuesto categoriaRepuesto) {
		this.categoriaRepuesto = categoriaRepuesto;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public List<CategoriaRepuesto> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaRepuesto> categorias) {
		this.categorias = categorias;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Moto> getMotos() {
		return motos;
	}

	public void setMotos(List<Moto> motos) {
		this.motos = motos;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Boolean mostrarBotonGuardar(){
		return operacion.equals(Constants.PARAMETRO_CREAR);
	}

	public Boolean mostrarBotonModificar(){
		return operacion.equals(Constants.PARAMETRO_MODIFICAR);
	}

	public CategoriaRepuestoBackingService getCategoriaRepuestoBackingService() {
		return categoriaRepuestoBackingService;
	}

	public void setCategoriaRepuestoBackingService(CategoriaRepuestoBackingService categoriaRepuestoBackingService) {
		this.categoriaRepuestoBackingService = categoriaRepuestoBackingService;
	}

	public MarcaBackingService getMarcaBackingService() {
		return marcaBackingService;
	}

	public void setMarcaBackingService(MarcaBackingService marcaBackingService) {
		this.marcaBackingService = marcaBackingService;
	}

	public MotoBackingService getMotoBackingService() {
		return motoBackingService;
	}

	public void setMotoBackingService(MotoBackingService motoBackingService) {
		this.motoBackingService = motoBackingService;
	}

	public UIComponent getMensaje() {
		return mensaje;
	}

	public void setMensaje(UIComponent mensaje) {
		this.mensaje = mensaje;
	}
}
