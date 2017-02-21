package edu.utn.frgp.laboratoriov.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public final class ConexionDB {

	private final static String host = "jdbc:mysql://localhost:3306/";//coneccion;
	private final static String dbName = "final_labov";//nombre de la base
	private final static String user = "root";//usuario de la base
	private final static String pass = "root";//contraseña de la base
	private static Connection conexion = null;
	
	private ConexionDB(){
		conexion = initConexion();
	}
	
	private static Connection initConexion(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = (Connection) DriverManager.getConnection(host + dbName, user , pass);
		} catch (InstantiationException e) {
			//throw error initiatinf database.
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static Connection getConexion(){
		return initConexion();
	}
	
}
