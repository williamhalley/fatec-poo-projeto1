package br.edu.fateccarapicuiba.poo.projeto1;

public class ContaCorrente extends Conta {
	
	private final double taxa = 0.20;

	public ContaCorrente(int agencia, int numeroConta, TipoConta tipoConta) {
		super(agencia, numeroConta, tipoConta);
	}
	
	@Override
	public void sacar(double valor) {
		double valorMaisTaxa = valor + taxa; //R$ 0,20 de taxa para cada saque
		super.sacar(valorMaisTaxa);
	}
	
	public double consultarTaxaDeSaque() {
		return taxa;
	}

}
