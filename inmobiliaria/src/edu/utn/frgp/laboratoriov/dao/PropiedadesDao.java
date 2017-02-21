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
	
	CommonHelper helper  = new CommonHelper();

	public List<Propiedad> getUltimasPropiedades(){

		Connection connection = null;
		ResultSet rs = null;
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		try {
			connection = ConexionDB.getConexion();
			Statement st = (Statement) connection.createStatement();
			rs = st.executeQuery("select * from final_labov.propiedades order by create_date desc limit 6;");
			while (rs.next()) {
				propiedades.add(helper.buildPropiedad(rs));
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
				prop = helper.buildPropiedad(rs);
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
				propiedades.add(helper.buildPropiedad(rs));
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
				propiedades.add(helper.buildPropiedad(rs));
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

	public List<Propiedad> getPropiedadesCercanas(Integer ciudadId, Integer propId) {
		Connection connection = null;
		ResultSet rs = null;
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		try {
			StringBuffer query = new StringBuffer("select * from final_labov.propiedades where ciudad_id = ? and propiedad_id <> ? limit 2");
			connection = ConexionDB.getConexion();
			PreparedStatement st = (PreparedStatement)connection.prepareStatement(query.toString());
			st.setInt(1, ciudadId);
			st.setInt(2, propId);
			rs = st.executeQuery();
			while (rs.next()) {
				propiedades.add(helper.buildPropiedad(rs));
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

	public List<Propiedad> getPropiedadesDestacadas() {
		Connection connection = null;
		ResultSet rs = null;
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		try {
			connection = ConexionDB.getConexion();
			Statement st = (Statement) connection.createStatement();
			rs = st.executeQuery("select * from final_labov.propiedades where destacada = 'Y'");
			while (rs.next()) {
				propiedades.add(helper.buildPropiedad(rs));
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
}
