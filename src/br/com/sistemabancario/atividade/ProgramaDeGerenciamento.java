package br.com.sistemabancario.atividade;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProgramaDeGerenciamento {
	public static int menuDeGestao(Scanner teclado) {
		int opcao;
		System.out.println(" -- GESTÃO DE CLIENTES -- ");

		System.out.println(" -Digite a opção desejada- ");
		System.out.println("1 - Cadastrar novo cliente PF");
		System.out.println("2 - Cadastrar novo cliente PJ");
		System.out.println("3 - Remover um cliente");
		System.out.println("4 - Consultar dados de um cliente");
		System.out.println("5 - Mudar o limite de cheque especial");
		System.out.println("6 - Efetuar uma transferência entre clientes");
		System.out.println("7 - Adicionar saldo a uma conta");
		System.out.println("8 - Acessar o relatório de clientes gerenciados");
		System.out.println("9 - METODO EXTRA - realizar saque");
		System.out.println("0 - Encerrar o programa");
		opcao = teclado.nextInt();
		return opcao;
	}

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		Gerente gerente = new Gerente();

		int opcao;
		do {
			opcao = menuDeGestao(teclado);
			try {

				switch (opcao) {
				case 1:
					gerente.cadastraClientePF(teclado);
					break;
				case 2:
					gerente.cadastraClientePJ(teclado);
					break;
				case 3:
					gerente.removeCliente(teclado);
					break;
				case 4:
					gerente.consultaCliente(teclado);
					break;
				case 5:
					gerente.atualizaLimiteChequeEsp(teclado);
					break;
				case 6:
					gerente.realizaTranferenciasEntreClientes(teclado);
					break;
				case 7:
					gerente.adicionaSaldo(teclado);
					break;
				case 8:
					System.out.println("# LISTA DE CLIENTES GERENCIADOS ATUALMENTE #\n");
					gerente.acessaRelatorioClientes(teclado);
					break;
				case 9:
					gerente.realizaSaque(teclado);
					break;
				default:
				}
			} catch (InputMismatchException e) {
				System.out.println("Erro! refaça a operação");
				teclado.nextLine();
			}

		} while (opcao != 0);

		teclado.close();
	}
}
