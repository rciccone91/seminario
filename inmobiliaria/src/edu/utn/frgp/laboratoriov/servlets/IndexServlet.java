package edu.utn.frgp.laboratoriov.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.gson.Gson;

import edu.utn.frgp.laboratoriov.dao.PropiedadesDao;
import edu.utn.frgp.laboratoriov.dao.TipoDePropiedadDao;
import edu.utn.frgp.laboratoriov.domain.Ciudad;
import edu.utn.frgp.laboratoriov.domain.Propiedad;
import edu.utn.frgp.laboratoriov.domain.TipoDePropiedad;
import edu.utn.frgp.laboratoriov.domain.Usuario;

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
		List<Propiedad> propiedades = new PropiedadesDao().getUltimasPropiedades();
		List<Propiedad> propiedadesDestacadas = new PropiedadesDao().getPropiedadesDestacadas();
		request.setAttribute("tipoDePropiedades", tipoDePropiedades);
		request.setAttribute("propiedades", propiedades);
		request.setAttribute("propiedadesDestacadas", propiedadesDestacadas);
		if(session.getAttribute("usuario")!= null){
			System.out.println("getwithUserAfterLogin");
		 }
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("postIndexServlet");
		String action = request.getParameter("action");
		PropiedadesDao dao = new PropiedadesDao();

		if ("Detalles".equals(action)) {
		   System.out.println("detallesIndexServlet");
		   request.setAttribute("prop", dao.getPropiedadById(Integer.parseInt(request.getParameter("propSel"))));
		   request.getRequestDispatcher("DetallePropiedadServlet").forward(request, response);
		} else if ("Buscar".equals(action)) {
			String ciudad = request.getParameter("inputCiudad");
			Integer tipoProp = Integer.parseInt(request.getParameter("inputTipoProp"));
			Integer ambientes = Integer.parseInt(request.getParameter("inputAmbientes"));
			Integer operacion = Integer.parseInt(request.getParameter("inputOperacion"));
			request.setAttribute("props", dao.getPropiedadesFiltradas(ciudad,tipoProp,ambientes,operacion));
			request.getRequestDispatcher("ResultadosPropiedadServlet").forward(request, response);;
//			response.sendRedirect("resultadoPropiedades.jsp");
		}
	}



}
