package br.com.sistemabancario.atividade;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.sistemabancario.atividade.DAO.GerenteDao;
import br.com.sistemabancario.atividade.POJO.ClientePF;
import br.com.sistemabancario.atividade.POJO.ClientePJ;

public class ProgramaDeGerenciamento {
	public static int menuDeGestao(Scanner teclado) {
		int opcao;
		System.out.println(" -- GESTÃO DE CLIENTES -- ");

		System.out.println(" -Digite a opção desejada- ");
		System.out.println("##------------------------##");
		System.out.println("1 - CADASTRO DE CLIENTE PJ");
		System.out.println("2 - CADASTRO DE CLIENTE PF");
		System.out.println("3 - EXCLUIR UM CLIENTE");
		System.out.println("4 - CONSULTA DE DADOS");
		System.out.println("5 - ATUALIZAÇÃO DE LIMITE DE CHEQUE ESPECIAL");
		System.out.println("6 - ADICIONAR SALDO");
		System.out.println("7 - SAQUE");
		System.out.println("8 - TRANFERÊNCIA ENTRE CLIENTES");
		System.out.println("9 - RELATÓRIO DE CLIENTES GERENCIADOS");
		System.out.println("0 - Encerrar o programa");
		opcao = teclado.nextInt();
		return opcao;
	}

	public static void cadastraClientePJ(GerenteDao gerenteDao, Scanner teclado) {

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

		if (gerenteDao.insereClientePJ(clienteASerInserido)) {
			System.out.println("Cliente PJ cadastrado com sucesso!");
		}

	}

	public static void cadastraClientePF(GerenteDao gerenteDao, Scanner teclado) {

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

		ClientePF clienteASerInserido = new ClientePF(numConta, agencia, telefone, saldo, 
				limiteChequeEspecial, nome, idade, cpf);

		if (gerenteDao.insereClientePF(clienteASerInserido)) {
			System.out.println("Cliente PJ cadastrado com sucesso!");
		}

	}

	private static void remocaoCliente(GerenteDao gerenteDao, Scanner teclado) {
		System.out.println(" -- REMOÇÃO DE CLIENTE -- ");
		System.out.println("A conta que você deseja remover é: ");
		System.out.println("1- PF");
		System.out.println("2- PJ");
		int resposta = teclado.nextInt();

		System.out.println("##-------------##");
		System.out.println("Digite o numero da conta que deseja remover: ");
		int numConta = teclado.nextInt();

		if (resposta == 1) {
			if (gerenteDao.removeClientePF(numConta)) {
				System.out.println("Conta PF removida com sucesso!");
			}
		} else if (resposta == 2) {
			if (gerenteDao.removeClientePJ(numConta)) {
				System.out.println("Conta PJ removida com sucesso!");
			}
		} else {
			System.out.println("Opção inválida! ");
		}

	}

	private static void imprimeCliente(GerenteDao gerenteDao, Scanner teclado) {
		System.out.println(" -- CONSULTA DE INFORMAÇÕES -- ");
		System.out.println("A conta que você deseja consultar é: ");
		System.out.println("1- PF");
		System.out.println("2- PJ");
		int resposta = teclado.nextInt();

		System.out.println("##-------------##");
		System.out.println("Digite o numero da conta que deseja consultar: ");
		int numConta = teclado.nextInt();

		if (resposta == 1) {
			System.out.println("Nome: " + gerenteDao.consultaClientePF(numConta).getNome());
			System.out.println("Saldo Disponível: " + gerenteDao.consultaClientePF(numConta).getSaldo());
			System.out.println(
					"Limite de cheque especial: " + gerenteDao.consultaClientePF(numConta).getLimiteChequeEspecial());
		} else if (resposta == 2) {
			System.out.println("Razão social: " + gerenteDao.consultaClientePJ(numConta).getRazaoSocial());
			System.out.println("Saldo Disponível: " + gerenteDao.consultaClientePJ(numConta).getSaldo());
			System.out.println(
					"Limite de cheque especial: " + gerenteDao.consultaClientePJ(numConta).getLimiteChequeEspecial());
		} else {
			System.out.println("Opção inválida! ");
		}

	}

	private static void mudaLimiteChequeEspecial(GerenteDao gerenteDao, Scanner teclado) {
		System.out.println(" -- ATUALIZAÇÃO LIMITE DE CHEQUE ESPECIAL -- ");
		System.out.println("A conta que você deseja atualizar é: ");
		System.out.println("1- PF");
		System.out.println("2- PJ");
		int resposta = teclado.nextInt();

		System.out.println("##-------------##");
		System.out.println("Digite o numero da conta que deseja atualizar: ");
		int numConta = teclado.nextInt();
		System.out.println("Digite o novo limite do cheque especial: ");
		double novoLimite = teclado.nextDouble();

		if (resposta == 1) {
			if (gerenteDao.atualizaLimiteChequeEspecialClientePF(numConta, novoLimite)) {
				System.out.println("Limite atualizado! Seu novo limite é: " + novoLimite);
			}
		} else if (resposta == 2) {
			if (gerenteDao.atualizaLimiteChequeEspecialClientePJ(numConta, novoLimite)) {
				System.out.println("Limite atualizado! Seu novo limite é: " + novoLimite);
			}
		} else {
			System.out.println("Opção inválida! ");
		}

	}

