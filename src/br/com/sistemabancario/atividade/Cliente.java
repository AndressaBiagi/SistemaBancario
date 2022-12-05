package br.com.sistemabancario.atividade;

public class Cliente {

	private int agencia;
	private String telefone;
	private double saldo;
	private double limiteChequeEspecial;

	public Cliente(int numConta, int agencia, String telefone, double saldo, double limiteChequeEspecial) {
		this.numConta = numConta;
		this.agencia = agencia;
		this.telefone = telefone;
		this.saldo = saldo;
		this.limiteChequeEspecial = limiteChequeEspecial;
	}

	private int numConta;

	public int getNumConta() {
		return numConta;
	}

	public int getAgencia() {
		return agencia;
	}

	public String getTelefone() {
		return telefone;
	}

	public double getSaldo() {
		return saldo;
	}

	public double getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setLimiteChequeEspecial(double limiteChequeEspecial) {
		this.limiteChequeEspecial = limiteChequeEspecial;
	}

}
