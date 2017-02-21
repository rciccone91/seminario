package edu.utn.frgp.laboratoriov.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.utn.frgp.laboratoriov.db.ConexionDB;
import edu.utn.frgp.laboratoriov.domain.ApplicationProperty;
import edu.utn.frgp.laboratoriov.domain.Properties;
import edu.utn.frgp.laboratoriov.domain.PropiedadFavorita;
import edu.utn.frgp.laboratoriov.domain.Reservas;

public class ReservasDao {
	
	public void saveReserva(Reservas r) throws SQLException,Exception{

		ApplicationPropertyDao appDao = new ApplicationPropertyDao();
		String duracion = appDao.getApplicationPropertyById(Properties.DURACION_RESERVA).getValue();
		
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement ps = null;
			String query = "insert into final_labov.reservas (usuario,fecha_reserva,vencimiento_reserva,propiedad_id,vencida) "
					+ "values (?,current_timestamp(),(current_timestamp() + interval "+ duracion+" second), ?, 'N');";
			ps= connection.prepareStatement(query);
			ps.setString(1, r.getUsuario().getUsuario());
			ps.setInt(2, r.getPropiedad().getId());
			ps.executeUpdate();	

		}finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	public Boolean isReservada(Integer propId){
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement st = (PreparedStatement) 
					connection.prepareStatement("select * from reservas "
							+ "where propiedad_id = ? and current_timestamp() between fecha_reserva "
							+ "and vencimiento_reserva and vencida = 'N';");
			st.setInt(1, propId);
			rs = st.executeQuery();
			if(rs.next()) {
				return true;
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
		return false;
	}

}
