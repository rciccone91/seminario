package edu.utn.seminario.motosnorte.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import edu.utn.seminario.motosnorte.domain.Cilindrada;
import edu.utn.seminario.motosnorte.service.CilindradaBackingService;

@FacesConverter("cilindradaConverter")
public class CilindradaConverter implements Converter{
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
			try {
				CilindradaBackingService service = (CilindradaBackingService) fc.getExternalContext().getApplicationMap().get("cilindradaBackingService");
				return service.getById(Integer.parseInt(value));
			} catch(NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es una cilindrada válida"));
			}
		}
		else {
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null && !object.toString().isEmpty()) {
            return String.valueOf(((Cilindrada) object).getId());
        }
        else {
            return "";
        }
	}  
}
