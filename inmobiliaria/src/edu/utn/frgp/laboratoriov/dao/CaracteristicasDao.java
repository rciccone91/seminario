package edu.utn.frgp.laboratoriov.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.utn.frgp.laboratoriov.db.ConexionDB;
import edu.utn.frgp.laboratoriov.domain.Caracteristica;

public class CaracteristicasDao {

	public List<Caracteristica> getCaracteristicasByProp(Integer id) {
		
		Connection connection = null;
		ResultSet rs = null;
		List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
		
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement st = 
					(PreparedStatement) connection.prepareStatement("SELECT c.CARACTERISTICA_ID, c.DESCRIPCION "
							+ "from final_labov.caracteristicas as c "
							+ "inner join caracteristicasporpropiedad as cp "
							+ "on c.caracteristica_id = cp.caracteristica_id and cp.propiedad_id =?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				caracteristicas.add(new Caracteristica(rs.getInt("c.CARACTERISTICA_ID"), rs.getString("c.DESCRIPCION")));
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
		return caracteristicas;
	}

}
