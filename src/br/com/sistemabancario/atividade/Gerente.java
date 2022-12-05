package br.com.sistemabancario.atividade;

import java.util.Scanner;

public class Gerente {

	private ClientePF[] clientesPF;
	private ClientePJ[] clientesPJ;

	public Gerente() { // não necessita de parametros para ser instanciado.
		this.clientesPF = new ClientePF[20];
		this.clientesPJ = new ClientePJ[20];
	}

	public ClientePF[] getClientesPF() {
		return clientesPF;
	}

	public ClientePJ[] getClientesPJ() {
		return clientesPJ;
	}

	public void cadastraClientePF(Scanner teclado) {

		System.out.println("---- NOVO CADASTRO -----");
		System.out.println("# Digite o número da conta: #");
		int numConta = teclado.nextInt();
		System.out.println("# Digite o número da agencia: #");
		int agencia = teclado.nextInt();
		teclado.nextLine();
		System.out.println("# Digite o número de telefone: #");
		String telefone = teclado.next();
		System.out.println("# Digite o saldo disponível para o cliente: #");
		double saldo = teclado.nextDouble();
		System.out.println("# Digite o limite do cheque especial: #");
		double limiteChequeEspecial = teclado.nextDouble();
		teclado.nextLine();
		System.out.println("# Digite o CPF do cliente: #");
		String cpf = teclado.next();
		System.out.println("# Digite o nome do cliente: #");
		String nome = teclado.next();
		System.out.println("# Digite a idade do cliente: #");
		int idade = teclado.nextInt();

		ClientePF clienteASerInserido = new ClientePF(numConta, agencia, telefone, saldo, limiteChequeEspecial, cpf,
				nome, idade);
		
		for (int i = 0; i < this.clientesPF.length;) {
			if (this.clientesPF[i] != null) {
				i++;
			}else {
				this.clientesPF[i] = clienteASerInserido;
				break;
			}
		}
	}

	// METODO CADASTRA CLIENTE PJ
	public void cadastraClientePJ(Scanner teclado) {

		int count = 0;

		System.out.println("---- NOVO CADASTRO -----");

		System.out.println("# Digite o número da conta: #");
		int numConta = teclado.nextInt();
		System.out.println("# Digite o número da agencia: #");
		int agencia = teclado.nextInt();
		teclado.nextLine();
		System.out.println("# Digite o número de telefone: #");
		String telefone = teclado.next();
		System.out.println("# Digite o saldo disponível para o cliente: #");
		double saldo = teclado.nextDouble();
		System.out.println("# Digite o limite do cheque especial: #");
		double limiteChequeEspecial = teclado.nextDouble();
		teclado.nextLine();
		System.out.println("# Digite o CNPJ do cliente: #");
		String cnpj = teclado.next();
		teclado.nextLine();
		System.out.println("# Digite a razão social: #");
		String razaoSocial = teclado.next();
		teclado.nextLine();
		System.out.println("# Digite o nome fantasia: #");
		String nomeFantasia = teclado.next();

		ClientePJ clienteASerInserido = new ClientePJ(numConta, agencia, telefone, saldo, limiteChequeEspecial, cnpj,
				razaoSocial, nomeFantasia);

		int opcaoCadastraSocio = -1;

		System.out.println("# Deseja cadastrar socios? #");
		System.out.println("1 - SIM");
		System.out.println("2 - NÃO");
		opcaoCadastraSocio = teclado.nextInt();

		if (opcaoCadastraSocio == 1) {
			clienteASerInserido.cadastraSocio(teclado);
		} else {
			System.out.println("Você escolheu não adicionar nenhum socio");
			teclado.nextLine();
		}

		for (int i = 0; i < this.clientesPJ.length; i++) {
			if (this.clientesPJ[i] != null) {
				count++;
			} else {
				this.clientesPJ[count] = clienteASerInserido;
				break;
			}

		}
	}

