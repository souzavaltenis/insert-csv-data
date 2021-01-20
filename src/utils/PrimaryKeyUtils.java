package utils;

import java.util.List;

import model.CategoriaCNH;
import model.Cidade;
import model.Estado;

public class PrimaryKeyUtils {
	
	/*
	 * Função para percorrer uma lista de cidades e obter o seu ID.
	 * 
	 * Entrada:
	 * List<Cidade> cidades - lista contendo todas as cidades
	 * String cidade - nome da cidade para ser encontrada
	 * 
	 * Saída:
	 * int com o id da cidade encontrada (sua chave primária),
	 * caso não encontre, retornará -1.
	 * */
	public static int getPKCidade(List<Cidade> cidades, String cidade) {
		
		for(int i=0; i<cidades.size(); i++) {
			Cidade city = cidades.get(i);
			String c1 = Utils.removerAcentos(city.getNome()).toUpperCase();
			String c2 = Utils.removerAcentos(cidade).toUpperCase();
			if(c1.equals(c2)) {
				return city.getId();
			}
		}
		
		return -1;
	}
	
	/*
	 * Função para percorrer uma lista de estados e obter o seu ID.
	 * 
	 * Entrada:
	 * List<Estado> estados - lista contendo todos os estados
	 * String estado - nome do estado para ser encontrado
	 * 
	 * Output:
	 * int com o id do estado encontrado (sua chave primária),
	 * caso não encontre, retornará -1.
	 * */
	public static int getPKEstado(List<Estado> estados, String estado) {

		for (int i = 0; i < estados.size(); i++) {
			Estado estadu = estados.get(i);
			String e1 = Utils.removerAcentos(estadu.getNome()).toUpperCase();
			String e2 = Utils.removerAcentos(estado).toUpperCase();
			if (e1.equals(e2)) {
				return estadu.getId();
			}
		}

		return -1;
	}
	
	/*
	 * Função para percorrer uma lista de Categorias CNH e obter o seu ID.
	 * 
	 * Entrada:
	 * List<CategoriaCNH> categoriasCNH - lista contendo todos as categorias
	 * String categoriaCNH - categoria a ser pesquisada
	 * 
	 * Saída:
	 * String com o id da categoriaCNH encontrada (sua chave primária),
	 * caso não encontre, retornará -1.
	 * */
	public static String getPKCategoriaCNH(List<CategoriaCNH> categoriasCNH, String categoriaCNH) {

		for (int i = 0; i < categoriasCNH.size(); i++) {
			String categoria1 = categoriasCNH.get(i).getId_cat_cnh();//AB
			String categoria2 = categoriaCNH.split("-")[0];//AB-Condutor Moto/Carro, por isso o split.
			if (categoria1.toUpperCase().equals(categoria2.toUpperCase())) {
				return categoria1;
			}
		}

		return "";
	}
}