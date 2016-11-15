package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.utn.seminario.motosnorte.domain.CategoriaMoto;
import edu.utn.seminario.motosnorte.domain.Cilindrada;
import edu.utn.seminario.motosnorte.domain.Color;
import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.helper.Constants;
import edu.utn.seminario.motosnorte.service.CategoriaMotoBackingService;
import edu.utn.seminario.motosnorte.service.CategoriaRepuestoBackingService;
import edu.utn.seminario.motosnorte.service.CilindradaBackingService;
import edu.utn.seminario.motosnorte.service.MarcaBackingService;
import edu.utn.seminario.motosnorte.service.MotoBackingService;
import edu.utn.seminario.motosnorte.service.MotoService;
import edu.utn.seminario.motosnorte.service.RepuestosService;


@ManagedBean(name = "productoMotosBean")
@ViewScoped
public class productoMotosBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private MotoService service;
	private MarcaBackingService marcaService;
	private List<Moto> motos;
	private List<Marca> marcas;
	
	@PostConstruct
	public void init() {
		service = new MotoService();
		marcaService = new MarcaBackingService();
		marcas = marcaService.listar();
		motos = service.listar();	
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

}
