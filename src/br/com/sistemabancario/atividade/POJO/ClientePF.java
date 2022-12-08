package br.com.sistemabancario.atividade.POJO;

public class ClientePF extends Cliente {

	private String cpf;
	private String nome;
	private int idade;

	public ClientePF(int numConta, int agencia, String telefone, double saldo, double limiteChequeEspecial, 
			String nome, int idade, String cpf) {
		super(numConta, agencia, telefone, saldo, limiteChequeEspecial);
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;

	}

	public String getNome() {
		return nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public int getIdade() {
		return idade;
	}

}
