package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Conexoes.Conexoes;


@WebServlet("/Publicar")
public class Publicar extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher r = req.getRequestDispatcher("/paginas/principal.jsp");
		int visibilidade = req.getIntHeader("visibilidade");
		String texto = req.getParameter("pensamento");			
		int destino = req.getIntHeader("destino");
		HttpSession htps = req.getSession();
		Integer idUsuario = (Integer)htps.getAttribute("idUsuario");
		boolean conf = false;
		try {
			if(visibilidade != 1) {
				conf = Postar(visibilidade, texto, idUsuario, destino);
			}else {
				conf = Postar(0, texto, idUsuario, idUsuario);
			}
			if(conf) {
				req.setAttribute("msg", "PUBLICADO!");
			}else {
				req.setAttribute("msg", "ERRO!");
			}
		}catch (SQLException e){
			e.printStackTrace();
			req.setAttribute("msg", "ERRO!");
		}
		r.forward(req, res);
	}
	
	public boolean Postar(int visibilidade, String texto, int idUsuario, int idDestino) throws SQLException {
		String insert = "INSERT INTO publicacoes (IDUsarioAutor, IDDestino, Visibilidade, Texto) VALUES "
				+ "(" + idUsuario + "," + idDestino + "," + visibilidade + ",'" + texto + "')";
		Connection conexao = null;
		Statement stmt = null;
		boolean result = false;
		try {
			conexao = Conexoes.getConnection();
			stmt = conexao.createStatement();
			result = stmt.execute(insert);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return result;
	}

}
