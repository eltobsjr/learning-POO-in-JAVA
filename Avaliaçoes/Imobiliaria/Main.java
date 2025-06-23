import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Imovel> imoveis = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Corretor> corretores = new ArrayList<>();
        List<Contrato> contratos = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Menu Imobiliária ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Corretor");
            System.out.println("3. Cadastrar Imóvel");
            System.out.println("4. Listar Imóveis Disponíveis");
            System.out.println("5. Criar Contrato");
            System.out.println("6. Listar Contratos");
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
                    clientes.add(new Cliente(nome, cpf, email));
                    break;
                case 2:
                    System.out.print("Nome: ");
                    nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    cpf = scanner.nextLine();
                    System.out.print("CRECI: ");
                    String creci = scanner.nextLine();
                    corretores.add(new Corretor(nome, cpf, creci));
                    break;
                case 3:
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
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    if (tipo == 1) {
                        System.out.print("Quartos: ");
                        int quartos = scanner.nextInt();
                        imoveis.add(new Casa(imoveis.size() + 1, end, valor, quartos));
                    } else {
                        System.out.print("Andar: ");
                        int andar = scanner.nextInt();
                        System.out.print("Valor condomínio: ");
                        double cond = scanner.nextDouble();
                        imoveis.add(new Apartamento(imoveis.size() + 1, end, valor, andar, cond));
                    }
                    break;
                case 4:
                    for (Imovel i : imoveis) {
                        if (i.isDisponivel()) i.exibirDetalhes();
                    }
                    break;
                case 5:
                    System.out.print("ID do imóvel: ");
                    int idImovel = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Índice do cliente (0-n): ");
                    int idCliente = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Índice do corretor (0-n): ");
                    int idCorretor = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Tipo contrato (1-Venda, 2-Aluguel): ");
                    int tipoC = scanner.nextInt();
                    scanner.nextLine();

                    Imovel imv = imoveis.get(idImovel - 1);
                    Cliente cli = clientes.get(idCliente);
                    Corretor cor = corretores.get(idCorretor);
                    Contrato contrato;
                    if (tipoC == 1)
                        contrato = (imv instanceof Casa) ? ((Casa) imv).vender(cli, cor) : ((Apartamento) imv).vender(cli, cor);
                    else
                        contrato = (imv instanceof Casa) ? ((Casa) imv).alugar(cli, cor) : ((Apartamento) imv).alugar(cli, cor);
                    contratos.add(contrato);
                    System.out.println("Contrato criado com sucesso!");
                    contrato.imprimir();
                    break;
                case 6:
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