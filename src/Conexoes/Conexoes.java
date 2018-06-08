package Conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexoes {
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/RedeSocial"; // Nome da base de dados
		String user = "root"; // nome do usuário do MariaDB
		String password = "mariateste"; // senha do MariaDB
		Connection conexao = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");			
			conexao = DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conexao;
	}

}
