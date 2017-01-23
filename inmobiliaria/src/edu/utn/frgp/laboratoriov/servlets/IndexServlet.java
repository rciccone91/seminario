package edu.utn.frgp.laboratoriov.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import edu.utn.frgp.laboratoriov.dao.TipoDePropiedadDao;
import edu.utn.frgp.laboratoriov.domain.Ciudad;
import edu.utn.frgp.laboratoriov.domain.TipoDePropiedad;

public class IndexServlet  extends HttpServlet{
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<TipoDePropiedad> tipoDePropiedades = new TipoDePropiedadDao().getTiposDePropiedades();
		request.setAttribute("tipoDePropiedades", tipoDePropiedades);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
//		 if(session.getAttribute("usuario")== null){
//			 request.getRequestDispatcher("/login.jsp").forward(request, response);
//		 }else{
//			 Usuario usuario = (Usuario)session.getAttribute("usuario");
//			 redirectUser(request, response, usuario);
//		 }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Usuario usuario = service.login(request.getParameter("usuario"),request.getParameter("password"));
//		
//		if(usuario!=null){
//			 HttpSession session = request.getSession();
//			 session.setAttribute("usuario", usuario);
//			 redirectUser(request, response, usuario);
//		}
	}



}
