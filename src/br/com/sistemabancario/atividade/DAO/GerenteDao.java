package br.com.sistemabancario.atividade.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemabancario.atividade.POJO.ClientePF;
import br.com.sistemabancario.atividade.POJO.ClientePJ;
import br.com.sistemabancario.atividade.connectionfactory.ConnectionFactory;

public class GerenteDao {

	private Connection conn;

	public GerenteDao() {
		this.conn = ConnectionFactory.getConnection();
	}

	public boolean insereClientePJ(ClientePJ clientePJ) {

		String sql = "INSERT INTO sistema_bancario_atividade.clientes_pj("
				+ "	num_conta, agencia, telefone, saldo, limite_cheque_especial, cnpj, razao_social, noma_fantasia)"
				+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		// STATEMENTS
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql); // CHAMADA DO METODO DE ABERTURA DA CONEXÃO
			stmt.setInt(1, clientePJ.getNumConta());
			stmt.setInt(2, clientePJ.getAgencia());
			stmt.setString(3, clientePJ.getTelefone());
			stmt.setDouble(4, clientePJ.getSaldo());
			stmt.setDouble(5, clientePJ.getLimiteChequeEspecial());
			stmt.setString(6, clientePJ.getCnpj());
			stmt.setString(7, clientePJ.getRazaoSocial());
			stmt.setString(8, clientePJ.getNomeFantasia());
			stmt.execute();
			stmt.close();
			System.out.println("Dados enviados");
			return true;
		} catch (SQLException e) {
			System.err.println("Erro ao enviar dado para o banco de dados");
			System.err.println(e.getMessage());
			return false;
		}
	}

	public boolean removeClientePJ(int numConta) {

		String sql = "DELETE FROM sistema_bancario_atividade.clientes_pj" + "	WHERE num_conta = ?;";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, numConta);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			System.err.println("Erro ao remover dados do banco");
			System.err.println(e.getMessage());
			return false;

		}
	}

	public boolean insereClientePF(ClientePF clientePF) {

		String sql = "INSERT INTO sistema_bancario_atividade.clientes_pf("
				+ "	num_conta, agencia, telefone, saldo, limite_cheque_especial, nome, idade, cpf)"
				+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, clientePF.getNumConta());
			stmt.setInt(2, clientePF.getAgencia());
			stmt.setString(3, clientePF.getTelefone());
			stmt.setDouble(4, clientePF.getSaldo());
			stmt.setDouble(5, clientePF.getLimiteChequeEspecial());
			stmt.setString(6, clientePF.getNome());
			stmt.setInt(7, clientePF.getIdade());
			stmt.setString(8, clientePF.getCpf());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			System.err.println("Erro ao enviar dado para o banco de dados");
			System.err.println(e.getMessage());
			return false;
		}
	}

	public boolean removeClientePF(int numConta) {

		
		String sql = "DELETE FROM sistema_bancario_atividade.clientes_pf" + "	WHERE num_conta = ?;";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, numConta);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			System.err.println("Erro ao remover dados do banco");
			System.err.println(e.getMessage());
			return false;

		}
	}

	public ClientePF consultaClientePF(int numConta) {

		String sql = "SELECT * FROM sistema_bancario_atividade.clientes_pf WHERE num_conta = ?";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, numConta);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				ClientePF clienteRetornado = new ClientePF(numConta, rs.getInt("agencia"), sql, rs.getDouble("saldo"),
						rs.getDouble("limite_cheque_especial"), rs.getString("nome"), rs.getInt("idade"), rs.getString("cpf"));

				clienteRetornado.setNome(rs.getString("nome"));
				clienteRetornado.setSaldo(rs.getDouble("saldo"));
				clienteRetornado.setLimiteChequeEspecial(rs.getDouble("limite_cheque_especial"));

				return clienteRetornado;
			}

		} catch (SQLException e) {
			System.err.println("Erro ao consultar cliente PF.");
		}

		return null;
	}

	public ClientePJ consultaClientePJ(int numConta) {

		String sql = "SELECT * FROM sistema_bancario_atividade.clientes_pj WHERE num_conta = ?";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, numConta);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				ClientePJ clienteRetornado = new ClientePJ(numConta, numConta, sql, numConta, numConta, sql, sql, sql);

				clienteRetornado.setRazaoSocial(rs.getString("razao_social"));
				clienteRetornado.setSaldo(rs.getDouble("saldo"));
				clienteRetornado.setLimiteChequeEspecial(rs.getDouble("limite_cheque_especial"));

				return clienteRetornado;
			}

		} catch (SQLException e) {
			System.err.println("Erro ao consultar cliente PJ.");
		}

		return null;
	}

	public boolean atualizaLimiteChequeEspecialClientePF(int numConta, double novoLimite) {

		String sql = "UPDATE sistema_bancario_atividade.clientes_pf" + "	SET limite_cheque_especial=?"
				+ "	WHERE num_conta=?;";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, numConta);
			stmt.setDouble(5, novoLimite);
			stmt.execute();
			stmt.close();
			return true;

		} catch (SQLException e) {
			System.err.println("Erro ao consultar cliente PF.");
			return false;
		}
	}

	public boolean atualizaLimiteChequeEspecialClientePJ(int numConta, double novoLimite) {

		String sql = "UPDATE sistema_bancario_atividade.clientes_pj" + "	SET limite_cheque_especial=?"
				+ "	WHERE num_conta=?;";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, numConta);
			stmt.setDouble(5, novoLimite);
			stmt.execute();
			stmt.close();
			return true;

		} catch (SQLException e) {
			System.err.println("Erro ao consultar cliente PJ.");
			return false;
		}
	}

	public boolean adicionaSaldoClientePF(int numConta, double saldoAdicionado) {

		String sql = "SELECT * FROM sistema_bancario_atividade.clientes_pf WHERE num_conta = ?";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, numConta);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				ClientePF clienteRetornado = new ClientePF(numConta, rs.getInt("agencia"), rs.getString("telefone"),
						rs.getDouble("saldo"), rs.getDouble("limite_cheque_especial"), rs.getString("nome"),
						rs.getInt("idade"), rs.getString("cpf"));

				clienteRetornado.setSaldo(clienteRetornado.getSaldo() + saldoAdicionado);

				double novoSaldo = clienteRetornado.getSaldo();

				String sql2 = "UPDATE sistema_bancario_atividade.clientes_pf SET saldo=?" + "	WHERE num_conta=?;";

				PreparedStatement stmt2 = conn.prepareStatement(sql2);
				stmt2.setDouble(1, novoSaldo);
				stmt2.setInt(2, numConta);
				stmt2.executeUpdate();
				stmt.close();

			}
			return true;

		} catch (SQLException e) {
			System.err.println("Erro ao adicionar saldo.");
			return false;
		}
	}

	public boolean adicionaSaldoClientePJ(int numConta, double saldoAdicionado) {

		String sql = "SELECT saldo FROM sistema_bancario_atividade.clientes_pj WHERE num_conta = ?";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, numConta);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				ClientePJ clienteRetornado = new ClientePJ(numConta, 0, sql, 0, 0, sql, sql, sql);

				clienteRetornado.setSaldo(clienteRetornado.getSaldo() + saldoAdicionado);

				double novoSaldo = clienteRetornado.getSaldo();

				String sql2 = "UPDATE sistema_bancario_atividade.clientes_pj SET saldo=?" + "	WHERE num_conta=?;";

				PreparedStatement stmt2 = conn.prepareStatement(sql2);
				stmt2.setDouble(1, novoSaldo);
				stmt2.setInt(2, numConta);
				stmt2.executeUpdate();
				stmt.close();

			}
			return true;

		} catch (SQLException e) {
			System.err.println("Erro ao adicionar saldo.");
			return false;
		}
	}

	public boolean RealizaSaqueClientePF(int numConta, double valorSaque) {

		String sql = "SELECT saldo FROM sistema_bancario_atividade.clientes_pf WHERE num_conta = ?";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, numConta);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				ClientePF clienteRetornado = new ClientePF(numConta, 0, sql, 0, 0, sql, 0, sql);

				clienteRetornado.setSaldo(rs.getDouble("saldo"));

				if (clienteRetornado.getSaldo() >= valorSaque) {
					double novoSaldo = clienteRetornado.getSaldo() - valorSaque;

					String sql2 = "UPDATE sistema_bancario_atividade.clientes_pF SET saldo=?" + "	WHERE num_conta=?;";

					PreparedStatement stmt2 = conn.prepareStatement(sql2);
					stmt2.setDouble(1, novoSaldo);
					stmt2.setInt(2, numConta);
					stmt2.executeUpdate();
					stmt.close();

				} else {
					System.out.println("Saldo insuficiente");
				}
			}
			return true;

		} catch (SQLException e) {
			System.err.println("Erro ao realizar saque.");
			return false;
		}
	}

	public boolean RealizaSaqueClientePJ(int numConta, double valorSaque) {

		String sql = "SELECT saldo FROM sistema_bancario_atividade.clientes_pj WHERE num_conta = ?";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, numConta);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				ClientePJ clienteRetornado = new ClientePJ(numConta, numConta, sql, valorSaque, valorSaque, sql, sql,
						sql);

				clienteRetornado.setSaldo(rs.getDouble("saldo"));

				if (clienteRetornado.getSaldo() >= valorSaque) {
					double novoSaldo = clienteRetornado.getSaldo() - valorSaque;

					String sql2 = "UPDATE sistema_bancario_atividade.clientes_pj SET saldo=?" + "	WHERE num_conta=?;";

					PreparedStatement stmt2 = conn.prepareStatement(sql2);
					stmt2.setDouble(1, novoSaldo);
					stmt2.setInt(2, numConta);
					stmt2.executeUpdate();
					stmt.close();

				} else {
					System.out.println("Saldo insuficiente");
				}

			}
			return true;

		} catch (SQLException e) {
			System.err.println("Erro ao realizar saque.");
			return false;
		}
	}

	/*public boolean realizaTransferenciaClientePJ(int numContaATransferir, int numContaAReceber,
			double valorATransfererir) {

		String sql = "SELECT saldo, limite_cheque_especial FROM sistema_bancario_atividade.clientes_pj WHERE num_conta = ?";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, numContaATransferir);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				ClientePJ clienteATransferir = new ClientePJ(numContaATransferir, 0, sql, rs.getDouble("saldo"),
						rs.getDouble("limite_cheque_especial"), sql, sql, sql);

				if (clienteATransferir.getSaldo()
						+ clienteATransferir.getLimiteChequeEspecial() >= valorATransfererir) {
					// SE O SALDO > VALOR
					if (clienteATransferir.getSaldo() >= valorATransfererir) {

						// MUDANÇA DE SALDO DA CONTA QUE TRANFERE
						clienteATransferir.setSaldo(clienteATransferir.getSaldo() - valorATransfererir);
						double novoSaldo = clienteATransferir.getSaldo() - valorATransfererir;

						String sql2 = "UPDATE sistema_bancario_atividade.clientes_pj SET saldo=?"
								+ "	WHERE num_conta=?;";

						PreparedStatement stmt2 = conn.prepareStatement(sql2);
						stmt2.setDouble(1, novoSaldo);
						stmt2.setInt(2, numContaATransferir);
						stmt2.executeUpdate();

					} else {
						// MUDANÇA DE LIMITE CASO O SALDO NÃO SEJA SUFICIENTE
						double sobra = valorATransfererir - clienteATransferir.getSaldo();

						clienteATransferir
								.setLimiteChequeEspecial(clienteATransferir.getLimiteChequeEspecial() - sobra);

						double novoLimite = clienteATransferir.getLimiteChequeEspecial();
						String sql2 = "UPDATE sistema_bancario_atividade.clientes_pj SET limite_cheque_especial=?"
								+ "	WHERE num_conta=?;";

						PreparedStatement stmt2 = conn.prepareStatement(sql2);
						stmt2.setDouble(1, novoLimite);
						stmt2.setInt(2, numContaATransferir);
						stmt2.executeUpdate();

						// ZERANDO O SALDO
						clienteATransferir.setSaldo(0.0);
						sql2 = "UPDATE sistema_bancario_atividade.clientes_pj SET saldo=?" + "	WHERE num_conta=?;";

						stmt2 = conn.prepareStatement(sql2);
						stmt2.setDouble(1, 0);
						stmt2.setInt(2, numContaATransferir);
						stmt2.executeUpdate();
					}
				}else {
					System.out.println("Você não possui saldo suficiente");
				}
				// DESCOBRIR SE O CLIENTE A RECEBER É PJ
				String sql4 = "SELECT saldo FROM sistema_bancario_atividade.clientes_pj WHERE num_conta = ?";
				stmt.setInt(1, numContaAReceber);
				ResultSet rs4 = stmt.executeQuery();
				
				// CASO O CLIENTE QUE IRA RECEBER SEJA PJ
				if (rs4.next()) {

					ClientePJ clienteAReceber = new ClientePJ(numContaAReceber, 0, sql, 0, 0, sql, sql, sql);

					// SE O SALDO+LIMITE > VALOR A TRANSFERIR

					// MUDANÇA DE SALDO DA CONTA QUE IRÁ RECEBER
					clienteAReceber.setSaldo(clienteAReceber.getSaldo() + valorATransfererir);

					double novoSaldo = clienteAReceber.getSaldo() + valorATransfererir;

					String sql2 = "UPDATE sistema_bancario_atividade.clientes_pj SET saldo=?" + "	WHERE num_conta=?;";
					PreparedStatement stmt2 = conn.prepareStatement(sql2);
					stmt2.setDouble(1, novoSaldo);
					stmt2.setInt(2, numContaAReceber);
					stmt2.executeUpdate();

				} 

				// DESCOBRIR SE A CONTA A RECEBER É PF

				String sql3 = "SELECT saldo FROM sistema_bancario_atividade.clientes_pf WHERE num_conta = ?";
				stmt.setInt(1, numContaAReceber);
				ResultSet rs2 = stmt.executeQuery();
				// CASO A CONTA A RECEBER SEJA PF
				if (rs2.next()) {
					ClientePF clienteAReceber = new ClientePF(numContaAReceber, 0, sql, 0, 0, sql, 0, sql);

					clienteAReceber.setSaldo(clienteAReceber.getSaldo() + valorATransfererir);
					double novoSaldo = clienteAReceber.getSaldo() + valorATransfererir;

					String sql2 = "UPDATE sistema_bancario_atividade.clientes_pf SET saldo=?" + "	WHERE num_conta=?;";

					PreparedStatement stmt2 = conn.prepareStatement(sql2);
					stmt2.setDouble(1, novoSaldo);
					stmt2.setInt(2, numContaAReceber);
					stmt2.executeUpdate();
					stmt.close();

				}

			}
			return true;
		} catch (SQLException e) {
			System.err.println("Erro ao realizar transferência.");
			return false;
		}

		
	}

	public boolean realizaTransferenciaClientePF(int numContaATransferir, int numContaAReceber,
			double valorATransfererir) {

		String sql = "SELECT saldo, limite_cheque_especial FROM sistema_bancario_atividade.clientes_pf WHERE num_conta = ?";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, numContaATransferir);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				ClientePF clienteATransferir = new ClientePF(rs.getInt("num_conta"), rs.getInt("agencia"), sql,
						rs.getDouble("saldo"), rs.getDouble("saldo"), rs.getString("nome"), rs.getInt("idade"),
						rs.getString("cpf"));

				sql = "SELECT saldo FROM sistema_bancario_atividade.clientes_pj WHERE num_conta = ?";
				stmt.setInt(1, numContaAReceber);
				rs = stmt.executeQuery();

				if (rs.next()) {
					ClientePJ ClienteAReceber = new ClientePJ(numContaAReceber, 0, sql, 0, 0, sql, sql, sql);

					if (clienteATransferir.getSaldo()
							+ clienteATransferir.getLimiteChequeEspecial() >= valorATransfererir) {
						if (clienteATransferir.getSaldo() >= valorATransfererir) {
							clienteATransferir.setSaldo(clienteATransferir.getSaldo() - valorATransfererir);

						} else {
							double sobra = valorATransfererir - clienteATransferir.getSaldo();
							clienteATransferir
									.setLimiteChequeEspecial(clienteATransferir.getLimiteChequeEspecial() - sobra);
							clienteATransferir.setSaldo(0.0);
						}
						ClienteAReceber.setSaldo(ClienteAReceber.getSaldo() + valorATransfererir);
					} else {
						System.out.println("Você não possui saldo suficiente");
					}

				} else {
					sql = "SELECT saldo FROM sistema_bancario_atividade.clientes_pf WHERE num_conta = ?";
					stmt.setInt(1, numContaAReceber);
					rs = stmt.executeQuery();

					if (rs.next()) {
						ClientePF clienteAReceber = new ClientePF(rs.getInt("num_conta"), rs.getInt("agencia"), sql,
								rs.getDouble("saldo"), rs.getDouble("saldo"), rs.getString("nome"), rs.getInt("idade"),
								rs.getString("cpf"));

						if (clienteATransferir.getSaldo()
								+ clienteATransferir.getLimiteChequeEspecial() >= valorATransfererir) {
							if (clienteATransferir.getSaldo() >= valorATransfererir) {
								clienteATransferir.setSaldo(clienteATransferir.getSaldo() - valorATransfererir);

							} else {
								double sobra = valorATransfererir - clienteATransferir.getSaldo();
								clienteATransferir
										.setLimiteChequeEspecial(clienteATransferir.getLimiteChequeEspecial() - sobra);
								clienteATransferir.setSaldo(0.0);
							}
							clienteAReceber.setSaldo(clienteAReceber.getSaldo() + valorATransfererir);
						} else {
							System.out.println("Você não possui saldo suficiente");
						}

					}

				}

			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar transferência.");
			return false;
		}

		return true;
	}
*/
	public List<ClientePF> listaClientesPF() {

		List<ClientePF> clientes = new ArrayList<ClientePF>();

		String sql = "SELECT * FROM sistema_bancario_atividade.clientes_pf";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				while (rs.next()) {
					ClientePF clienteRetornado = new ClientePF(rs.getInt("num_conta"), rs.getInt("agencia"), sql,
							rs.getDouble("saldo"), rs.getDouble("saldo"), rs.getString("nome"), rs.getInt("idade"),
							rs.getString("cpf"));

					clienteRetornado.setNome(rs.getString("nome"));
					clienteRetornado.setSaldo(rs.getDouble("saldo"));
					clienteRetornado.setLimiteChequeEspecial(rs.getDouble("limite_cheque_especial"));

					clientes.add(clienteRetornado);
				}
			} else {
				System.out.println("Não existem clientes PJ cadastrados");
			}

		} catch (SQLException e) {
			System.err.println("Erro ao listar clientes!");
		}

		return clientes;
	}

	public List<ClientePJ> listaClientesPJ() {

		List<ClientePJ> clientes = new ArrayList<ClientePJ>();

		String sql = "SELECT * FROM sistema_bancario_atividade.clientes_pj";

		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				while (rs.next()) {
					ClientePJ clienteRetornado = new ClientePJ(0, 0, sql, 0, 0, sql, sql, sql);

					clienteRetornado.setRazaoSocial(rs.getString("razao_social"));
					clienteRetornado.setSaldo(rs.getDouble("saldo"));
					clienteRetornado.setLimiteChequeEspecial(rs.getDouble("limite_cheque_especial"));

					clientes.add(clienteRetornado);
				}
			} else {
				System.out.println("Não existem clientes PJ cadastrados");
			}

		} catch (SQLException e) {
			System.err.println("Erro ao listar clientes!");
		}

		return clientes;
	}
}
