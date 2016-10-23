package edu.utn.seminario.motosnorte.bean;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean(name = "errorBean")
@ViewScoped
public class ErrorBean {

	private String error;
	private UIComponent mensaje;
	
	@PostConstruct
	public void init() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		error= paramMap.get("msg");
		
		context.addMessage(
				mensaje.getClientId(),
				new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error",
						error));
	}
	
	public String confirmar(){
		return "index.xhtml";
	}
}
