package edu.utn.frgp.laboratoriov.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import edu.utn.frgp.laboratoriov.common.CommonHelper;
import edu.utn.frgp.laboratoriov.db.ConexionDB;
import edu.utn.frgp.laboratoriov.domain.Propiedad;
import edu.utn.frgp.laboratoriov.domain.PropiedadFavorita;
import edu.utn.frgp.laboratoriov.domain.Reservas;
import edu.utn.frgp.laboratoriov.domain.Usuario;
import edu.utn.frgp.laboratoriov.exceptions.PropiedadNoEncontradaException;

public class PropiedadFavoritaDao {

	public void saveFavorita(PropiedadFavorita p) throws SQLException,Exception{

		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement ps = null;
			String query = "insert into final_labov.propiedadesfavoritas(propiedad_id,usuario,creation_date) values(?,?,?)";
			ps= connection.prepareStatement(query);
			ps.setInt(1,p.getPropiedad().getId());
			ps.setString(2, p.getUsuario().getUsuario());
			ps.setDate(3, null);
			ps.executeUpdate();	

		}finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	public Boolean isPropiedadFavorita(Integer propId,String usuario){
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement st = (PreparedStatement) 
					connection.prepareStatement("select * from propiedadesfavoritas where propiedad_id = ? and usuario = ?");
			st.setInt(1, propId);
			st.setString(2, usuario);
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

	public List<Propiedad> getFavoritasByUser(Usuario usuario) {
		Connection connection = null;
		ResultSet rs = null;
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		PropiedadesDao propDao = new PropiedadesDao();
		try {
			StringBuffer query = new StringBuffer("select * from final_labov.propiedadesfavoritas where usuario = ?");
			connection = ConexionDB.getConexion();
			PreparedStatement st = (PreparedStatement)connection.prepareStatement(query.toString());
			st.setString(1, usuario.getUsuario());
			rs = st.executeQuery();
			while (rs.next()){
				Propiedad propiedad = new Propiedad();
				propiedad = propDao.getPropiedadById(rs.getInt("propiedad_id"));
				propiedades.add(propiedad);
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
