package edu.utn.frgp.laboratoriov.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.utn.frgp.laboratoriov.dao.PropiedadesDao;

@WebServlet("/PropiedadesCercanasServlet")
public class PropiedadesCercanasServlet extends HttpServlet{
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PropiedadesCercanasServlet() {
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
		String action = request.getParameter("action");
		PropiedadesDao dao = new PropiedadesDao();
		if ("Detalles".equals(action)) {
		   request.setAttribute("prop", dao.getPropiedadById(Integer.parseInt(request.getParameter("propSelCercana"))));
		   request.getRequestDispatcher("DetallePropiedadServlet").forward(request, response);
		}
	}
}
