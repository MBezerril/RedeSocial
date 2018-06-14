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
import javax.servlet.http.HttpSession;

import Conexoes.Conexoes;
import Conexoes.Consultas;

@WebServlet("/Loginsvl")
public class Login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String password = req.getParameter("password");
		System.out.println("Recebido= " + nome);
		RequestDispatcher r = req.getRequestDispatcher("index.jsp");
		Integer idUsuario;
		try {
			idUsuario = checarUsuario(nome, password);
			if (idUsuario != null && idUsuario != 0) {
				r = req.getRequestDispatcher("/paginas/principal.jsp");
				
				HttpSession session = req.getSession();
				session.setAttribute("idUsuario", idUsuario);
				
				req.setAttribute("idUsuario", idUsuario);
				req.setAttribute("msg", "Bem vindo!");
				
				req.setAttribute("publicacoes", Consultas.getPublicacao(idUsuario, idUsuario));
				req.setAttribute("listaAmigos", Consultas.getAmigos(idUsuario));
				req.setAttribute("listaGrupos", Consultas.getGrupos(idUsuario));
				
			} else {
				req.setAttribute("msg", "CREDENCIAIS INCORRETAS");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "CREDENCIAIS INCORRETAS");
		}
		r.forward(req, res);

	}

	/**
	 * Recebe o nome de usuario e o password e checa se a senha está correta para o
	 * usuario
	 * 
	 * @return User ID
	 */
	public Integer checarUsuario(String nome, String userPassword) throws SQLException {
		String query = "SELECT ID, PasswordHash FROM  Usuarios WHERE Login = '" + nome + "'";

		Connection conexao = null;
		Statement stmt = null;
		// Variável de retorno
		Integer idUsuario = new Integer(0);
		// Variáveis temporárias
		String p = "";
		int idtemp = 0;
		try {
			// pega conexão
			conexao = Conexoes.getConnection();
			stmt = conexao.createStatement();
			// Executa query predefinida
			ResultSet results = stmt.executeQuery(query);
			// pega os resultados pelo nome das colunas da tabela
			while (results.next()) {
				p = results.getString("PasswordHash");
				idtemp = results.getInt("ID");
				System.out.println(p);
			}
			// Se a senha recebida é igual a senha armazanada no banco
			// retorna o id que veio junto na consulta
			if (p.equals(userPassword)) {
				idUsuario = idtemp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			idUsuario = null;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return idUsuario;
	}

}