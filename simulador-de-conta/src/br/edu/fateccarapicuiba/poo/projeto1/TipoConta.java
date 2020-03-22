package br.edu.fateccarapicuiba.poo.projeto1;

public enum TipoConta {
	
	CONTACORRENTE("Conta Corrente"), CONTAPOUPANCA("Conta Poupança");
	
	private String valor;
	
	private TipoConta(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}

}
