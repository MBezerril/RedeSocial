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

import Classes.Grupo;
import Conexoes.Conexoes;


@WebServlet("/paginas/CriarGruposvl")
public class CriarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		Grupo grupo = new Grupo();
		grupo.setNome(nome);
		RequestDispatcher r = req.getRequestDispatcher("/paginas/principal.jsp");		
		try {
			boolean nomeOcupado = checarGrupo(nome);
			if (nomeOcupado == false) {
				HttpSession htps = req.getSession();
				Integer idUsuario = (Integer)htps.getAttribute("idUsuario");
				insereGrupo(grupo, idUsuario);
				req.setAttribute("msg", "GRUPO CRIADO!");				
			} else {
				req.setAttribute("msg", "NOME JA EM USO");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "NOME JA EM USO");
		}
		r.forward(req, res);
	}
	
	public boolean insereGrupo(Grupo grupo, Integer idUsuario) throws SQLException{
		String insertDestino = "INSERT INTO Destino VALUES (NULL,'0','" + grupo.getNome() + "')";
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
				grupo.setID(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		String insert = "INSERT INTO grupos (ID, IDUsuarioAdm) VALUES (" + grupo.getID() + "," + idUsuario + " )";

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
	
	public boolean checarGrupo(String nome) throws SQLException{
		String query = "SELECT * FROM  Grupos WHERE Nome = '" + nome + "'";;

		Connection conexao = null;
		Statement stmt = null;
		boolean retorno = false;
		
		try { 
			conexao = Conexoes.getConnection();
			stmt = conexao.createStatement();
			ResultSet results = stmt.executeQuery(query);
			if (results.next()) {
				retorno = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			retorno = false;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return retorno;
	}

}
