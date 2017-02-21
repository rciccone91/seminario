package edu.utn.frgp.laboratoriov.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.utn.frgp.laboratoriov.db.ConexionDB;
import edu.utn.frgp.laboratoriov.domain.Caracteristica;
import edu.utn.frgp.laboratoriov.domain.Imagen;

public class ImagenDao {

	public List<Imagen> getImagenesByProp(Integer id) {

		Connection connection = null;
		ResultSet rs = null;
		List<Imagen> imagenes = new ArrayList<Imagen>();
		
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement st = 
					(PreparedStatement) connection.prepareStatement("select i.IMAGEN_ID,i.IMAGE_PATH,i.PROPIEDAD_ID "
							+ "from final_labov.imagenes as i "
							+ "inner join propiedades as p "
							+ "on i.propiedad_id = p.propiedad_id and p.propiedad_id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				imagenes.add(new Imagen(rs.getInt("i.IMAGEN_ID"), rs.getString("i.IMAGE_PATH"), 
						rs.getInt("i.PROPIEDAD_ID"), true));
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
		return imagenes;
	}

}
