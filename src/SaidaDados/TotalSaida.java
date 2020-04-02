package SaidaDados;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TotalSaida {

			
	public TotalSaida(List<String> dados) {
		
		try {
			FileWriter fw = new FileWriter ( "prova-tecnica\\Total.csv");
			
			fw.append ( "nm_subproduto;total \n" );
			
			for(String dado : dados) {
				fw.append ( dado+"\n");
			}
			
			fw.flush ();
			fw.close ();
			
		} catch (IOException ex) {
			System.out.println("Ocorreu um erro na saida de total "+ex);
			ex.printStackTrace();
		}
	}

}
