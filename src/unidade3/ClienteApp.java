package unidade3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteApp extends AcessoBD {
	
	public void inserir(int cpf, String nome, String email) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		String sql = "INSERT INTO cliente VALUES (?, ?, ?)";
		conectar(true);
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, cpf);
		statement.setString(2, nome);
		statement.setString(3, email);
		statement.execute();
		conexao.commit();
	}
	
	public void alterar() {
		
	}
	
	public void excluir() {
		
	}
	
	public void consultar(int cpf) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		String sql = "SELECT * FROM cliente WHERE cpf = ?";
		conectar(true);
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, cpf);
		ResultSet rs = statement.executeQuery();
		
		if(rs.next()) {
			System.out.println("CPF: " + rs.getInt("cpf") + " Nome: " + rs.getString("nome"));			
		}
		desconectar();
	}
	
	public void consultarTodos() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		String sql = "SELECT * FROM cliente";
		conectar(true);
		Statement statement = conexao.createStatement();		
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()) {
			System.out.println("CPF: " + rs.getInt("cpf") + " Nome: " + rs.getString("nome"));			
		}
		desconectar();
	}
}
