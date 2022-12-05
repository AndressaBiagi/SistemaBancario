package br.com.sistemabancario.atividade;

import java.util.Scanner;

public class ClientePJ extends Cliente {

	private String cnpj;
	private SocioDeClientePJ[] socios;
	private String razaoSocial;
	private String nomeFantasia;

	public ClientePJ(int numConta, int agencia, String telefone, double saldo, double limiteChequeEspecial, String cnpj,
			String razaoSocial, String nomeFantasia) {
		super(numConta, agencia, telefone, saldo, limiteChequeEspecial);

		this.cnpj = cnpj;
		this.socios = new SocioDeClientePJ[3];
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
	}

	public SocioDeClientePJ[] getSocios() {
		return socios;
	}

	public void setSocios(SocioDeClientePJ[] socios) {
		this.socios = socios;
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

	public void cadastraSocio(Scanner teclado) {
		int count = 0;
		for (int i = 0; i <= this.socios.length + 1; i++) {

			if (count < this.socios.length + 1) {
				if (this.socios[count] == null) {

					System.out.println("## Digite o nome do socio: #");
					String nomeSocioASerCadastrado = teclado.next();
					SocioDeClientePJ socioASerInserido = new SocioDeClientePJ(nomeSocioASerCadastrado);

					this.socios[count] = socioASerInserido;

				} else {
					count++;
				}
			}

		}

	}

}
