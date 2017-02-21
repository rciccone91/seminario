package edu.utn.frgp.laboratoriov.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.utn.frgp.laboratoriov.dao.PropiedadesDao;
import edu.utn.frgp.laboratoriov.domain.Propiedad;
import edu.utn.frgp.laboratoriov.domain.Usuario;
import edu.utn.frgp.laboratoriov.web.ResultadoPropiedades;

@WebServlet("/ResultadosPropiedadServlet")
public class ResultadosPropiedadServlet extends HttpServlet{

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResultadosPropiedadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer op = Integer.parseInt(request.getParameter("op"));
		PropiedadesDao propDao = new PropiedadesDao();
		List<Propiedad> props = propDao.getPropiedadesByOperacion(op);
		request.setAttribute("resultados", props);
		request.getRequestDispatcher("/resultadoPropiedades.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("postResultadosPropServlet");
//		request.setAttribute("usuario",(Usuario) request.getSession().getAttribute("usuario")); 
		String action = request.getParameter("action");

		if ("Detalles".equals(action)) {
			PropiedadesDao dao = new PropiedadesDao();
			System.out.println("detallesResultadosPropServlet");
			request.setAttribute("prop", dao.getPropiedadById(Integer.parseInt(request.getParameter("propSel"))));
//			request.getRequestDispatcher("/detallePropiedad.jsp").forward(request, response);
			request.getRequestDispatcher("DetallePropiedadServlet").forward(request, response);
		}else{
			List<Propiedad> props = (List<Propiedad>) request.getAttribute("props");
			System.out.println(request.getAttribute("props"));
			request.setAttribute("resultados", props);
			request.getRequestDispatcher("/resultadoPropiedades.jsp").forward(request, response);
		}
	}

}
