package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Conexoes.Consultas;

/**
 * Servlet implementation class Mural
 */
@WebServlet("/Mural")
public class Mural extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Mural() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		Integer id = Integer.parseInt(request.getParameter("id"));
		System.out.println("Recebido= " + id);
		RequestDispatcher r = request.getRequestDispatcher("index.jsp");
		Integer idUsuario;
		try {
			r = request.getRequestDispatcher("/paginas/principal.jsp");

			HttpSession session = request.getSession();
			idUsuario = (Integer) session.getAttribute("idUsuario");

			request.setAttribute("idUsuario", idUsuario);
			request.setAttribute("publicacoes", Consultas.getPublicacao(idUsuario, id));
			request.setAttribute("listaAmigos", Consultas.getAmigos(idUsuario));
			request.setAttribute("listaGrupos", Consultas.getGrupos(idUsuario));

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "ERRO AO CONSULTAR!");
		}
		r.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
