package edu.utn.frgp.laboratoriov.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import edu.utn.frgp.laboratoriov.dao.CaracteristicasDao;
import edu.utn.frgp.laboratoriov.dao.CiudadDao;
import edu.utn.frgp.laboratoriov.dao.ImagenDao;
import edu.utn.frgp.laboratoriov.dao.TipoDeOperacionesDao;
import edu.utn.frgp.laboratoriov.dao.TipoDePropiedadDao;
import edu.utn.frgp.laboratoriov.domain.Propiedad;

public class CommonHelper {
	
	public static String convertToMySqlBoolean(Boolean val){
		if(val){
			return "Y";
		}else{
			return "N";
		}
	}
	
	public static Boolean convertToJavaBoolean(String val){
		if("Y".equals(val)){
			return true;
		}else{
			return false;
		}
	}
	
	public static Date getYesterdayDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.getTime();	
	}
	
	public Propiedad buildPropiedad(ResultSet rs) {

		CiudadDao ciudadDao = new CiudadDao();
		ImagenDao imagenDao = new ImagenDao();
		TipoDePropiedadDao tipoDePropiedadDao = new TipoDePropiedadDao();
		TipoDeOperacionesDao tipoDeOperacionesDao = new TipoDeOperacionesDao();
		CaracteristicasDao caracteristicasDao = new CaracteristicasDao();
		Propiedad p = new Propiedad();

		try {

			p.setId(rs.getInt("propiedad_id"));
			p.setActivo(CommonHelper.convertToJavaBoolean(rs.getString("active")));
			p.setAmbientes(rs.getInt("ambientes"));
			p.setBanios(rs.getInt("banios"));
			//Setear ciudad
			p.setCiudad(ciudadDao.getCiudadById(rs.getInt("ciudad_id")));
			p.setCreateDate(rs.getDate("create_date"));
			// Usuario de create
			p.setCreateUser(rs.getString("create_user"));
			p.setDesc(rs.getString("descripcion"));
			p.setDireccion(rs.getString("direccion"));
			p.setFechaDeConstruccion(rs.getDate("fecha_de_construccion"));
			p.setHabitaciones(rs.getInt("habitaciones"));
			p.setId(rs.getInt("propiedad_id"));
			p.setLatitud(Double.valueOf(rs.getFloat("latitud")));
			p.setLongitud(Double.valueOf(rs.getFloat("longitud")));
			p.setPrecio(rs.getInt("precio"));
			p.setSuperficieCubierta(rs.getInt("superficie_cubierta"));
			p.setSuperficieTotal(rs.getInt("superficie_total"));
			p.setImagenes(imagenDao.getImagenesByProp(p.getId()));
			// Tipo de Op y Tipo de Prop
			p.setTipoDeOperacion(tipoDeOperacionesDao.getTipoDeOperacionById(rs.getInt("tipo_de_operacion_id")));
			p.setTipoDePropiedad(tipoDePropiedadDao.getTipoDePropiedadById(rs.getInt("tipo_de_propiedad_id")));
			p.setUpdateDate(rs.getDate("update_date"));
			// Usuario de update
			p.setUpdateUser(rs.getString("update_user"));
			p.setCaracteristicas(caracteristicasDao.getCaracteristicasByProp(p.getId()));
			p.setImagenes(imagenDao.getImagenesByProp(p.getId()));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}
}
