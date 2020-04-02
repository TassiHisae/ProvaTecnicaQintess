package SaidaDados;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResultadoSaida {
	
	
public ResultadoSaida(List<String> dados) {
	
	try {
		FileWriter fw = new FileWriter ( "prova-tecnica\\Resultado.csv");
		
		fw.append ( "NM_SUBPRODUTO;RESULTADO \n" );
		
		for(String dado : dados) {
			fw.append ( dado+"\n");
		}
		
		fw.flush ();
		fw.close ();
		
	} catch (IOException ex) {
		System.out.println("Ocorreu um erro na saida de dados do resultado "+ex);
		ex.printStackTrace();
	}
}


}
