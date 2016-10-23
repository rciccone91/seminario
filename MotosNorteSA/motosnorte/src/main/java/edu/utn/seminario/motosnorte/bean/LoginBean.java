package edu.utn.seminario.motosnorte.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import edu.utn.seminario.motosnorte.domain.Usuario;
import edu.utn.seminario.motosnorte.exception.UsuarioOContraseñaIncorrectoException;
import edu.utn.seminario.motosnorte.helper.SessionHelper;
import edu.utn.seminario.motosnorte.service.UsuarioService;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable{

	private String user;
	private String password;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	//validate login
	public String login() {
		try {
			UsuarioService service = new UsuarioService();
			Usuario usuario;
			usuario = (Usuario) service.login(user, password);

			HttpSession session = SessionHelper.getSession();
			session.setAttribute("username", user);
			session.setAttribute("rol", usuario.getRol());
			return "index.html";
		}catch (UsuarioOContraseñaIncorrectoException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Atención",
							e.getMessage()));
			return "login.xhtml";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							e.getMessage()));
			return "login.xhtml";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionHelper.getSession();
		session.invalidate();
		return "login.xhtml";
	}



}
