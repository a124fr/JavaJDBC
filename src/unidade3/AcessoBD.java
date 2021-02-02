package unidade3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class AcessoBD {
	
	static String url = "";
	static String usuario = "root";
	static String senha = "";
	static Connection conexao;
	
	private static void carregarDados() throws FileNotFoundException, IOException {
		Properties prop = new Properties();		
		prop.load(new FileInputStream("./config/config.properties"));
		
		url = prop.getProperty("banco.dados.url");
		usuario = prop.getProperty("banco.dados.usuario");
		senha = prop.getProperty("banco.dados.senha");
	}
	
	public static void conectar() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		AcessoBD.conectar(false);		
	}
	
	public static void conectar(boolean ehOperacaoTransacionado) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		carregarDados();
		Class.forName("com.mysql.jdbc.Driver");
		conexao = DriverManager.getConnection(url, usuario, senha);
		conexao.setAutoCommit(ehOperacaoTransacionado);//desabilita operações de transação.		
	}
	
	public static void desconectar() throws SQLException {
		if(!conexao.isClosed()) {
			conexao.close();
		}
	}
	
	public static void consultarCliente() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {		
		String consulta = "SELECT * FROM cliente";		
		conectar();
		Statement stament = conexao.createStatement();
		ResultSet rs = stament.executeQuery(consulta);
		
		while(rs.next()) {
			System.out.println("CPF: " + rs.getInt("cpf") + " Nome: " + rs.getString("nome"));			
		}
		desconectar();
	}
	
	public static void mostrarMetaInfoBD() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		conectar();
		DatabaseMetaData meta = conexao.getMetaData();		
		String fabricanteDB = meta.getDatabaseProductName();
		String versaoBD = meta.getDatabaseProductVersion();
		desconectar();
		JOptionPane.showMessageDialog(null, "Fabricante: " + fabricanteDB + " Versão: " + versaoBD);
	}
}
