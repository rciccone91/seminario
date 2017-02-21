package edu.utn.frgp.laboratoriov.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import edu.utn.frgp.laboratoriov.common.CommonHelper;
import edu.utn.frgp.laboratoriov.db.ConexionDB;
import edu.utn.frgp.laboratoriov.domain.Propiedad;
import edu.utn.frgp.laboratoriov.domain.TipoDePropiedad;
import edu.utn.frgp.laboratoriov.domain.Usuario;
import edu.utn.frgp.laboratoriov.exceptions.NoSePudoRealizarLoginException;
import edu.utn.frgp.laboratoriov.exceptions.PropiedadNoEncontradaException;
import edu.utn.frgp.laboratoriov.exceptions.UsuarioYaExistenteException;

public class UsuarioDao {

	public void saveUsuario(Usuario u) throws SQLException,Exception{


		//chequear que no exista el usuario
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement ps = null;
			String query = "insert into final_labov.usuarios values(?,?,?,?,?,?,?);";
			ps= connection.prepareStatement(query);
			ps.setString(1, u.getUsuario());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getMail());
			ps.setString(4, u.getNombre());
			ps.setString(5, u.getDni());
			ps.setInt(6, u.getRolId());
			ps.setString(7, u.getTelefono());
			ps.executeUpdate();	

		}finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
	}

	public Usuario doLogin(String user, String pass) throws NoSePudoRealizarLoginException {

		Connection connection = null;
		ResultSet rs = null;
		Usuario u = new Usuario();
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement st = (PreparedStatement) 
					connection.prepareStatement("select * from final_labov.usuarios where upper(usuario) = ? and PASSWORD = ?");
			st.setString(1, user);
			st.setString(2, pass);
			rs = st.executeQuery();
			if(rs.next()) {
				u = buildUsuario(rs);
			}else{
				throw new NoSePudoRealizarLoginException("El usuario o la password ingresada son incorrectos. Por favor, vuelva a intentarlo.");
			}

		} catch (SQLException s) {
			System.out.println("Error: ");
			s.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		return u;
	}

	private Usuario buildUsuario(ResultSet rs) {
		Usuario u = new Usuario();

		try {
			u.setDni(rs.getString("dni"));
			u.setMail(rs.getString("mail"));
			u.setNombre(rs.getString("nombre"));
			u.setPassword(rs.getString("PASSWORD"));
			u.setRolId(rs.getInt("rol_id"));
			u.setTelefono(rs.getString("telefono"));
			u.setUsuario(rs.getString("USUARIO"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public Boolean getUserById(String user) throws UsuarioYaExistenteException {
		Connection connection = null;
		ResultSet rs = null;
		Usuario u = new Usuario();
		try {
			connection = ConexionDB.getConexion();
			PreparedStatement st = (PreparedStatement) 
					connection.prepareStatement("select * from final_labov.usuarios where upper(usuario) = ?");
			st.setString(1, user.trim().toUpperCase());
			rs = st.executeQuery();
			if(rs.next()) {
				throw new UsuarioYaExistenteException("El usuario ingresado ya existe. Por favor ingrese otro.");
			}

		} catch (SQLException s) {
			System.out.println("Error: ");
			s.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		return true;
	}
}
