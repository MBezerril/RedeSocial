package Conexoes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Classes.Publicacao;
import Conexoes.Conexoes;

public class Consultas {
	public Map<Integer, String> getGrupos(int idUsuario) throws SQLException {
		String query = "SELECT g.Nome AS Nome, g.ID AS ID " + "FROM Destino AS g "
				+ "INNER JOIN Participa AS p ON g.ID = p.IDGrupo " + "WHERE p.IDParticipante = " + idUsuario;

		Connection conexao = null;
		Statement stmt = null;
		// Variável de retorno
		Map<Integer, String> example = new HashMap<Integer, String>();
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
				p = results.getString("Nome");
				idtemp = results.getInt("ID");
				example.put(idtemp, p);
				System.out.println(p + " " + idtemp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return example;
	}

	public Map<Integer, String> getAmigos(int idUsuario) throws SQLException {
		String query = "SELECT u.Nome AS Nome, u.ID AS ID " + "FROM Destino AS u "
				+ "INNER JOIN Amizades AS a ON u.ID = a.Amigo1 OR u.ID = a.Amigo2 " + "WHERE a.Amigo1 = " + idUsuario
				+ " OR a.Amigo2 =" + idUsuario;

		Connection conexao = null;
		Statement stmt = null;
		// Variável de retorno
		Map<Integer, String> example = new HashMap<Integer, String>();
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
				p = results.getString("Nome");
				idtemp = results.getInt("ID");
				example.put(idtemp, p);
				System.out.println(p + " " + idtemp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return example;
	}

	public ArrayList<Publicacao> getPublicacao(int idUsuarioAutor, int idUsuarioAlvo) throws SQLException {
		String query = "SELECT a.ID AS ID " + "FROM Amizades AS a " + "WHERE (a.Amigo1 = " + idUsuarioAutor
				+ " AND a.Amigo2 =" + idUsuarioAlvo + ") OR (a.Amigo2 =" + idUsuarioAlvo + " AND a.Amigo2 ="
				+ idUsuarioAutor + ")";

		Connection conexao = null;
		Statement stmt = null;
		boolean amizade = false;
		try {
			// pega conexão
			conexao = Conexoes.getConnection();
			stmt = conexao.createStatement();
			// Executa query predefinida
			ResultSet results = stmt.executeQuery(query);
			// pega os resultados pelo nome das colunas da tabela
			while (results.next()) {
				amizade = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String query2 = "SELECT p.ID AS ID, p.IDUsuarioAutor AS IDUsuarioAutor, p.IDUsuarioDestino "
				+ "AS IDUsuarioDestino, p.Visibilidade AS Visibilidade, p.Imagem AS "
				+ "Imagem , p.Data AS Data,p.Texto AS Texto" + "FROM Publicacoes AS p " + "WHERE p.IDUsuarioDestino = "
				+ idUsuarioAlvo;

		// Variável de retorno
		ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();
		// Variáveis temporárias
		Publicacao example = new Publicacao();
		try {
			// Executa query predefinida
			ResultSet results = stmt.executeQuery(query2);
			// pega os resultados pelo nome das colunas da tabela
			while (results.next()) {
				if (amizade) {
					example.setData(results.getDate("Data"));
					example.setID(results.getInt("ID"));
					example.setIDDestino(results.getInt("IDUsuarioDestino"));
					example.setIDUsuarioAutor(results.getInt("IDUsuarioAutor"));
					example.setImagem(results.getString("Imagem"));
					example.setTexto(results.getString("Texto"));
					example.setVisibilidade(results.getInt("Visibilidade"));
					publicacoes.add(example);
				} else {
					if (results.getInt("Visibilidade") == 0) {
						example.setData(results.getDate("Data"));
						example.setID(results.getInt("ID"));
						example.setIDDestino(results.getInt("IDUsuarioDestino"));
						example.setIDUsuarioAutor(results.getInt("IDUsuarioAutor"));
						example.setImagem(results.getString("Imagem"));
						example.setTexto(results.getString("Texto"));
						example.setVisibilidade(results.getInt("Visibilidade"));
						publicacoes.add(example);
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return publicacoes;
	}

	public boolean setSolicitacao(int idManda, int idRecebe) throws SQLException {
		String insert = "INSERT INTO Solicitacao" + "VALUES ('" + idManda + "','" + idRecebe;
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

	public boolean setBloqueio(int idManda, int idRecebe) throws SQLException {
		String insert = "INSERT INTO Bloqueio" + "VALUES ('" + idManda + "','" + idRecebe;
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
