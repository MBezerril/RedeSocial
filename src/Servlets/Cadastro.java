package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.utils.JavaUtils;

import Classes.Usuario;
import Conexoes.Conexoes;

@WebServlet("/Cadastrosvl")
public class Cadastro extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String trabalho = req.getParameter("trabalho");
		String endereco = req.getParameter("ender");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		RequestDispatcher r = req.getRequestDispatcher("cadastro.jsp");

		try {
			Usuario usuario = new Usuario(nome, trabalho, endereco, login, senha);
			boolean re = insereUsuario(usuario);
			if (re == true) {
				req.setAttribute("msg", "CADASTRADO!");
				r = req.getRequestDispatcher("/index.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "CREDENCIAIS INCORRETAS");
		}
		r.forward(req, res);
	}

	public boolean insereUsuario(Usuario user) throws SQLException {
		String insertDestino = "INSERT INTO Destino VALUES (NULL,'" + user.getNomeCompleto() + "','0')";

		String SelectDestino = "SELECT max(ID) AS ID FROM Destino";

		Connection conexao = null;
		Statement stmt = null;
		boolean result = false;

		try {
			conexao = Conexoes.getConnection();
			stmt = conexao.createStatement();
			result = stmt.execute(insertDestino);
			ResultSet resultSet = stmt.executeQuery(SelectDestino);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("ID");
				user.setIDUsuario(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		String insertUsuario = "INSERT INTO Usuarios VALUES (" + user.getIDUsuario() + ",'" + user.getLogin() + "','"
				+ user.getPassword() + "','" + user.getEndereco() + "','" + user.getTrabalho() + "',NULL)";
		try {
			conexao = Conexoes.getConnection();
			stmt = conexao.createStatement();
			result = stmt.execute(insertUsuario);
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
