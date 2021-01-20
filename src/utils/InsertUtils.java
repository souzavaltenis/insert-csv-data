package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.CategoriaCNH;
import model.Cidade;
import model.Estado;

public class InsertUtils {
	
	/*
	 * Função que irá inserir dados na tabela frotas utilizando os dados do CSV (dadosFrotas),
	 * quanto os dados do próprio BD (cidades).
	 * 
	 * Entrada:
	 * List<String[]> dadosFrotas - lista contendo os dados do Csv das Frotas
	 * List<Cidade> cidades - lista contendo os dados das cidades
	 * 
	 * Saída: void
	 * */
	public static void insertValuesFrotas(List<String[]> dadosFrotas, List<Cidade> cidades) {
		
		long timeInit = System.currentTimeMillis();
		
		Connection conn = ConexaoMySQL.getConexaoMySQL();
		
		String query = "insert into FROTA_VEICULO"
				+ " (qtd_veiculos, id_cidade, veiculo) values (?, ?, ?)";

		for(int i=1; i<dadosFrotas.size(); i++) { //COMEÇA NO 1 PARA Ñ PEGAR OS NOMES DAS COLUNAS

			String frota[] = dadosFrotas.get(i);
			
			//0 uf
			//1 cidade
			//2 veiculo
			//3 quantidade

			int id_cidade = PrimaryKeyUtils.getPKCidade(cidades, frota[1]);
			String veiculo = frota[2];
			int quantidade = Integer.parseInt(frota[3]);

			try {
				
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, quantidade);
				preparedStmt.setInt(2, id_cidade);
				preparedStmt.setString(3, veiculo);
			    preparedStmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		}
			
	    try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    long timeEnd = System.currentTimeMillis();
	    
	    System.out.println("Dados das frotas foram inseridos. (" + (timeEnd-timeInit) + " ms)");
	}
	
	/*
	 * Função que irá inserir dados na tabela infracoes utilizando os dados do CSV (dadosInfracoes),
	 * quanto os dados do próprio BD (estados).
	 * 
	 * Entrada:
	 * List<String[]> dadosInfracoes - lista contendo os dados do Csv das Infrações
	 * List<Cidade> cidades - lista contendo os dados das cidades
	 * 
	 * Saída: void
	 * */
	public static void insertValuesInfracoes(List<String[]> dadosInfracoes, List<Estado> estados) {
	
		long timeInit = System.currentTimeMillis();
		
		Connection conn = ConexaoMySQL.getConexaoMySQL();
		
		String query = "insert into INFRACOES"
				+ " (codigo, quantidade, id_estado) values (?, ?, ?)";

		for(int i=1; i<dadosInfracoes.size(); i++) { //COMEÇA NO 1 PARA N PEGAR OS NOMES DAS COLUNAS
			
			String infracao[] = dadosInfracoes.get(i);
			
			//0 estado
			//1 codigo
			//2 quantidade
			
			String codigo = infracao[1];
			int quantidade = Integer.parseInt(infracao[2]);
			int id_estado = PrimaryKeyUtils.getPKEstado(estados, infracao[0]);

			try {
				
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, codigo);
				preparedStmt.setInt(2, quantidade);
				preparedStmt.setInt(3, id_estado);
			    preparedStmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	    try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    long timeEnd = System.currentTimeMillis();
		
	    System.out.println("Dados das infrações foram inseridos. (" + (timeEnd-timeInit) + " ms)");
	}
	
	/*	 
	 * Função que irá inserir dados na tabela condutores_habilitados utilizando os dados do CSV (dadosCondutores),
	 * quanto os dados do próprio BD (estados, categoriasCNH).
	 * 
	 * Entrada:
	 * List<String[]> dadosCondutores - lista contendo os dados do Csv dos Condutores Habilitados
	 * List<Estado> estados - lista contendo os dados dos estados
	 * List<CategoriaCNH> categoriasCNH - lista contendo os dados das categorias de CNH
	 * 
	 * Saída: void
	 * */
	public static void insertValuesCondutores(List<String[]> dadosCondutores, List<Estado> estados, List<CategoriaCNH> categoriasCNH) {
		
		long timeInit = System.currentTimeMillis();
		
		Connection conn = ConexaoMySQL.getConexaoMySQL();
		
		String query = "insert into CONDUTORES_HAB"
				+ " (qtd_habilitados, sexo, faixa_etaria, id_categoria, id_estado) values (?, ?, ?, ?, ?)";

		for(int i=1; i<dadosCondutores.size(); i++) { //COMEÇA NO 1 PARA N PEGAR OS NOMES DAS COLUNAS
			
			String condutor[] = dadosCondutores.get(i);
			
			//0 regiao
			//1 estado
			//2 sexo
			//3 faixaetaria
			//4 categoria
			//5 qtdhabilitado
			
			int qtd_habilitados = Integer.parseInt(condutor[5].replace(".", ""));
			String sexo = condutor[2].equals("Masculino") ? "M" : "F";
			String faixa_etaria = condutor[3];
			String id_categoria = PrimaryKeyUtils.getPKCategoriaCNH(categoriasCNH, condutor[4]);
			int id_estado = PrimaryKeyUtils.getPKEstado(estados, condutor[1]);
			
			try {
				
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, qtd_habilitados);
				preparedStmt.setString(2, sexo);
				preparedStmt.setString(3, faixa_etaria);
				preparedStmt.setString(4, id_categoria);
			    preparedStmt.setInt(5, id_estado);
			    preparedStmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	    try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    long timeEnd = System.currentTimeMillis();
	    
	    System.out.println("Dados dos condutores habilitados foram inseridos. (" + (timeEnd-timeInit) + " ms)");
	}
	
}