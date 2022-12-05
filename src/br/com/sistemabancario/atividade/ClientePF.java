package br.com.sistemabancario.atividade;

public class ClientePF extends Cliente {

	private String cpf;
	private String nome;
	private int idade;

	public ClientePF(int numConta, int agencia, String telefone, double saldo, double limiteChequeEspecial, String cpf,
			String nome, int idade) {
		super(numConta, agencia, telefone, saldo, limiteChequeEspecial);
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;

	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public int getIdade() {
		return idade;
	}

}
