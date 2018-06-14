package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Conexoes.Consultas;

@WebServlet("/ConsultaPublicsvl")
public class ConsultaPublic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		RequestDispatcher r = req.getRequestDispatcher("/paginas/publicar.jsp");
		HttpSession htps = req.getSession();
		Integer idUsuario = (Integer)htps.getAttribute("idUsuario");
		try {
			Map<Integer,String> grupos = Consultas.getGrupos(idUsuario);
			Map<Integer,String> amigos = Consultas.getAmigos(idUsuario);
			req.setAttribute("listaAmigos", amigos);
			req.setAttribute("listaGrupos", grupos);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		r.forward(req, res);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
