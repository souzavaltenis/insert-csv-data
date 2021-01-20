package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Início da classe de conexão//
public class ConexaoMySQL {

	public static String serverName = "";    //caminho do servidor do BD
	public static String mydatabase = "";        //nome do seu banco de dados
	public static String url = "";
	public static String username = "";        //nome de um usuário de seu BD
	public static String password = "";      //sua senha de acesso
	public static String status = "Não conectou...";

	//Método Construtor da Classe//
	public ConexaoMySQL() {
		
	}
	
	public static void setInfo(String mydatabase, String username, String password) {
		ConexaoMySQL.serverName = "localhost";
		ConexaoMySQL.mydatabase = mydatabase;
		ConexaoMySQL.url = "jdbc:mysql://" + ConexaoMySQL.serverName + "/" + mydatabase;
		ConexaoMySQL.username = username;
		ConexaoMySQL.password = password;
	}

	//Método de Conexão//
	public static Connection getConexaoMySQL() {

		Connection connection = null;          //atributo do tipo Connection
	
		try {
			
			//Carregando o JDBC Driver padrão
			String driverName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverName);
	
			connection = DriverManager.getConnection(url, username, password);
	
			//Testa sua conexão//
			if (connection != null) {
				status = ("STATUS--->Conectado com sucesso!");
			} else {
				status = ("STATUS--->Não foi possivel realizar conexão");
			}
			
			return connection;
	
		} catch (ClassNotFoundException e) {  //Driver não encontrado
	    	System.out.println("O driver expecificado nao foi encontrado.");
	    	return null;
		} catch (SQLException e) {
			//Não conseguindo se conectar ao banco
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
			return null;
		}
	
	}
	
	// Método que retorna o status da sua conexão//
	public static String statusConection() {
		return status;
	}
	
	// Método que fecha sua conexão//
	public static boolean FecharConexao() {
		try {
			ConexaoMySQL.getConexaoMySQL().close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	
	}

	// Método que reinicia sua conexão//
	public static java.sql.Connection ReiniciarConexao() {
		FecharConexao();
		return ConexaoMySQL.getConexaoMySQL();

	}

}