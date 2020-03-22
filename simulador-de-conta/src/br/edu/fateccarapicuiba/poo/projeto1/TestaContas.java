package br.edu.fateccarapicuiba.poo.projeto1;

import java.util.Map.Entry;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class TestaContas {
	
	public static final long TEMPO = 86400000; // atualiza o CÁLCULO DE RENDIMENTO DA POUPANÇA uma vez por dia / 24 horas = 86400000 milesegundos = 1000 * 60 * 60 * 24
	//public static final long TEMPO = (1000 * 24); // atualiza o CÁLCULO DE RENDIMENTO DA POUPANÇA a cada 24 segundos

	public static void main(String[] args) {
		
		//TipoConta tipoContaCorrente = TipoConta.CONTACORRENTE;
		//TipoConta tipoContaPoupanca = TipoConta.CONTAPOUPANCA;
		
		//######Declaração e Istanciação dos Objetos
		Cliente cliente1 = new Cliente("William Halley", "11111111100");
		cliente1.setProfissao("Músico");
		
		Cliente cliente2 = new Cliente("Fulano da Silva", "22222222200");
		cliente2.setProfissao("Programador Java");
		
		Conta cc1 = new ContaCorrente(1111, 11110, TipoConta.CONTACORRENTE);
		cc1.setTitular(cliente1);
		
		Conta cp1 = new ContaPoupanca(2222, 22220, TipoConta.CONTAPOUPANCA);
		cp1.setTitular(cliente2);
		
		//##### Manipulando os Objetos###
		System.out.println(cc1);
		System.out.println(cp1);
		
		cc1.depositar(300);
		cc1.depositar(200.00);
		cp1.depositar(400.00);
		cp1.depositar(600.00);
		
		System.out.println(cc1);
		System.out.println(cp1);
		
		cc1.sacar(250.00); //saca R$ 250.00 + R$ 0,20 de taxa por saque
		System.out.println("\nApós saque na cc1: " + cc1); 
		
		cp1.sacar(100.00);
		System.out.println("\nApós saque na cp1" + cp1);
		
		cc1.transferir(100.00, cp1);
		System.out.println("\nApós transferir R$ 100,00 da cc11 para cp1: " + cc1);
		System.out.println("\nApós transferir R$ 100,00 da cc11 para cp1: " + cp1);
		
		//##### Tabela de Rendimentos - Poupança		
		ContaPoupanca cp2 = (ContaPoupanca) cp1;
		Set<Entry<Integer, Double>> associacoesTabelaRendimentos = cp2.getTabelaRendimentos();
		System.out.println("\n####### Tabela de Rendimentos - Poupança #######");
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		for (Entry<Integer, Double> entry : associacoesTabelaRendimentos) {
			System.out.println("Dia: " + entry.getKey() + " | Valor: " + df.format(entry.getValue()));
		}
		System.out.println();
		
		
		//###### Roda a cada 24 horas para calcular o rendimento da poupança
		Timer timer = null;
		if(timer == null) {
			timer = new Timer();
			TimerTask tarefa = new TimerTask() {
				
				@Override
				public void run() {
					try {
						cp2.calcularRendimentoMensal();	
						//System.out.println(cp2.consultarSaldo());
						System.out.println("Tarefa executada -> Calcular Rendimento Mensal - Poupança");
					} catch (Exception e) {
						//e.printStackTrace();
						System.out.println("Tarefa Falhou");						
					}
				}
			};
			timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
		}		

	}
}
