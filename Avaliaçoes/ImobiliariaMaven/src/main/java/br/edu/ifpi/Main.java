package br.edu.ifpi;

import br.edu.ifpi.Model.*;
import br.edu.ifpi.DAO.*;
import java.util.*;

public class Main {
    public static ClienteDAO clienteDAO = new ClienteDAO();
    public static CorretorDAO corretorDAO = new CorretorDAO();
    public static EnderecoDAO enderecoDAO = new EnderecoDAO();
    public static ImovelDAO imovelDAO = new ImovelDAO();
    public static ContratoDAO contratoDAO = new ContratoDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Menu Imobiliária ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Cadastrar Corretor");
            System.out.println("4. Listar Corretores");
            System.out.println("5. Cadastrar Imóvel");
            System.out.println("6. Listar Imóveis Disponíveis");
            System.out.println("7. Criar Contrato");
            System.out.println("8. Listar Contratos");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    Cliente cliente = new Cliente(nome, cpf, email);
                    clienteDAO.salvar(cliente);
                    System.out.println("Cliente cadastrado!");
                    break;
                case 2:
                    List<Cliente> clientes = clienteDAO.listarTodos();
                    System.out.println("--- Clientes ---");
                    for (Cliente c : clientes) {
                        System.out.println("Nome: " + c.getNome() + ", CPF: " + c.getCpf() + ", Email: " + c.getEmail());
                    }
                    break;
                case 3:
                    System.out.print("Nome: ");
                    nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    cpf = scanner.nextLine();
                    System.out.print("CRECI: ");
                    String creci = scanner.nextLine();
                    Corretor corretor = new Corretor(nome, cpf, creci);
                    corretorDAO.salvar(corretor);
                    System.out.println("Corretor cadastrado!");
                    break;
                case 4:
                    List<Corretor> corretores = corretorDAO.listarTodos();
                    System.out.println("--- Corretores ---");
                    for (Corretor cor : corretores) {
                        System.out.println("Nome: " + cor.getNome() + ", CPF: " + cor.getCpf() + ", CRECI: " + cor.getCreci());
                    }
                    break;
                case 5:
                    System.out.print("Tipo (1-Casa, 2-Apartamento): ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Rua: ");
                    String rua = scanner.nextLine();
                    System.out.print("Número: ");
                    String numero = scanner.nextLine();
                    System.out.print("Bairro: ");
                    String bairro = scanner.nextLine();
                    System.out.print("Cidade: ");
                    String cidade = scanner.nextLine();
                    System.out.print("CEP: ");
                    String cep = scanner.nextLine();
                    Endereco end = new Endereco(rua, numero, bairro, cidade, cep);
                    enderecoDAO.salvar(end);
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    if (tipo == 1) {
                        System.out.print("Quartos: ");
                        int quartos = scanner.nextInt();
                        Casa casa = new Casa(end, valor, quartos);
                        imovelDAO.salvar(casa);
                    } else {
                        System.out.print("Andar: ");
                        int andar = scanner.nextInt();
                        System.out.print("Valor condomínio: ");
                        double cond = scanner.nextDouble();
                        Apartamento apt = new Apartamento(end, valor, andar, cond);
                        imovelDAO.salvar(apt);
                    }
                    System.out.println("Imóvel cadastrado!");
                    break;
                case 6:
                    List<Imovel> imoveis = imovelDAO.listarDisponiveis();
                    for (Imovel i : imoveis) {
                        i.exibirDetalhes();
                    }
                    break;
                case 7:
                    System.out.print("ID do imóvel: ");
                    Long idImovel = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("CPF do cliente: ");
                    String cpfCliente = scanner.nextLine();
                    System.out.print("CPF do corretor: ");
                    String cpfCorretor = scanner.nextLine();
                    System.out.print("Tipo contrato (1-Venda, 2-Aluguel): ");
                    int tipoC = scanner.nextInt();
                    scanner.nextLine();
                    Imovel imv = imovelDAO.buscarPorId(idImovel);
                    Cliente cli = clienteDAO.buscarPorCpf(cpfCliente);
                    Corretor cor = corretorDAO.buscarPorCpf(cpfCorretor);
                    TipoContrato tipoContrato = (tipoC == 1) ? TipoContrato.VENDA : TipoContrato.ALUGUEL;
                    Contrato contrato = new Contrato(imv, cli, cor, imv.getValor(), tipoContrato);
                    contratoDAO.salvar(contrato);
                    System.out.println("Contrato criado com sucesso!");
                    contrato.imprimir();
                    break;
                case 8:
                    List<Contrato> contratos = contratoDAO.listarTodos();
                    for (Contrato c : contratos) {
                        c.imprimir();
                        System.out.println("---");
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
