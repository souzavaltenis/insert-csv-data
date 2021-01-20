package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.ConexaoMySQL;

public class Cidade {

	private int id;
	private String nome;
	private int id_estado;
	
	public Cidade(int id, String nome, int id_estado) {
		this.id = id;
		this.nome = nome;
		this.id_estado = id_estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId_estado() {
		return id_estado;
	}

	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", nome=" + nome + ", id_estado=" + id_estado + "]";
	}
	
	public static List<Cidade> getCidades() {
		
		Connection conn = ConexaoMySQL.getConexaoMySQL();
		String query = "SELECT * FROM CIDADES";
		
		List<Cidade> dados = new ArrayList<>();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				int id_estado = rs.getInt("id_estado");
				
				dados.add(new Cidade(id, nome, id_estado));
			}
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dados;
	}
	
}