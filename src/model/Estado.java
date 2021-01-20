package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.ConexaoMySQL;

public class Estado {

	private int id;
	private String nome;
	private String uf;
	private int id_regiao;
	
	public Estado(int id, String nome, String uf, int id_regiao) {
		this.id = id;
		this.nome = nome;
		this.uf = uf;
		this.id_regiao = id_regiao;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public int getId_regiao() {
		return id_regiao;
	}

	public void setId_regiao(int id_regiao) {
		this.id_regiao = id_regiao;
	}
	
	public static List<Estado> getEstados() {
		
		Connection conn = ConexaoMySQL.getConexaoMySQL();
		String query = "SELECT * FROM ESTADOS";
		
		List<Estado> dados = new ArrayList<>();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String uf = rs.getString("uf");
				int id_regiao = rs.getInt("id_regiao");
				
				dados.add(new Estado(id, nome, uf, id_regiao));
			}
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dados;
	}
	
}