	public void removeCliente(Scanner teclado) {
		System.out.println("Digite o número da conta que deseja remover: ");
		int numContaASerRemovida = teclado.nextInt();
		int i;
		try {

			for (i = 0; i < this.clientesPJ.length; i++) {
				if (this.clientesPJ[i].getNumConta() == numContaASerRemovida) {
					this.clientesPJ[i] = null;
					System.out.println("Cliente removido com sucesso!");

					if (this.clientesPJ[i].getSocios() != null) {
						for (int count = 0; count < this.clientesPJ[i].getSocios().length; count++) {
							if (this.clientesPJ[i].getSocios()[count].getNome() != null) {
								this.clientesPJ[i].getSocios()[count] = null;
							}
						}
					}
				} else if (this.clientesPF[i].getNumConta() == numContaASerRemovida) {
					this.clientesPF[i] = null;
					System.out.println("Cliente removido com sucesso!");

				}
			}

		} catch (NullPointerException e) {
		}

	}

	public void consultaCliente(Scanner teclado) {
		System.out.println("Digite o número da conta que deseja consultar: ");
		int numContaASerConsultada = teclado.nextInt();
		int i;
		try {
			for (i = 0; i < this.clientesPJ.length; i++) {
				if (this.clientesPJ[i] != null) {
					if (this.clientesPJ[i].getNumConta() == numContaASerConsultada) {
						System.out.println("Número da conta: " + this.clientesPJ[i].getNumConta());
						System.out.println("Número da agência: " + this.clientesPJ[i].getAgencia());
						System.out.println("Número do telefone: " + this.clientesPJ[i].getTelefone());
						System.out.println("Saldo atual: " + this.clientesPJ[i].getSaldo());
						System.out.println(
								"Limite de Cheque especial atual: " + this.clientesPJ[i].getLimiteChequeEspecial());
						System.out.println("CNPJ: " + this.clientesPJ[i].getCnpj());
						System.out.println("Razão social: " + this.clientesPJ[i].getRazaoSocial());
						System.out.println("Nome fantasia: " + this.clientesPJ[i].getNomeFantasia());

						for (int count = 0; count < this.clientesPJ[i].getSocios().length; count++) {
							if (this.clientesPJ[i].getSocios()[count].getNome() != null) {
								System.out.println("Socio " + (count + 1) + ": "
										+ this.clientesPJ[i].getSocios()[count].getNome());
							}
						}
					}
				}
			}
			for (i = 0; i < this.clientesPJ.length; i++) {
				if (this.clientesPF[i] != null) {
					if (this.clientesPF[i].getNumConta() == numContaASerConsultada) {
						System.out.println("Número da conta: " + this.clientesPF[i].getNumConta());
						System.out.println("Número da agência: " + this.clientesPF[i].getAgencia());
						System.out.println("Número do telefone: " + this.clientesPF[i].getTelefone());
						System.out.println("Saldo atual: " + this.clientesPF[i].getSaldo());
						System.out.println(
								"Limite de Cheque especial atual: " + this.clientesPF[i].getLimiteChequeEspecial());
						System.out.println("CPF: " + this.clientesPF[i].getCpf());
						System.out.println("Nome: " + this.clientesPF[i].getNome());
						System.out.println("Idade: " + this.clientesPF[i].getIdade());

					}
				}
			}

		} catch (NullPointerException e) {

		}
	}

	public void atualizaLimiteChequeEsp(Scanner teclado) {
		System.out.println("Digite o número da conta que deseja consultar: ");
		int numContaASerConsultada = teclado.nextInt();
		int i;

		try {
			for (i = 0; i < 100; i++) {
				if (this.clientesPJ[i].getNumConta() == numContaASerConsultada) {
					System.out.println("Digite o novo limite de cheque especial: ");
					double novoLimite = teclado.nextDouble();
					this.clientesPJ[i].setLimiteChequeEspecial(novoLimite);

				} else if (this.clientesPF[i].getNumConta() == numContaASerConsultada) {
					System.out.println("Digite o novo limite de cheque especial: ");
					double novoLimite = teclado.nextDouble();
					this.clientesPF[i].setLimiteChequeEspecial(novoLimite);

				} else {
					System.out.println("Conta não encontrada.");
				}
			}
		} catch (NullPointerException e) {

		}
	}

