package entradaDados;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import classes.DadosMercado;

public class EntradaDadosMercado {
	
	public List<DadosMercado> dados() {
		String arquivo = "prova-tecnica\\DadosMercado.csv";
		String linha = "";
		BufferedReader conteudoArquivo;
		List<DadosMercado> dadosLista = new ArrayList<>();
		int contador = 0;

		
		try {		
			conteudoArquivo = new BufferedReader(new FileReader(arquivo));
			while((linha = conteudoArquivo.readLine()) != null) {
				contador++;
				String[] dados = linha.split(";");

				if(contador != 1) {
					DadosMercado dm = new DadosMercado();
					dm.setId_preco(dados[0]);	
					dm.setN_prazo(dados[1]);
					dm.setVl_preco(dados[2]);
					
					dadosLista.add(dm);
				}

				
			}

		}catch(NullPointerException ex) {
			System.out.println("Ocorreu um erro na entrada de dados do mercado "+ex.getMessage());
		}catch(FileNotFoundException ex) {
			System.out.println("Ocorreu um erro na entrada de dados do mercado "+ex.getMessage());
		}catch (Exception ex) {
			System.out.println("Ocorreu um erro na entrada de dados do mercado "+ex.getMessage());
		}
		
		return dadosLista;
    }
	
}
