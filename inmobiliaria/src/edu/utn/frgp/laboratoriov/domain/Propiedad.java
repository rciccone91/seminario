package edu.utn.frgp.laboratoriov.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Propiedad {
	
	private Integer id;
	private String direccion;
	private String desc;
	private Integer ambientes;
	private Integer banios;
	private Integer superficieCubierta;
	private Integer precio;
	private TipoDeOperacion tipoDeOperacion;
	private TipoDePropiedad tipoDePropiedad;
	private Ciudad ciudad;
	// Nuevos atributos - agregar al documento
	private Integer superficieTotal;
	private Integer habitaciones;
	private Date fechaDeConstruccion;
	private Double latitud;
	private Double longitud;
	//
	private List<Imagen> imagenes;
	private List<Caracteristica> caracteristicas;
	private Boolean activo;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;
	private Boolean reservada;
	
	public Propiedad() {
		super();
	}
	
	

	public Propiedad(Integer id, String desc, Integer ambientes,
			Integer banios, Integer superficieCubierta, Integer precio,
			TipoDeOperacion tipoDeOperacion, TipoDePropiedad tipoDePropiedad,
			Boolean activo, Date createDate, String createUser,
			Date updateDate, String updateUser, Boolean reservada) {
		super();
		this.id = id;
		this.desc = desc;
		this.ambientes = ambientes;
		this.banios = banios;
		this.superficieCubierta = superficieCubierta;
		this.precio = precio;
		this.tipoDeOperacion = tipoDeOperacion;
		this.tipoDePropiedad = tipoDePropiedad;
		this.activo = activo;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.reservada = reservada;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getAmbientes() {
		return ambientes;
	}

	public void setAmbientes(Integer ambientes) {
		this.ambientes = ambientes;
	}

	public Integer getBanios() {
		return banios;
	}

	public void setBanios(Integer banios) {
		this.banios = banios;
	}

	public Integer getSuperficieCubierta() {
		return superficieCubierta;
	}

	public void setSuperficieCubierta(Integer superficieCubierta) {
		this.superficieCubierta = superficieCubierta;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public TipoDeOperacion getTipoDeOperacion() {
		return tipoDeOperacion;
	}

	public void setTipoDeOperacion(TipoDeOperacion tipoDeOperacion) {
		this.tipoDeOperacion = tipoDeOperacion;
	}

	public TipoDePropiedad getTipoDePropiedad() {
		return tipoDePropiedad;
	}

	public void setTipoDePropiedad(TipoDePropiedad tipoDePropiedad) {
		this.tipoDePropiedad = tipoDePropiedad;
	}

	public Boolean getActivo() {
		return activo;
	}

//	public void setActivo(String ac) {
//		if("SI".equals(ac.toUpperCase())){
//			this.activo = true;
//		}
//		else{
//			this.activo = false;
//		}
//		
//	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public Integer getSuperficieTotal() {
		return superficieTotal;
	}



	public void setSuperficieTotal(Integer superficieTotal) {
		this.superficieTotal = superficieTotal;
	}



	public Integer getHabitaciones() {
		return habitaciones;
	}



	public void setHabitaciones(Integer habitaciones) {
		this.habitaciones = habitaciones;
	}



	public Date getFechaDeConstruccion() {
		return fechaDeConstruccion;
	}



	public void setFechaDeConstruccion(Date fechaDeConstruccion) {
		this.fechaDeConstruccion = fechaDeConstruccion;
	}



	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public List<String> getTodas() {
		List<String> lista = new ArrayList<String>();
		for (Imagen imagen : imagenes) {
			lista.add(imagen.getArchivo());
		}
		return lista;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}


	public Imagen getPrimera(){
		if(this.imagenes.size() >0 ){
			return this.imagenes.get(0);
		}else{
			return new Imagen(0,"default2.png",null,true);
		}
		
	}

	public Ciudad getCiudad() {
		return ciudad;
	}



	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}



	public Double getLatitud() {
		return latitud;
	}



	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}



	public Double getLongitud() {
		return longitud;
	}



	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}



	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}



	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}



	public Boolean getReservada() {
		return reservada;
	}



	public void setReservada(Boolean reservada) {
		this.reservada = reservada;
	}
	
	
	
	

}
