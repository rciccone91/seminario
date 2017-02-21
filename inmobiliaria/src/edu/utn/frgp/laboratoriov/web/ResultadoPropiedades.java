package edu.utn.frgp.laboratoriov.web;

import java.util.Comparator;
import java.util.List;

import edu.utn.frgp.laboratoriov.domain.Propiedad;

public class ResultadoPropiedades {

	private Integer pagina;
	private List<Propiedad> propiedades;

	public ResultadoPropiedades(Integer pagina, List<Propiedad> propiedades) {
		super();
		this.pagina = pagina;
		this.propiedades = propiedades;
	}

	public Integer getPagina() {
		return pagina;
	}
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	public List<Propiedad> getPropiedades() {
		return propiedades;
	}
	public void setPropiedades(List<Propiedad> propiedades) {
		this.propiedades = propiedades;
	}
}

