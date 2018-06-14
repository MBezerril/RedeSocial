package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Grupo;
import Classes.Usuario;
import Conexoes.Conexoes;

/**
 * Servlet implementation class Pesquisa
 */
@WebServlet("/Pesquisasvl")
public class Pesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Pesquisa() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String nome = request.getParameter("nomepesquisa");
		HashMap<Integer, String> lista;
		RequestDispatcher r = request.getRequestDispatcher("/paginas/resultados.jsp");

		try {
			lista = pesquisar(nome);
			if (lista.size() == 0) {
				request.setAttribute("msg",
						"Nenhum resultado foi encontrado, revise as letras maiusculas e minusculas");
			} else {
				request.setAttribute("lista", lista);
				HttpSession htps = request.getSession();
				Integer idUsuario = (Integer) htps.getAttribute("idUsuario");
				System.out.println(idUsuario);
			}
		} catch (SQLException e) {
			request.setAttribute("msg", "Erro durante a consulta!");
			e.printStackTrace();
		}
		r.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private HashMap<Integer, String> pesquisar(String nome) throws SQLException {
		String query = "SELECT ID, Nome FROM Destino WHERE Nome like '%" + nome + "%'";

		Connection conexao = null;
		Statement stmt = null;
		HashMap<Integer, String> listaUsuarios = new HashMap<Integer, String>();
		try {
			conexao = Conexoes.getConnection();
			stmt = conexao.createStatement();
			ResultSet results = stmt.executeQuery(query);
			while (results.next()) {
				listaUsuarios.put(results.getInt("ID"), results.getString("Nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		return listaUsuarios;
	}

}
