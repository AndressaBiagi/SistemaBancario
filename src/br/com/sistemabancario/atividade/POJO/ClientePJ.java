package br.com.sistemabancario.atividade.POJO;

public class ClientePJ extends Cliente {

	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;

	public ClientePJ(int numConta, int agencia, String telefone, double saldo, double limiteChequeEspecial, String cnpj,
			String razaoSocial, String nomeFantasia) {
		super(numConta, agencia, telefone, saldo, limiteChequeEspecial);

		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

}
