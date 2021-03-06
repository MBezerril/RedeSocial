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
	public static Map<Integer, String> getGrupos(int idUsuario) throws SQLException {
		String query = "SELECT g.Nome AS Nome, g.ID AS ID " + "FROM Destino AS g "
				+ "INNER JOIN Participa AS p ON g.ID = p.IDGrupo " + "WHERE p.IDParticipante = " + idUsuario;

		Connection conexao = null;
		Statement stmt = null;
		// Vari�vel de retorno
		Map<Integer, String> example = new HashMap<Integer, String>();
		// Vari�veis tempor�rias
		String p = "";
		int idtemp = 0;
		try {
			// pega conex�o
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

	public static HashMap<Integer, String> getAmigos(int idUsuario) throws SQLException {
		String query = "SELECT u.Nome AS Nome, u.ID AS ID " + "FROM Destino AS u "
				+ "INNER JOIN Amizades AS a ON u.ID = a.Amigo1 OR u.ID = a.Amigo2 " + "WHERE a.Amigo1 = " + idUsuario
				+ " OR a.Amigo2 =" + idUsuario;

		Connection conexao = null;
		Statement stmt = null;
		HashMap<Integer, String> example = new HashMap<Integer, String>();
		String p = "";
		int idtemp = 0;
		try {
			// pega conex�o
			conexao = Conexoes.getConnection();
			stmt = conexao.createStatement();
			ResultSet results = stmt.executeQuery(query);
			while (results.next()) {
				idtemp = results.getInt("ID");
				p = results.getString("Nome");
				if (idtemp != idUsuario)
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

	public static ArrayList<Publicacao> getPublicacao(int meuId, int idUsuarioAlvo) throws SQLException {
		String query = "SELECT a.Amigo1 AS Amigo1 " + "FROM Amizades AS a " + "WHERE (a.Amigo1 = " + meuId
				+ " AND a.Amigo2 =" + idUsuarioAlvo + ") OR (a.Amigo2 =" + idUsuarioAlvo + " AND a.Amigo2 =" + meuId
				+ ")";

		Connection conexao = null;
		Statement stmt = null;
		boolean amizade = false;
		try {
			// pega conex�o
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

		String query2 = "SELECT p.ID AS ID, p.IDUsuarioAutor AS IDUsuarioAutor, p.IDDestino "
				+ "AS IDDestino, p.Visibilidade AS Visibilidade, p.Imagem AS "
				+ "Imagem , p.Data AS Data,p.Texto AS Texto FROM Publicacoes AS p WHERE p.IDDestino = " + idUsuarioAlvo;

		ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();
		Publicacao example;
		try {
			ResultSet results = stmt.executeQuery(query2);
			while (results.next()) {
				if (amizade) {
					example = new Publicacao();
					example.setData(results.getDate("Data"));
					example.setID(results.getInt("ID"));
					example.setIDDestino(results.getInt("IDDestino"));
					example.setIDUsuarioAutor(results.getInt("IDUsuarioAutor"));
					example.setImagem(results.getString("Imagem"));
					example.setTexto(results.getString("Texto"));
					example.setVisibilidade(results.getInt("Visibilidade"));
					Boolean b = setNamePublicacao(example);
					publicacoes.add(example);
				} else {
					if (results.getInt("Visibilidade") == 0) {
						example = new Publicacao();
						example.setData(results.getDate("Data"));
						example.setID(results.getInt("ID"));
						example.setIDDestino(results.getInt("IDDestino"));
						example.setIDUsuarioAutor(results.getInt("IDUsuarioAutor"));
						example.setImagem(results.getString("Imagem"));
						example.setTexto(results.getString("Texto"));
						example.setVisibilidade(results.getInt("Visibilidade"));
						Boolean b = setNamePublicacao(example);
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

	public static boolean setSolicitacao(int idManda, int idRecebe) throws SQLException {
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

	public static boolean setBloqueio(int idManda, int idRecebe) throws SQLException {
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

	public static boolean setNamePublicacao(Publicacao pub) throws SQLException {
		String query = "SELECT d.Nome AS Nome FROM Destino AS d WHERE d.ID = " + pub.getIDUsuarioAutor();
		Connection conexao = null;
		Statement stmt = null;
		boolean retorno = false;

		try {
			conexao = Conexoes.getConnection();
			stmt = conexao.createStatement();
			ResultSet results = stmt.executeQuery(query);
			while (results.next()) {
				pub.setNomeIDAutor(results.getString("Nome"));
				retorno = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return retorno;
	}

}
