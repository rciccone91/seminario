package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.StockMotos;
import edu.utn.seminario.motosnorte.service.MarcaBackingService;
import edu.utn.seminario.motosnorte.service.MotoService;
import edu.utn.seminario.motosnorte.service.StockMotosService;


@ManagedBean(name = "productoMotosBean")
@ViewScoped
public class productoMotosBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private MotoService service;
	private MarcaBackingService marcaService;
	private List<Moto> motos;
	private List<Marca> marcas;
	private List<StockMotos> stockMotos;
	private StockMotosService stockService;
	private UIComponent mensaje;
	
	@PostConstruct
	public void init() {
		service = new MotoService();
		marcaService = new MarcaBackingService();
		marcas = marcaService.listar();
		motos = service.listar();	
	}
	
	public void buscarStock(Moto moto){
		try {
			stockMotos = new ArrayList<StockMotos>();
			stockService = new StockMotosService();
			stockMotos = stockService.getStockByMoto(moto);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					mensaje.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
		}
	}

	public MotoService getService() {
		return service;
	}

	public void setService(MotoService service) {
		this.service = service;
	}

	public List<Moto> getMotos() {
		return motos;
	}

	public void setMotos(List<Moto> motos) {
		this.motos = motos;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean filtrarPorPrecio(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<StockMotos> getStockMotos() {
		return stockMotos;
	}

	public void setStockMotos(List<StockMotos> stockMotos) {
		this.stockMotos = stockMotos;
	}

	public StockMotosService getStockService() {
		return stockService;
	}

	public void setStockService(StockMotosService stockService) {
		this.stockService = stockService;
	}

	public UIComponent getMensaje() {
		return mensaje;
	}

	public void setMensaje(UIComponent mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
