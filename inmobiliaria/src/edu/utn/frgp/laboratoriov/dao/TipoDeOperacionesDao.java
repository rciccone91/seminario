package edu.utn.frgp.laboratoriov.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import edu.utn.frgp.laboratoriov.db.ConexionDB;
import edu.utn.frgp.laboratoriov.domain.TipoDeOperacion;

public class TipoDeOperacionesDao {

	public TipoDeOperacion getTipoDeOperacionById(int id) {

		Connection connection = null;
		ResultSet rs = null;
		TipoDeOperacion op = new TipoDeOperacion();
		
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement st = (PreparedStatement) connection.prepareStatement("select * from final_labov.tipodeoperacion where TIPO_DE_OPERACION_ID = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				op.setId(rs.getInt("TIPO_DE_OPERACION_ID"));
				op.setDescripcion(rs.getString("descripcion"));
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
		
		return op;
	}

}
