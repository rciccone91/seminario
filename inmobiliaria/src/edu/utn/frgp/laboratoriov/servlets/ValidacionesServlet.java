package edu.utn.frgp.laboratoriov.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.utn.frgp.laboratoriov.dao.UsuarioDao;
import edu.utn.frgp.laboratoriov.exceptions.UsuarioYaExistenteException;

@WebServlet("/ValidacionesServlet")
public class ValidacionesServlet extends HttpServlet{
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidacionesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Llegue al servlet");
		UsuarioDao uDao = new UsuarioDao();
		
		if(request.getParameter("usuario") != null){
			try {
				uDao.getUserById(request.getParameter("usuario").trim());
				response.setContentType("text/plain");
				response.getWriter().write("OK");
			} catch (UsuarioYaExistenteException e) {
				response.getWriter().write("ERROR");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
