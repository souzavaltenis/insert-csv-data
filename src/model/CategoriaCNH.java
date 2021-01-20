package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.ConexaoMySQL;

public class CategoriaCNH {

	private String id_cat_cnh;
	private String descricao;
	
	public CategoriaCNH(String id_cat_cnh, String descricao) {
		this.id_cat_cnh = id_cat_cnh;
		this.descricao = descricao;
	}

	public String getId_cat_cnh() {
		return id_cat_cnh;
	}

	public void setId_cat_cnh(String id_cat_cnh) {
		this.id_cat_cnh = id_cat_cnh;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "CategoriaCNH [id_cat_cnh=" + id_cat_cnh + ", descricao=" + descricao + "]";
	}
	
	public static List<CategoriaCNH> getCategoriasCNH() {
		
		Connection conn = ConexaoMySQL.getConexaoMySQL();
		String query = "SELECT * FROM CATEGORIA_CNH";
		
		List<CategoriaCNH> dados = new ArrayList<>();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				
				String id_cat_cnh = rs.getString("id");
				String descricao = rs.getString("descricao");
				
				dados.add(new CategoriaCNH(id_cat_cnh, descricao));
			}
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dados;
	}
	
}