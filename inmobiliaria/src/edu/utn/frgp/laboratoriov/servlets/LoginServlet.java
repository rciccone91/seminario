package edu.utn.frgp.laboratoriov.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.utn.frgp.laboratoriov.dao.UsuarioDao;
import edu.utn.frgp.laboratoriov.domain.Usuario;
import edu.utn.frgp.laboratoriov.exceptions.NoSePudoRealizarLoginException;

public class LoginServlet extends HttpServlet{
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getAttribute("error"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioDao dao = new UsuarioDao();
		try {
			Usuario u = dao.doLogin(request.getParameter("usuario"),request.getParameter("password"));
			HttpSession sess = request.getSession();
			sess.setAttribute("usuario", u);
			response.sendRedirect("IndexServlet");
		} catch (NoSePudoRealizarLoginException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
