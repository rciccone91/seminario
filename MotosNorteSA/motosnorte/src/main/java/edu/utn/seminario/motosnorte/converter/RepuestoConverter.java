package edu.utn.seminario.motosnorte.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import edu.utn.seminario.motosnorte.domain.Repuesto;
import edu.utn.seminario.motosnorte.service.RepuestoBackingService;
import edu.utn.seminario.motosnorte.service.RepuestosService;

@FacesConverter("repuestoConverter")
public class RepuestoConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0 && !value.equals("Seleccionar..")) {
            try {
            	RepuestosService service = new RepuestosService();
                return service.getById(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversi�n", "No es un repuesto v�lido"));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null && !object.toString().isEmpty()) {
            return String.valueOf(((Repuesto) object).getId());
        }
        else {
            return null;
        }
    }   
}
