package edu.utn.frgp.laboratoriov.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.utn.frgp.laboratoriov.dao.PropiedadFavoritaDao;
import edu.utn.frgp.laboratoriov.dao.PropiedadesDao;
import edu.utn.frgp.laboratoriov.dao.ReservasDao;
import edu.utn.frgp.laboratoriov.domain.Usuario;

@WebServlet("/FavoritasServlet")
public class FavoritasServlet extends HttpServlet{
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FavoritasServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		Usuario usuario = (Usuario) sess.getAttribute("usuario");
		PropiedadFavoritaDao favDao = new PropiedadFavoritaDao();
		
		request.setAttribute("resultados", favDao.getFavoritasByUser(usuario));
		request.getRequestDispatcher("favoritas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("postFavoritasServlet");
		String action = request.getParameter("action");
		PropiedadesDao dao = new PropiedadesDao();

		if ("Detalles".equals(action)) {
		   request.setAttribute("prop", dao.getPropiedadById(Integer.parseInt(request.getParameter("propSel"))));
		   request.getRequestDispatcher("DetallePropiedadServlet").forward(request, response);
		}
	}
}