	private static void adicionaSaldo(GerenteDao gerenteDao, Scanner teclado) {
		System.out.println(" -- AUMENTO DE SALDO -- ");
		System.out.println("A conta que você deseja atualizar é: ");
		System.out.println("1- PF");
		System.out.println("2- PJ");
		int resposta = teclado.nextInt();

		System.out.println("##-------------##");
		System.out.println("Digite o numero da conta que deseja atualizar: ");
		int numConta = teclado.nextInt();
		System.out.println("Digite o valor que será adicionado: ");
		double novoSaldo = teclado.nextDouble();

		if (resposta == 1) {
			if(gerenteDao.adicionaSaldoClientePF(numConta, novoSaldo)) {
				System.out.println("Operação realizada com sucesso!");
			}

		} else if (resposta == 2) {
			if(gerenteDao.adicionaSaldoClientePJ(numConta, novoSaldo)) {
				System.out.println("Operação realizada com sucesso!");
			}
		} else {
			System.out.println("Opção inválida! ");
		}

	}

	private static void realizaSaque(GerenteDao gerenteDao, Scanner teclado) {
		System.out.println(" -- SAQUE -- ");
		System.out.println("A conta de onde você deseja sacar é: ");
		System.out.println("1- PF");
		System.out.println("2- PJ");
		int resposta = teclado.nextInt();

		System.out.println("##-------------##");
		System.out.println("Digite o numero da conta: ");
		int numConta = teclado.nextInt();
		System.out.println("Digite o valor que será retirado: ");
		double valorSaque = teclado.nextDouble();

		if (resposta == 1) {
			if(gerenteDao.RealizaSaqueClientePF(numConta, valorSaque)) {
				System.out.println("Operação realizada com sucesso!");
			}

		} else if (resposta == 2) {
			if(gerenteDao.RealizaSaqueClientePJ(numConta, valorSaque)) {
				System.out.println("Operação realizada com sucesso!");
			}
		} else {
			System.out.println("Opção inválida! ");
		}
	}

	/*private static void transferenciaEntreClientes(GerenteDao gerenteDao, Scanner teclado) {
		System.out.println(" -- TRANSFERÊNCIA ENTRE CLIENTES -- ");
		System.out.println("A conta que irá fazer a transferência é: ");
		System.out.println("1- PF");
		System.out.println("2- PJ");
		int resposta = teclado.nextInt();

		System.out.println("##-------------##");
		System.out.println("Digite o numero da conta que irá transferir: ");
		int numContaTransfere = teclado.nextInt();
		System.out.println("Digite o numero da conta que irá receber: ");
		int numContaRecebe = teclado.nextInt();
		System.out.println("Digite o valor que será transferido: ");
		double valorTransferido = teclado.nextDouble();

		if (resposta == 1) {
			gerenteDao.realizaTransferenciaClientePF(numContaTransfere, numContaRecebe, valorTransferido);

		} else if (resposta == 2) {
			if(gerenteDao.realizaTransferenciaClientePJ(numContaTransfere, numContaRecebe, valorTransferido)) {
				System.out.println("Operação realizada com sucesso!");
			}
		} else {
			System.out.println("Opção inválida! ");
		}
	}
*/
	private static void imprimeClientes(GerenteDao gerenteDao) {
		System.out.println("-- CLIENTES PF --");
		List<ClientePF> clientesPFNoDb = gerenteDao.listaClientesPF();
		for (ClientePF clienteEncontradoNaListagem : clientesPFNoDb) {
			System.out.println(clienteEncontradoNaListagem.getNome());
			System.out.println(clienteEncontradoNaListagem.getSaldo());
			System.out.println(clienteEncontradoNaListagem.getLimiteChequeEspecial());
		}
		
		System.out.println("-- CLIENTES PJ --");
		List<ClientePJ> clientesPJNoDb = gerenteDao.listaClientesPJ();
		for (ClientePJ clienteEncontradoNaListagem : clientesPJNoDb) {
			System.out.println(clienteEncontradoNaListagem.getRazaoSocial());
			System.out.println(clienteEncontradoNaListagem.getSaldo());
			System.out.println(clienteEncontradoNaListagem.getLimiteChequeEspecial());		}

	}

	public static void main(String[] args) {
		GerenteDao gerenteDao = new GerenteDao();

		Scanner teclado = new Scanner(System.in);

		int opcao;
		do {
			opcao = menuDeGestao(teclado);
			try {

				switch (opcao) {
				case 1:
					cadastraClientePJ(gerenteDao, teclado);
					break;
				case 2:
					cadastraClientePF(gerenteDao, teclado);
					break;
				case 3:
					remocaoCliente(gerenteDao, teclado);
					break;
				case 4:
					imprimeCliente(gerenteDao, teclado);
					break;
				case 5:
					mudaLimiteChequeEspecial(gerenteDao, teclado);
					break;
				case 6:
					adicionaSaldo(gerenteDao, teclado);
					break;
				case 7:
					realizaSaque(gerenteDao, teclado);
					break;
				/*case 8:
					transferenciaEntreClientes(gerenteDao, teclado);
					break;*/
				case 9:
					imprimeClientes(gerenteDao);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Erro! refaça a operação");
				teclado.nextLine();
			}

		} while (opcao != 0);

		teclado.close();
	}
}
