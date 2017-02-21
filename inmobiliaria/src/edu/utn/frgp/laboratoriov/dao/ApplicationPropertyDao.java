package edu.utn.frgp.laboratoriov.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import edu.utn.frgp.laboratoriov.db.ConexionDB;
import edu.utn.frgp.laboratoriov.domain.ApplicationProperty;

public class ApplicationPropertyDao {
	
	public ApplicationProperty getApplicationPropertyById(String id){

		Connection connection = null;
		ResultSet rs = null;
		ApplicationProperty appProp = new ApplicationProperty();
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement st = (PreparedStatement) connection.prepareStatement("select * from final_labov.applicationproperties where property = ?");
			st.setString(1, id.toString());
			rs = st.executeQuery();
			if(rs.next()) {
				appProp.setKey(id);
				appProp.setValue(rs.getString("value"));
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
		return appProp;
	}

}
