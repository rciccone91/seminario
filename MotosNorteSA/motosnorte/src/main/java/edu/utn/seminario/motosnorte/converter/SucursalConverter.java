package edu.utn.seminario.motosnorte.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import edu.utn.seminario.motosnorte.domain.Moto;
import edu.utn.seminario.motosnorte.domain.Sucursal;
import edu.utn.seminario.motosnorte.service.MotoBackingService;
import edu.utn.seminario.motosnorte.service.SucursalBackingService;

@FacesConverter("sucursalConverter")
public class SucursalConverter implements Converter{
	
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
            	SucursalBackingService service = (SucursalBackingService) fc.getExternalContext().getApplicationMap().get("sucursalBackingService");
                return service.getById(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es una sucursal válida"));
            }
            catch(Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "Error al consultar sucursal"));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Sucursal) object).getId());
        }
        else {
            return null;
        }
    }   

}
