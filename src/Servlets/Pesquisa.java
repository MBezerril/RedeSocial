package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		ArrayList<Usuario> listaUsuarios;
		ArrayList<Grupo> listaGrupos;
		RequestDispatcher r = request.getRequestDispatcher("/paginas/resultados.jsp");
		
		try {
			listaUsuarios = pesquisarUsuarios(nome);
			listaGrupos = pesquisarGrupos(nome);
			if(listaGrupos.size() == 0 && listaUsuarios.size() == 0) {
				request.setAttribute("msg", "Nenhum resultado foi encontrado, revise as letras maiusculas e minusculas");
			}else{
				request.setAttribute("listaUsuarios", listaUsuarios);
				request.setAttribute("listaGruos", listaGrupos);
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

	private ArrayList<Usuario> pesquisarUsuarios(String nome) throws SQLException {
		String query = "SELECT ID, NomeCompleto FROM Usuarios WHERE NomeCompleto like '%" + nome + "%'";

		Connection conexao = null;
		Statement stmt = null;
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			conexao = Conexoes.getConnection();
			stmt = conexao.createStatement();
			ResultSet results = stmt.executeQuery(query);
			while (results.next()) {
				Usuario usuario = new Usuario();
				usuario.setIDUsuario(results.getInt("ID"));
				usuario.setNomeCompleto(results.getString("NomeCompleto"));
				listaUsuarios.add(usuario);
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

	private ArrayList<Grupo> pesquisarGrupos(String nome) throws SQLException {
		String query = "SELECT ID, Nome FROM Grupos WHERE Nome like '%" + nome + "%'";

		Connection conexao = null;
		Statement stmt = null;
		ArrayList<Grupo> listaGrupos = new ArrayList<Grupo>();
		try {
			conexao = Conexoes.getConnection();
			stmt = conexao.createStatement();
			ResultSet results = stmt.executeQuery(query);
			while (results.next()) {
				Grupo grupo = new Grupo();
				grupo.setID(results.getInt("ID"));
				grupo.setNome(results.getString("Nome"));
				listaGrupos.add(grupo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		return listaGrupos;
	}

}
