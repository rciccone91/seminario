package edu.utn.frgp.laboratoriov.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import edu.utn.frgp.laboratoriov.common.CommonHelper;
import edu.utn.frgp.laboratoriov.db.ConexionDB;
import edu.utn.frgp.laboratoriov.domain.*;
import edu.utn.frgp.laboratoriov.exceptions.PropiedadNoEncontradaException;
import edu.utn.frgp.laboratoriov.web.ResultadoPropiedades;

public class PropiedadesDao {

	public List<Propiedad> getUltimasPropiedades(){

		Connection connection = null;
		ResultSet rs = null;
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		try {
			connection = ConexionDB.getConexion();
			Statement st = (Statement) connection.createStatement();
			rs = st.executeQuery("select * from final_labov.propiedades order by create_date desc limit 6;");
			while (rs.next()) {
				propiedades.add(buildPropiedad(rs));
			}

		} catch (SQLException s) {
			System.out.println("Error: ");
			s.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: ");
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		return propiedades;
	}

	public Propiedad getPropiedadById(Integer id){

		Connection connection = null;
		ResultSet rs = null;
		Propiedad prop = new Propiedad();
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement st = (PreparedStatement) connection.prepareStatement("select * from final_labov.propiedades where propiedad_id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				prop = buildPropiedad(rs);
			}else{
				throw new PropiedadNoEncontradaException("No se encontró la propiedad requerida. Comuniquese con el administrador.");
			}

		} catch (SQLException s) {
			System.out.println("Error: ");
			s.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: ");
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		return prop;
	}


	private Propiedad buildPropiedad(ResultSet rs) {

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

	public List<Propiedad> getPropiedadesFiltradas(String ciudad, Integer tipoProp, Integer ambientes, Integer operacion) {
		Connection connection = null;
		ResultSet rs = null;
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		try {
			int param = 1;
			StringBuffer query = new StringBuffer("select * from final_labov.propiedades as prop where prop.tipo_de_operacion_id = ? ");
			if(tipoProp != null && tipoProp != 0){
				query.append("and prop.tipo_de_propiedad_id = ? ");
			} 
			if(ambientes != null && ambientes != 0){
				if(ambientes != 5){
					query.append("and prop.ambientes = ? ");
				}else{
					query.append("and prop.ambientes >= ? ");
				}
			} 
			if(ciudad != null && ciudad.trim().length() > 0){
				query.append("and prop.ciudad_id in (select ciudad_id from final_labov.ciudad where upper(nombre) like ?)");
			} 

			System.out.println("Query: "+query.toString());
			connection = ConexionDB.getConexion();
			PreparedStatement st = (PreparedStatement) 
					connection.prepareStatement(query.toString());
			
			st.setInt(param, operacion);
			System.out.println("Operacion: "+ operacion);
			param = param+1;
			
			if(tipoProp != null && tipoProp != 0){
				st.setInt(param, tipoProp);
				param = param+1;
				System.out.println("TipoProp: "+ tipoProp);
			} 
			if(ambientes != null && ambientes != 0){
				st.setInt(param, ambientes);
				param = param+1;
				System.out.println("Ambientes: "+ ambientes);
			} 
			if(ciudad != null && ciudad.trim().length() > 0){
				st.setString(param,"%"+ ciudad.trim().toUpperCase()+"%");
				param = param+1;
				System.out.println("Ciudad: "+ ciudad.trim().toUpperCase());
			} 

			rs = st.executeQuery();
			while (rs.next()) {
				propiedades.add(buildPropiedad(rs));
			}

		} catch (SQLException s) {
			System.out.println("Error: ");
			s.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		return propiedades;
	}

	public List<Propiedad> getPropiedadesByOperacion(Integer op) {
		Connection connection = null;
		ResultSet rs = null;
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		try {
			StringBuffer query = new StringBuffer("select * from final_labov.propiedades where tipo_de_operacion_id = ? ");
			connection = ConexionDB.getConexion();
			PreparedStatement st = (PreparedStatement)connection.prepareStatement(query.toString());
			st.setInt(1, op);
			rs = st.executeQuery();
			while (rs.next()) {
				propiedades.add(buildPropiedad(rs));
			}

		} catch (SQLException s) {
			System.out.println("Error: ");
			s.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		return propiedades;
	}
}
