package classes;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import SaidaDados.ResultadoSaida;
import SaidaDados.TotalSaida;
import entradaDados.EntradaDadosMercado;
import entradaDados.EntradaOperacoes;
import entradaDados.EntradaTotal;

public class ProvaPrincipal {

	public static void main(String[] args) {
		EntradaDadosMercado edm = new EntradaDadosMercado();
		EntradaOperacoes eo = new EntradaOperacoes();
		EntradaTotal et = new EntradaTotal();
		List<String> total = new ArrayList<>();
		List<String> resultadoFinal = new ArrayList<>();
		int contadorT = 0;
		int contadorOp = 0;
		int contadorDm = 0;
		double somadorT = 0;
		String nome_subprod = "";
		long tempoInicial = System.currentTimeMillis();
		long ms = 0;
		DateTimeFormatter formato = DateTimeFormat.forPattern("dd/MM/yyyy");

		for(Operacoes op : eo.dados()) {
			contadorOp++;
			op.getNm_subproduto();
			op.getDataInicio();
			op.getDataFim();
			op.getId();
			op.getQuantidade();

	       	Days d = Days.daysBetween(DateTime.parse(op.getDataInicio(),formato), DateTime.parse(op.getDataFim(),formato));
	  		int n_prazo = d.getDays();
            for(DadosMercado dm : edm.dados()) {
	  		    dm.getId_preco();
	  			dm.getN_prazo();
	  			dm.getVl_preco();

		  		if(dm.getId_preco().equals(op.getId()) && n_prazo == Integer.parseInt(dm.getN_prazo())) {
		  			contadorDm++;
		  			String valor = dm.getVl_preco().replaceAll(",",".");
		  			String quantidade = op.getQuantidade().replaceAll(",", ".");
					double resultado = Double.parseDouble(quantidade) * Double.parseDouble(valor);
  					total.add(op.getNm_subproduto()+";"+resultado);
		  		}
		  		
	  		 }
             if(contadorOp != contadorDm) {
            	contadorDm++;
	  			String quantidade = op.getQuantidade().replaceAll(",", ".");
				double resultado = Double.parseDouble(quantidade) * 0;
					total.add(op.getNm_subproduto()+";"+resultado);
            }
            Collections.sort(total);
            System.out.println("Processando primeira parte...");
	  	}
		TotalSaida ts = new TotalSaida(total);
		
		ProvaPrincipal pp = new ProvaPrincipal();
		
		if(pp.arquivoExistente()) {
			for(Total t : et.dados()) {
				contadorT++;
				t.getNm_sub();
				t.getTotal();
					
				if(contadorT == 1) {
					nome_subprod = t.getNm_sub();
				}
					
				if(nome_subprod.equals(t.getNm_sub())) {
					somadorT+=Double.parseDouble(t.getTotal());
				}else {
					resultadoFinal.add(nome_subprod+";"+somadorT);
					nome_subprod = t.getNm_sub();
					somadorT = Double.parseDouble(t.getTotal());
				}
					
				if(contadorT == et.dados().size()) {
					resultadoFinal.add(nome_subprod+";"+somadorT);
				}
				System.out.println("Processando segunda parte...");
			}
				
		}else {
			System.out.println("Arquivo não existe, mas é preciso. Favor rodar o programa novamente para que ele crie o arquivo necessário");
		}
			
			ResultadoSaida rs = new ResultadoSaida(resultadoFinal);
			ms = System.currentTimeMillis()-tempoInicial;
			System.out.println("Finalizado");
			System.out.println("Tempo Total: "+pp.calculaHora(ms));
	}
		
		
		
	
	public boolean arquivoExistente(){
		boolean existe = false;
		File file = new File("prova-tecnica\\Total.csv");
        if(file.exists()){
            existe = true;
        }
        return existe;
	}
	
	public String calculaHora(long ms) {
		long segundos = ( ms / 1000 ) % 60; 
		long minutos  = ( ms / 60000 ) % 60;
		long horas    = ms / 3600000;
		return String.format( "%03d:%02d:%02d", horas, minutos, segundos);
	}

}
