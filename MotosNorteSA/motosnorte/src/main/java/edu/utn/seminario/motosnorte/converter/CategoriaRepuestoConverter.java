package edu.utn.seminario.motosnorte.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import edu.utn.seminario.motosnorte.domain.CategoriaRepuesto;
import edu.utn.seminario.motosnorte.domain.Marca;
import edu.utn.seminario.motosnorte.service.CategoriaRepuestoBackingService;
import edu.utn.seminario.motosnorte.service.MarcaBackingService;

@FacesConverter("categoriaRepuestoConverter")
public class CategoriaRepuestoConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
            	CategoriaRepuestoBackingService service = (CategoriaRepuestoBackingService) fc.getExternalContext().getApplicationMap().get("categoriaRepuestoBackingService");
                return service.getById(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es una categoría válida"));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((CategoriaRepuesto) object).getId());
        }
        else {
            return null;
        }
    }   

}