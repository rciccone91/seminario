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
import edu.utn.frgp.laboratoriov.domain.Propiedad;
import edu.utn.frgp.laboratoriov.domain.PropiedadFavorita;
import edu.utn.frgp.laboratoriov.domain.Reservas;
import edu.utn.frgp.laboratoriov.domain.Usuario;

@WebServlet("/AccionesEnPropiedadesServlet")
public class AccionesEnPropiedadesServlet extends HttpServlet{
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccionesEnPropiedadesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Llego al get de acciones en prop");
		HttpSession sess = request.getSession();
		PropiedadesDao propDao = new PropiedadesDao();
		Propiedad p = propDao.getPropiedadById(Integer.parseInt(request.getParameter("propId")));
		
		if(request.getParameter("accion") != null && request.getParameter("accion").equals("Favorita")){
			Usuario u = (Usuario) sess.getAttribute("usuario");
			PropiedadFavoritaDao favDao = new PropiedadFavoritaDao();
			try {
				favDao.saveFavorita(new PropiedadFavorita(null,p,u,null));
				response.setContentType("text/plain");
				response.getWriter().write("OK");
			} catch (Exception e) {
				response.setContentType("text/plain");
				response.getWriter().write("Se produjo un error al guardar la propiedad como favorita. Contactese con el administrador.");
			}
			
		}else if(request.getParameter("accion") != null && request.getParameter("accion").equals("Reservar")){
			Usuario u = (Usuario) sess.getAttribute("usuario");
			ReservasDao resDao = new ReservasDao();
			try {
				resDao.saveReserva(new Reservas(null, u, null, null, p,false));
				response.setContentType("text/plain");
				response.getWriter().write("OK");
			} catch (Exception e) {
				e.printStackTrace();
				response.setContentType("text/plain");
				response.getWriter().write("Se produjo un error al reservar la propiedad. Contactese con el administrador.");
			}
		}else if(request.getParameter("accion") != null && request.getParameter("accion").equals("MasInfo")){
			response.setContentType("text/plain");
			response.getWriter().write("OK");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
