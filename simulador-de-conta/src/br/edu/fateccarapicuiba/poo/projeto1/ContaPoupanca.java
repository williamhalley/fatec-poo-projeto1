package br.edu.fateccarapicuiba.poo.projeto1;

import java.util.Map.Entry;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ContaPoupanca extends Conta {

	private final double rendimentoMensal = 0.5; //0,5% ao mês
	private Map<Integer, Double> mapDepositos = new HashMap<Integer, Double>();
	
	public ContaPoupanca(int agencia, int numeroConta, TipoConta tipoConta) {
		super(agencia, numeroConta, tipoConta);
	}
	
	@Override
	public void depositar(double valor) {
		super.depositar(valor);	
		LocalDate hoje = LocalDate.now();
	
		if(this.mapDepositos.containsKey(hoje.getDayOfMonth())) {
			//double valorAtualparaEstaData = mapDepositos.remove(hoje.getDayOfMonth());
			double valorAtualparaEstaData = mapDepositos.get(hoje.getDayOfMonth());
			double novoValor = valorAtualparaEstaData + valor;			
			mapDepositos.put(hoje.getDayOfMonth(), novoValor);
		} else {
			mapDepositos.put(hoje.getDayOfMonth(), valor);
		}		
	}
	
	@Override
	public void sacar(double valor) {
		super.sacar(valor);
		LocalDate hoje = LocalDate.now();
		
		if(this.mapDepositos.containsKey(hoje.getDayOfMonth())) {
			double valorAtualparaEstaData = mapDepositos.get(hoje.getDayOfMonth());
			double novoValor = valorAtualparaEstaData - valor;
			mapDepositos.put(hoje.getDayOfMonth(), novoValor);
		} 
		//mapDepositos.put(hoje.getDayOfMonth(), valor);
	}
	
	
	public double consultarRendimentoMensal() {
		return rendimentoMensal;
	}
	
	public void calcularRendimentoMensal() {		
		LocalDate ontem = LocalDate.now().plusDays(1);
		
		if(this.mapDepositos.containsKey(ontem.getDayOfMonth())) {
			//double valorAtual = mapDepositos.remove(ontem.getDayOfMonth());
			double valorAtual = mapDepositos.get(ontem.getDayOfMonth());
			double valorMaisRendimento = valorAtual + valorAtual * 0.005; //atualiza o valor da data de aniversário para --> valor + rendimento após 1 mês
			mapDepositos.put(ontem.getDayOfMonth(), valorMaisRendimento);
			super.depositar(valorAtual * 0.005); //atualiza o saldo total
		}		
	}
	
	public Set<Entry<Integer, Double>> getTabelaRendimentos(){
		Set<Entry<Integer, Double>> associacoesTabelaRendimentos = mapDepositos.entrySet();
		return associacoesTabelaRendimentos;
	}

}
