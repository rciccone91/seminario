package edu.utn.seminario.motosnorte.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import edu.utn.seminario.motosnorte.domain.CategoriaMoto;
import edu.utn.seminario.motosnorte.service.CategoriaMotoBackingService;

@FacesConverter("categoriaMotoConverter")
public class CategoriaMotoConverter implements Converter{

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
            	CategoriaMotoBackingService service = (CategoriaMotoBackingService) fc.getExternalContext().getApplicationMap().get("categoriaMotoBackingService");
                return service.getById(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversi�n", "No es una categor�a v�lida"));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null && !object.toString().isEmpty()) {
            return String.valueOf(((CategoriaMoto) object).getId());
        }
        else {
            return "";
        }
    }   

}
