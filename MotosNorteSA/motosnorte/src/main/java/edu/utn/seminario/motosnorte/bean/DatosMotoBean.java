package edu.utn.seminario.motosnorte.bean;

import java.util.Date;
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

import edu.utn.seminario.motosnorte.domain.CategoriaMoto;
import edu.utn.seminario.motosnorte.domain.Cilindrada;
import edu.utn.seminario.motosnorte.domain.Color;
import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.exception.MotoYaExistenteException;
import edu.utn.seminario.motosnorte.helper.CommonHelper;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.service.CategoriaMotoBackingService;
import edu.utn.seminario.motosnorte.service.CilindradaBackingService;
import edu.utn.seminario.motosnorte.service.MarcaBackingService;
import edu.utn.seminario.motosnorte.service.MotoService;

@ManagedBean(name = "datosMotoBean")
@ViewScoped
public class DatosMotoBean {
	private Integer id;
	private String modelo;
	private Marca marca;
	private Date anio;
	private Integer color;
	private Integer precio;
	private Cilindrada cilindrada;
	private Integer peso;
	private CategoriaMoto categoriaMoto;
	private Boolean activo;
	private MotoService service;
	private UIComponent mensaje;
	private List<CategoriaMoto> categorias;
	private List<Marca> marcas;
	private List<Color> colores;
	private List<Cilindrada> cilindradas;
	private String operacion;
	private Date max;

	
	@ManagedProperty("#{categoriaMotoBackingService}")
	CategoriaMotoBackingService categoriaMotoBackingService;

	@ManagedProperty("#{marcaBackingService}")
	MarcaBackingService marcaBackingService;

	@ManagedProperty("#{cilindradaBackingService}")
	CilindradaBackingService cilindradaBackingService;

	@PostConstruct
	public void init() {
		service = new MotoService();
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		operacion = paramMap.get("op");
		if(operacion != null && operacion.equals(Constants.PARAMETRO_MODIFICAR)){
			id = Integer.parseInt(paramMap.get("id"));
			popularFormulario(service.getById(id));
		}
		categorias = categoriaMotoBackingService.listar();
		marcas = marcaBackingService.listar();
		colores = Color.getColores();
		cilindradas = cilindradaBackingService.listar();
		setMax(CommonHelper.getYesterdayDate());
	}

	private void popularFormulario(Moto moto) {
		id = moto.getId();
		modelo = moto.getModelo();
		marca = moto.getMarca();
		color = moto.getColor();
		precio = moto.getPrecio();
		cilindrada = moto.getCilindrada();
		peso = moto.getPeso();
		categoriaMoto = moto.getCategoriaMoto();
		activo = moto.getActivo();
		anio = moto.getAño();
	}
	
	public Boolean mostrarActivo(){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		operacion = paramMap.get("op");
		if(activo!= null && activo == false && operacion.equals(Constants.PARAMETRO_MODIFICAR)){
			return true;
		}else{
			return false;
		}
	}

	public void guardar(){
		try {
			if(operacion.equals(Constants.PARAMETRO_CREAR)){
				service.guardar(armarMoto());
			}
			if(operacion.equals(Constants.PARAMETRO_MODIFICAR)){
				service.modificar(armarMoto());
			}
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('successDialog').show();");
		}catch (MotoYaExistenteException e) {
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
		}
	}
	
	private Moto armarMoto() {
		Moto moto = new Moto();
		moto.setActivo(activo);
		moto.setAño(anio);
		moto.setCategoriaMoto(categoriaMoto);
		moto.setCilindrada(cilindrada);
		moto.setColor(color);
		moto.setId(id);
		moto.setMarca(marca);
		moto.setModelo(modelo);
		moto.setPeso(peso);
		moto.setPrecio(precio);
		return moto;
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

	public Date getAnio() {
		return anio;
	}

	public void setAnio(Date anio) {
		this.anio = anio;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Cilindrada getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(Cilindrada cilindrada) {
		this.cilindrada = cilindrada;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public CategoriaMoto getCategoriaMoto() {
		return categoriaMoto;
	}

	public void setCategoriaMoto(CategoriaMoto categoriaMoto) {
		this.categoriaMoto = categoriaMoto;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public MotoService getService() {
		return service;
	}

	public void setService(MotoService service) {
		this.service = service;
	}

	public UIComponent getMensaje() {
		return mensaje;
	}

	public void setMensaje(UIComponent mensaje) {
		this.mensaje = mensaje;
	}

	public List<CategoriaMoto> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaMoto> categorias) {
		this.categorias = categorias;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Color> getColores() {
		return colores;
	}

	public void setColores(List<Color> colores) {
		this.colores = colores;
	}

	public List<Cilindrada> getCilindradas() {
		return cilindradas;
	}

	public void setCilindradas(List<Cilindrada> cilindradas) {
		this.cilindradas = cilindradas;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public CategoriaMotoBackingService getCategoriaMotoBackingService() {
		return categoriaMotoBackingService;
	}

	public void setCategoriaMotoBackingService(CategoriaMotoBackingService categoriaMotoBackingService) {
		this.categoriaMotoBackingService = categoriaMotoBackingService;
	}

	public MarcaBackingService getMarcaBackingService() {
		return marcaBackingService;
	}

	public void setMarcaBackingService(MarcaBackingService marcaBackingService) {
		this.marcaBackingService = marcaBackingService;
	}

	public CilindradaBackingService getCilindradaBackingService() {
		return cilindradaBackingService;
	}

	public void setCilindradaBackingService(CilindradaBackingService cilindradaBackingService) {
		this.cilindradaBackingService = cilindradaBackingService;
	}

	public Date getMax() {
		return max;
	}

	public void setMax(Date max) {
		this.max = max;
	}
	
	public Boolean mostrarBotonGuardar(){
		return operacion.equals(Constants.PARAMETRO_CREAR);
	}

	public Boolean mostrarBotonModificar(){
		return operacion.equals(Constants.PARAMETRO_MODIFICAR);
	}
}
