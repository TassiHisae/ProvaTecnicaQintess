package entradaDados;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import classes.Operacoes;

public class EntradaOperacoes {

	public List<Operacoes> dados() {
		String arquivo = "prova-tecnica\\Operacoes.csv";
		String linha = "";
		BufferedReader conteudoArquivo = null;
		List<Operacoes> operacoesLista = new ArrayList<>();
		int contador = 0;
		
		try {		
			conteudoArquivo = new BufferedReader(new FileReader(arquivo));
			while((linha = conteudoArquivo.readLine()) != null) {
				contador++;
				String[] dados = linha.split(";");
				
				if(contador != 1) {
					Operacoes op = new Operacoes();
					op.setNm_subproduto(dados[9]);
					op.setId(dados[13]);	
					op.setDataInicio(dados[1]);
					op.setDataFim(dados[2]);
					op.setQuantidade(dados[12]);

					operacoesLista.add(op);
				}
			}

		}catch(NullPointerException ex) {
			System.out.println("Ocorreu um erro na entrada de operacoes "+ex.getMessage());
		}catch(FileNotFoundException ex) {
			System.out.println("Ocorreu um erro na entrada de operacoes "+ex.getMessage());
		}catch(Exception ex) {
			System.out.println("Ocorreu um erro na entrada de operacoes "+ex.getMessage());
		}
		
		return operacoesLista;
    }
}
