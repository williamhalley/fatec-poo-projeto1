package br.edu.fateccarapicuiba.poo.projeto1;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public abstract class Conta {
	
	private int agencia;
	private int numeroConta;	
	private TipoConta tipoConta;  //private String tipoConta;
	private double saldo;
	private Cliente titular;
	private static int totalContas = 0;
	
	public Conta(int agencia, int numeroConta,  TipoConta tipoConta) {
		this.agencia = agencia;
		this.numeroConta = numeroConta;
		this.tipoConta = tipoConta;
		Conta.totalContas++;
		//this.titular = new Cliente(nome, cpf)); //cliente já pode exisitr por ter outras contas
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public String getTipoConta() {
		return tipoConta.getValor();
	}

	public double consultarSaldo() {
		return saldo;
	}

	public void depositar(double valor) {
		if(valor <= 0.0) {
			throw new IllegalArgumentException("Valor do depósito deve ser maior que zero");
		}
		this.saldo += valor;
	}
	
	public void depositar(int valor) {
		if(valor <= 0) {
			throw new IllegalArgumentException("Valor do depósito deve ser maior que zero");
		}
		this.saldo += valor;
	}
	
	public void sacar(double valor) {
		if(valor <= 0) {
			throw new IllegalArgumentException("Valor do saque deve ser maior que zero");
		}
		if(valor > this.saldo) {
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		this.saldo -= valor;
	}
	
	public void transferir(double valor, Conta destino) {
		this.sacar(valor);
		destino.depositar(valor);
	}

	public static int getTotalContas() {
		return Conta.totalContas;
	}

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		return "\nAgência: " + agencia + "\n" + 
				this.tipoConta.getValor() + ": " + numeroConta +
				"\nSaldo: R$ " + df.format(saldo);
	}

}
