package br.com.pitang.desafio.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.pitang.desafio.exception.SystemError;

public class ConnectionFactory {

	private static Connection con;
	//private static final String URL = "jdbc:h2:./desafio;INIT=CREATE SCHEMA IF NOT EXISTS desafio\\;RUNSCRIPT FROM './init.sql'";
	private static final String URL = "jdbc:h2:./desafio;INIT=CREATE SCHEMA IF NOT EXISTS desafio";
	private static final String USUARIO = "sa";
	private static final String SENHA = null;
		

	public static Connection getConexao() throws SystemError {
		if (con == null) {
			try {
				con = DriverManager.getConnection(URL, USUARIO, SENHA);
			} catch (SQLException e) {
				Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
				throw new SystemError("Nao foi possivel conectar ao banco de dados!", e);
			} catch(Exception e) {
				Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
				throw new SystemError("Erro inesperado do banco de dados!", e);
			}
		}
		return con;
	}
	
	public static void closeConnection() throws SystemError {
		if(con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				throw new SystemError("Erro ao fechar conexao com banco de dados", e);
			}
		}
			
	}
}
