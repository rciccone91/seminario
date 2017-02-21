package edu.utn.frgp.laboratoriov.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import edu.utn.frgp.laboratoriov.db.ConexionDB;
import edu.utn.frgp.laboratoriov.domain.Ciudad;
import edu.utn.frgp.laboratoriov.domain.TipoDePropiedad;


public class TipoDePropiedadDao {
	
	public List<TipoDePropiedad> getTiposDePropiedades(){

		Connection connection = null;
		ResultSet rs = null;
		List<TipoDePropiedad> lista = new ArrayList<TipoDePropiedad>();
		try {
			connection = ConexionDB.getConexion();
			Statement st = (Statement) connection.createStatement();
			rs = st.executeQuery("select * from final_labov.tipodepropiedad");
			while (rs.next()) {
				TipoDePropiedad tipo = new TipoDePropiedad();
				tipo.setId(rs.getInt("tipo_de_propiedad_id"));
				tipo.setDescripcion(rs.getString("descripcion"));
				lista.add(tipo);
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
		return lista;
	}

	public TipoDePropiedad getTipoDePropiedadById(int id) {

		Connection connection = null;
		ResultSet rs = null;
		TipoDePropiedad prop = new TipoDePropiedad();
		
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement st = (PreparedStatement) connection.prepareStatement("select * from final_labov.tipodepropiedad where TIPO_DE_PROPIEDAD_ID = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				prop.setId(rs.getInt("tipo_de_propiedad_id"));
				prop.setDescripcion(rs.getString("descripcion"));
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
}