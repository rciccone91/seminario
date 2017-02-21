package edu.utn.frgp.laboratoriov.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import edu.utn.frgp.laboratoriov.dao.PropiedadFavoritaDao;
import edu.utn.frgp.laboratoriov.dao.PropiedadesDao;
import edu.utn.frgp.laboratoriov.dao.ReservasDao;
import edu.utn.frgp.laboratoriov.dao.TipoDePropiedadDao;
import edu.utn.frgp.laboratoriov.domain.Propiedad;
import edu.utn.frgp.laboratoriov.domain.TipoDePropiedad;
import edu.utn.frgp.laboratoriov.domain.Usuario;

@WebServlet("/DetallePropiedadServlet")
public class DetallePropiedadServlet extends HttpServlet{
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetallePropiedadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("entre en el servlet");
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(request.getAttribute("prop"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		PropiedadFavoritaDao favDao = new PropiedadFavoritaDao();
		ReservasDao resDao = new ReservasDao();
		Propiedad prop = (Propiedad) request.getAttribute("prop");
		PropiedadesDao propDao = new PropiedadesDao();
		Usuario us = (Usuario) sess.getAttribute("usuario");
		request.setAttribute("propCercanas",propDao.getPropiedadesCercanas(prop.getCiudad().getId(),prop.getId()));
		
		if(us != null){
			System.out.println("En el Servlet de detalle. Tengo el usuario");
			request.setAttribute("esFavorita",favDao.isPropiedadFavorita(prop.getId(), us.getUsuario()));
			request.setAttribute("estaReservada",resDao.isReservada(prop.getId()));
			
		}
		request.getRequestDispatcher("/detallePropiedad.jsp").forward(request, response);
	}

}