	public void adicionaSaldo(Scanner teclado) {
		System.out.println("Digite o número da conta que deseja adicionar saldo: ");
		int numContaASerConsultada = teclado.nextInt();
		int i;
		try {

			for (i = 0; i < this.clientesPJ.length; i++) {
				if (clientesPJ[i] != null) {
					if (this.clientesPJ[i].getNumConta() == numContaASerConsultada) {
						System.out.println("Digite o saldo que será adicionado: ");
						double saldoAdicionado = teclado.nextDouble();
						this.clientesPJ[i].setSaldo(this.clientesPJ[i].getSaldo() + saldoAdicionado);

					}
				}
			}
			for (i = 0; i < this.clientesPF.length; i++) {
				if (clientesPF[i] != null) {
					if (this.clientesPF[i].getNumConta() == numContaASerConsultada) {
						System.out.println("Digite o saldo que será adicionado: ");
						double saldoAdicionado = teclado.nextDouble();
						this.clientesPF[i].setSaldo(this.clientesPF[i].getSaldo() + saldoAdicionado);

					}
				}
			}
		} catch (NullPointerException e) {

		}
	}

	public void acessaRelatorioClientes(Scanner teclado) {
		int i;
		int count = 0;
		try {
			for (i = 0; i < this.clientesPJ.length; i++) {
				if (this.clientesPJ[i] != null) {
					System.out.println("Razão social: " + this.getClientesPJ()[i].getRazaoSocial());
					System.out.println("Número da conta: " + this.getClientesPJ()[i].getNumConta());
					System.out.println("#----------------------#");
					if (this.getClientesPJ()[i].getSocios()[count] != null) {
						System.out.println("Socios: 1- " + this.getClientesPJ()[i].getSocios()[count].getNome());
						System.out.println("        2- " + this.getClientesPJ()[i].getSocios()[count + 1].getNome());
						System.out.println("        3- " + this.getClientesPJ()[i].getSocios()[count + 1].getNome());
						System.out.println("#----------------------#");
					}

					count++;
				}
			}

			for (i = 0; i < this.clientesPF.length; i++) {
				if (this.clientesPF[i] != null) {
					System.out.println("Nome: " + this.getClientesPF()[i].getNome());
					System.out.println("Número da conta: " + this.getClientesPF()[i].getNumConta());
					System.out.println("#----------------------#");

				}

			}
		} catch (NullPointerException e) {

		}

	}

