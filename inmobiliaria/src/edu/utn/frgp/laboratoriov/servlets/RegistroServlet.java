package edu.utn.frgp.laboratoriov.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.utn.frgp.laboratoriov.dao.UsuarioDao;
import edu.utn.frgp.laboratoriov.domain.Usuario;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet{
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistroServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsuarioDao dao = new UsuarioDao();
		Usuario u = new Usuario();
		u.setDni(request.getParameter("dni"));
		u.setMail(request.getParameter("email"));
		u.setNombre(request.getParameter("nombre"));
		u.setPassword(request.getParameter("password"));
		if(request.getParameter("rol")!=null){
			u.setRolId(Integer.parseInt(request.getParameter("rol")));
		}else{
			u.setRolId(1);
		}
		u.setTelefono(request.getParameter("telefono"));
		u.setUsuario(request.getParameter("usuario"));
		
		try {
			dao.saveUsuario(u);
			response.sendRedirect("IndexServlet");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocurrio un error al dar de alta el usuario");
		}
	}

}

