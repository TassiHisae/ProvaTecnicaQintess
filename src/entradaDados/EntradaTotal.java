package entradaDados;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import classes.Total;

public class EntradaTotal {

	public List<Total> dados() {
		String arquivo = "prova-tecnica\\Total.csv";
		String linha = "";
		BufferedReader conteudoArquivo;
		List<Total> total = new ArrayList<>();
		int contador = 0;

		
		try {		
			conteudoArquivo = new BufferedReader(new FileReader(arquivo));
			while((linha = conteudoArquivo.readLine()) != null ) {
				contador++;
				String[] dados = linha.split(";");
				if(contador != 1) {
					Total t = new Total();
					t.setNm_sub(dados[0]);	
					t.setTotal(dados[1]);
					
					total.add(t);
				}
				
			}

		}catch(FileNotFoundException ex) {
			System.out.println("Ocorreu um erro na entrada do total "+ex.getMessage());
		}
		catch(NullPointerException ex) {
			System.out.println("Ocorreu um erro na entrada do total "+ex.getMessage());
		}
		catch(Exception ex) {
			System.out.println("Ocorreu um erro na entrada do total "+ex.getMessage());
		}
		
		return total;
    }
}