	public boolean realizaTranferenciasEntreClientes(Scanner teclado) {

		System.out.println("Digite o número da conta que irá fazer a transferencia: ");
		int numContaATransferir = teclado.nextInt();
		System.out.println("Digite o número da conta que irá receber a transferencia: ");
		int numContaAReceber = teclado.nextInt();
		int i;
		double valorASerTransferido = 0;

		try {
			for (i = 0; i < this.clientesPJ.length; i++) {
				if (this.clientesPJ[i] != null) {
					if (this.clientesPJ[i].getNumConta() == numContaATransferir) {
						System.out.println("Digite o valor que será transferido: ");
						valorASerTransferido = teclado.nextDouble();

						if (this.clientesPJ[i].getSaldo()
								+ this.clientesPJ[i].getLimiteChequeEspecial() >= valorASerTransferido) {
							if (this.clientesPJ[i].getSaldo() >= valorASerTransferido) {
								this.clientesPJ[i].setSaldo(this.clientesPJ[i].getSaldo() - valorASerTransferido);

							} else {
								double sobra = valorASerTransferido - this.clientesPJ[i].getSaldo();
								this.clientesPJ[i]
										.setLimiteChequeEspecial(this.clientesPJ[i].getLimiteChequeEspecial() - sobra);
								this.clientesPJ[i].setSaldo(0.0);
							}
						} else {
							System.out.println("Você não possui saldo suficiente");
						}
						if (this.clientesPJ[i].getNumConta() == numContaAReceber) {
							this.clientesPJ[i].setSaldo(this.clientesPJ[i].getSaldo() + valorASerTransferido);

						} else if (this.clientesPF[i].getNumConta() == numContaAReceber) {
							this.clientesPF[i].setSaldo(this.clientesPF[i].getSaldo() + valorASerTransferido);

						}
					}
				}
			}
			for (i = 0; i < this.clientesPF.length; i++) {
				if (this.clientesPF[i] != null) {
					if (this.clientesPF[i].getNumConta() == numContaATransferir) {
						System.out.println("Digite o valor que será transferido: ");
						valorASerTransferido = teclado.nextDouble();

						if (this.clientesPF[i].getSaldo()
								+ this.clientesPF[i].getLimiteChequeEspecial() >= valorASerTransferido) {
							if (this.clientesPF[i].getSaldo() >= valorASerTransferido) {
								this.clientesPF[i].setSaldo(this.clientesPF[i].getSaldo() - valorASerTransferido);

							} else {
								double sobra = valorASerTransferido - this.clientesPF[i].getSaldo();
								this.clientesPF[i]
										.setLimiteChequeEspecial(this.clientesPF[i].getLimiteChequeEspecial() - sobra);
								this.clientesPF[i].setSaldo(0.0);
							}
						} else {
							System.out.println("Você não possui saldo suficiente!");
						}
						if (this.clientesPJ[i].getNumConta() == numContaAReceber) {
							this.clientesPJ[i].setSaldo(this.clientesPJ[i].getSaldo() + valorASerTransferido);

						} else if (this.clientesPF[i].getNumConta() == numContaAReceber) {
							this.clientesPF[i].setSaldo(this.clientesPF[i].getSaldo() + valorASerTransferido);

						}

					}

				}
			}

		} catch (

		NullPointerException e) {

		}

		return true;
	}

	public boolean realizaSaque(Scanner teclado) {

		System.out.println("Digite o número da conta: ");
		int numConta = teclado.nextInt();
		int i;
		double valorSaque;

		try {
			for (i = 0; i < this.clientesPJ.length; i++) {
				if (this.clientesPJ[i] != null) {
					if (this.clientesPJ[i].getNumConta() == numConta) {

						System.out.println("Digite o valor do saque: ");
						valorSaque = teclado.nextDouble();

						if (clientesPJ[i].getSaldo() >= valorSaque) {

							this.clientesPJ[i].setSaldo(this.clientesPJ[i].getSaldo() - valorSaque);
							System.out.println("Operação realizada! seu novo saldo é R$" + clientesPJ[i].getSaldo());
							return true;
						} else {
							System.out.println("Saldo insuficiente");
						}
					} else {
						System.out.println("Conta não encontrada.");
					}
				}
			}

			for (i = 0; i < this.clientesPF.length; i++) {
				if (this.clientesPF[i] != null) {
					if (this.clientesPF[i].getNumConta() == numConta) {

						System.out.println("Digite o valor do saque: ");
						valorSaque = teclado.nextDouble();

						if (clientesPF[i].getSaldo() >= valorSaque) {
							this.clientesPF[i].setSaldo(this.clientesPF[i].getSaldo() - valorSaque);
							System.out.println("Operação realizada! seu novo saldo é R$" + clientesPF[i].getSaldo());
							return true;
						} else {
							System.out.println("Saldo insuficiente");
						}
					} else {
						System.out.println("Conta não encontrada.");
					}
				}
			}

		} catch (

		NullPointerException e) {

		}
		return false;

	}
}
