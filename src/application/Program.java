package application;

import java.util.List;

import model.CategoriaCNH;
import model.Cidade;
import model.Estado;
import utils.Base;
import utils.ConexaoMySQL;
import utils.InsertUtils;
import utils.Utils;

public class Program {

	public static void main(String[] args) {
		
		//Setando as informações de acesso ao banco de dados.
		ConexaoMySQL.setInfo("BEDRIVER", "root", "123");

		//Listas de dados úteis que vem do banco de dados.
		List<Estado> estados = Estado.getEstados();
		List<Cidade> cidades = Cidade.getCidades();
		List<CategoriaCNH> categoriasCNH = CategoriaCNH.getCategoriasCNH();
		
		//Caminhos dos csv's das bases.
		String pathCondutores = Utils.getPath(Base.CONDUTORES_HAB);
		String pathInfracoes = Utils.getPath(Base.INFRACOES);
		String pathInFrotas = Utils.getPath(Base.FROTAS);
		
		//Dados extraídos dos csv's das bases.
		List<String[]> dadosCondutores = Utils.getDataCSV(pathCondutores);
		List<String[]> dadosInfracoes = Utils.getDataCSV(pathInfracoes);
		List<String[]> dadosFrotas = Utils.getDataCSV(pathInFrotas);
		
		//Inserindo os dados nas tabelas de condutores, infracoes e frotas.
		InsertUtils.insertValuesCondutores(dadosCondutores, estados, categoriasCNH);
		InsertUtils.insertValuesInfracoes(dadosInfracoes, estados);
		InsertUtils.insertValuesFrotas(dadosFrotas, cidades);
	}

}