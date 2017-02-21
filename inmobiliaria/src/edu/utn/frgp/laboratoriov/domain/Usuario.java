package edu.utn.frgp.laboratoriov.domain;

public class Usuario {
	
	private String usuario;
	private String password;
	private String mail;
	private String nombre;
	private String dni;
	private Integer rolId;
	private String telefono;
	private Boolean admin;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Integer getRolId() {
		return rolId;
	}
	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Boolean getAdmin() {
		admin = this.rolId.equals(2);
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
}
