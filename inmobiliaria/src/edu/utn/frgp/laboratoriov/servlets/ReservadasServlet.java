package edu.utn.frgp.laboratoriov.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.utn.frgp.laboratoriov.dao.PropiedadesDao;
import edu.utn.frgp.laboratoriov.dao.ReservasDao;
import edu.utn.frgp.laboratoriov.domain.Usuario;

@WebServlet("/ReservadasServlet")
public class ReservadasServlet extends HttpServlet{
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservadasServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		Usuario usuario = (Usuario) sess.getAttribute("usuario");
		ReservasDao resDao = new ReservasDao();
		
		request.setAttribute("resultados", resDao.getReservadasByUser(usuario));
		request.getRequestDispatcher("reservadas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("postReservadasServlet");
		String action = request.getParameter("action");
		PropiedadesDao dao = new PropiedadesDao();

		if ("Detalles".equals(action)) {
		   request.setAttribute("prop", dao.getPropiedadById(Integer.parseInt(request.getParameter("propSel"))));
		   request.getRequestDispatcher("DetallePropiedadServlet").forward(request, response);
		}
	}
}
