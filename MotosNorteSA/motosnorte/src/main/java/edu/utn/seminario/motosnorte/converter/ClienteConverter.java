package edu.utn.seminario.motosnorte.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import edu.utn.seminario.motosnorte.domain.Cliente;
import edu.utn.seminario.motosnorte.exception.ClienteNoEncontradoException;
import edu.utn.seminario.motosnorte.service.ClienteBackingService;

@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
			try {
				ClienteBackingService service = (ClienteBackingService) fc.getExternalContext().getApplicationMap().get("clienteBackingService");
				return service.getById(Integer.parseInt(value));
			} catch(ClienteNoEncontradoException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No se encontro el cliente requerido"));
			} 
			catch(NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es un cliente válido"));
			}
		}
		else {
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
			return String.valueOf(((Cliente) object).getId());
		}
		else {
			return null;
		}
	}   
}
