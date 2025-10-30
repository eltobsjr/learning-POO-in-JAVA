package br.edu.ifpi;

import br.edu.ifpi.Model.*;
import br.edu.ifpi.DAO.*;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    // Cores ANSI para melhorar a interface
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE_BOLD = "\u001B[1;37m";
    
    // DAOs
    private static final ClienteDAO clienteDAO = new ClienteDAO();
    private static final CorretorDAO corretorDAO = new CorretorDAO();
    private static final EnderecoDAO enderecoDAO = new EnderecoDAO();
    private static final ImovelDAO imovelDAO = new ImovelDAO();
    private static final ContratoDAO contratoDAO = new ContratoDAO();
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            exibirMenuPrincipal();
            int opcao = lerInteiro("Escolha uma opção: ", 0, 4);
            
            switch (opcao) {
                case 1 -> menuGestaoClientes();
                case 2 -> menuGestaoCorretores();
                case 3 -> menuGestaoImoveis();
                case 4 -> menuGestaoContratos();
                case 0 -> {
                    exibirSucesso("Encerrando sistema. Até logo!");
                    scanner.close();
                    return;
                }
            }
        }
    }
    
    // ==================== MENUS PRINCIPAIS ====================
    
    private static void exibirMenuPrincipal() {
        limparTela();
        System.out.println(CYAN + "╔════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║" + WHITE_BOLD + "     SISTEMA DE GESTÃO IMOBILIÁRIA         " + CYAN + "║" + RESET);
        System.out.println(CYAN + "╚════════════════════════════════════════════╝" + RESET);
        System.out.println();
        System.out.println(BLUE + "  [1]" + RESET + " Gestão de Clientes");
        System.out.println(BLUE + "  [2]" + RESET + " Gestão de Corretores");
        System.out.println(BLUE + "  [3]" + RESET + " Gestão de Imóveis");
        System.out.println(BLUE + "  [4]" + RESET + " Gestão de Contratos");
        System.out.println(RED + "  [0]" + RESET + " Sair do Sistema");
        System.out.println(CYAN + "────────────────────────────────────────────" + RESET);
    }
    
    private static void menuGestaoClientes() {
        while (true) {
            limparTela();
            System.out.println(PURPLE + "╔════════════════════════════════════════════╗" + RESET);
            System.out.println(PURPLE + "║" + WHITE_BOLD + "         GESTÃO DE CLIENTES                " + PURPLE + "║" + RESET);
            System.out.println(PURPLE + "╚════════════════════════════════════════════╝" + RESET);
            System.out.println();
            System.out.println(BLUE + "  [1]" + RESET + " Cadastrar Cliente");
            System.out.println(BLUE + "  [2]" + RESET + " Listar Todos os Clientes");
            System.out.println(BLUE + "  [3]" + RESET + " Buscar Cliente por CPF");
            System.out.println(BLUE + "  [4]" + RESET + " Atualizar Cliente");
            System.out.println(BLUE + "  [5]" + RESET + " Remover Cliente");
            System.out.println(RED + "  [0]" + RESET + " Voltar ao Menu Principal");
            System.out.println(PURPLE + "────────────────────────────────────────────" + RESET);
            
            int opcao = lerInteiro("Escolha uma opção: ", 0, 5);
            
            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> buscarClientePorCpf();
                case 4 -> atualizarCliente();
                case 5 -> removerCliente();
                case 0 -> { return; }
            }
        }
    }
    
    private static void menuGestaoCorretores() {
        while (true) {
            limparTela();
            System.out.println(PURPLE + "╔════════════════════════════════════════════╗" + RESET);
            System.out.println(PURPLE + "║" + WHITE_BOLD + "        GESTÃO DE CORRETORES               " + PURPLE + "║" + RESET);
            System.out.println(PURPLE + "╚════════════════════════════════════════════╝" + RESET);
            System.out.println();
            System.out.println(BLUE + "  [1]" + RESET + " Cadastrar Corretor");
            System.out.println(BLUE + "  [2]" + RESET + " Listar Todos os Corretores");
            System.out.println(BLUE + "  [3]" + RESET + " Buscar Corretor por CPF");
            System.out.println(BLUE + "  [4]" + RESET + " Atualizar Corretor");
            System.out.println(BLUE + "  [5]" + RESET + " Remover Corretor");
            System.out.println(RED + "  [0]" + RESET + " Voltar ao Menu Principal");
            System.out.println(PURPLE + "────────────────────────────────────────────" + RESET);
            
            int opcao = lerInteiro("Escolha uma opção: ", 0, 5);
            
            switch (opcao) {
                case 1 -> cadastrarCorretor();
                case 2 -> listarCorretores();
                case 3 -> buscarCorretorPorCpf();
                case 4 -> atualizarCorretor();
                case 5 -> removerCorretor();
                case 0 -> { return; }
            }
        }
    }
    
    private static void menuGestaoImoveis() {
        while (true) {
            limparTela();
            System.out.println(PURPLE + "╔════════════════════════════════════════════╗" + RESET);
            System.out.println(PURPLE + "║" + WHITE_BOLD + "         GESTÃO DE IMÓVEIS                 " + PURPLE + "║" + RESET);
            System.out.println(PURPLE + "╚════════════════════════════════════════════╝" + RESET);
            System.out.println();
            System.out.println(BLUE + "  [1]" + RESET + " Cadastrar Imóvel");
            System.out.println(BLUE + "  [2]" + RESET + " Listar Todos os Imóveis");
            System.out.println(BLUE + "  [3]" + RESET + " Listar Imóveis Disponíveis");
            System.out.println(BLUE + "  [4]" + RESET + " Buscar Imóvel por ID");
            System.out.println(BLUE + "  [5]" + RESET + " Buscar Imóveis por Cidade");
            System.out.println(BLUE + "  [6]" + RESET + " Atualizar Imóvel");
            System.out.println(BLUE + "  [7]" + RESET + " Remover Imóvel");
            System.out.println(RED + "  [0]" + RESET + " Voltar ao Menu Principal");
            System.out.println(PURPLE + "────────────────────────────────────────────" + RESET);
            
            int opcao = lerInteiro("Escolha uma opção: ", 0, 7);
            
            switch (opcao) {
                case 1 -> cadastrarImovel();
                case 2 -> listarTodosImoveis();
                case 3 -> listarImoveisDisponiveis();
                case 4 -> buscarImovelPorId();
                case 5 -> buscarImoveisPorCidade();
                case 6 -> atualizarImovel();
                case 7 -> removerImovel();
                case 0 -> { return; }
            }
        }
    }
    
    private static void menuGestaoContratos() {
        while (true) {
            limparTela();
            System.out.println(PURPLE + "╔════════════════════════════════════════════╗" + RESET);
            System.out.println(PURPLE + "║" + WHITE_BOLD + "        GESTÃO DE CONTRATOS                " + PURPLE + "║" + RESET);
            System.out.println(PURPLE + "╚════════════════════════════════════════════╝" + RESET);
            System.out.println();
            System.out.println(BLUE + "  [1]" + RESET + " Criar Novo Contrato");
            System.out.println(BLUE + "  [2]" + RESET + " Listar Todos os Contratos");
            System.out.println(BLUE + "  [3]" + RESET + " Buscar Contrato por ID");
            System.out.println(BLUE + "  [4]" + RESET + " Cancelar Contrato");
            System.out.println(RED + "  [0]" + RESET + " Voltar ao Menu Principal");
            System.out.println(PURPLE + "────────────────────────────────────────────" + RESET);
            
            int opcao = lerInteiro("Escolha uma opção: ", 0, 4);
            
            switch (opcao) {
                case 1 -> criarContrato();
                case 2 -> listarContratos();
                case 3 -> buscarContratoPorId();
                case 4 -> cancelarContrato();
                case 0 -> { return; }
            }
        }
    }
    
    // ==================== FUNCIONALIDADES - CLIENTES ====================
    
    private static void cadastrarCliente() {
        exibirTitulo("CADASTRAR NOVO CLIENTE");
        
        String nome = lerStringObrigatoria("Nome completo: ");
        String cpf = lerCpfValido("CPF (apenas números): ");
        
        // Verifica duplicidade
        if (clienteDAO.buscarPorCpf(cpf) != null) {
            exibirErro("CPF já cadastrado no sistema!");
            aguardarEnter();
            return;
        }
        
        String email = lerEmailValido("E-mail: ");
        
        try {
            Cliente cliente = new Cliente(nome, cpf, email);
            clienteDAO.salvar(cliente);
            exibirSucesso("Cliente cadastrado com sucesso!");
            System.out.println(CYAN + "Nome: " + RESET + nome);
            System.out.println(CYAN + "CPF: " + RESET + formatarCpf(cpf));
            System.out.println(CYAN + "E-mail: " + RESET + email);
        } catch (Exception e) {
            exibirErro("Erro ao cadastrar cliente: " + e.getMessage());
        }
        
        aguardarEnter();
    }
    
    private static void listarClientes() {
        exibirTitulo("LISTA DE CLIENTES");
        
        List<Cliente> clientes = clienteDAO.listarTodos();
        
        if (clientes.isEmpty()) {
            exibirAviso("Nenhum cliente cadastrado no sistema.");
        } else {
            System.out.println(CYAN + "Total de clientes: " + RESET + clientes.size());
            System.out.println();
            for (Cliente c : clientes) {
                System.out.println(BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
                System.out.println(CYAN + "ID: " + RESET + c.getId());
                System.out.println(CYAN + "Nome: " + RESET + c.getNome());
                System.out.println(CYAN + "CPF: " + RESET + formatarCpf(c.getCpf()));
                System.out.println(CYAN + "E-mail: " + RESET + c.getEmail());
            }
            System.out.println(BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        }
        
        aguardarEnter();
    }
    
    private static void buscarClientePorCpf() {
        exibirTitulo("BUSCAR CLIENTE POR CPF");
        
        String cpf = lerCpfValido("CPF (apenas números): ");
        Cliente cliente = clienteDAO.buscarPorCpf(cpf);
        
        if (cliente == null) {
            exibirErro("Cliente não encontrado com o CPF: " + formatarCpf(cpf));
        } else {
            System.out.println(BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
            System.out.println(CYAN + "ID: " + RESET + cliente.getId());
            System.out.println(CYAN + "Nome: " + RESET + cliente.getNome());
            System.out.println(CYAN + "CPF: " + RESET + formatarCpf(cliente.getCpf()));
            System.out.println(CYAN + "E-mail: " + RESET + cliente.getEmail());
            System.out.println(BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        }
        
        aguardarEnter();
    }
    
    private static void atualizarCliente() {
        exibirTitulo("ATUALIZAR DADOS DO CLIENTE");
        
        String cpf = lerCpfValido("CPF do cliente: ");
        Cliente cliente = clienteDAO.buscarPorCpf(cpf);
        
        if (cliente == null) {
            exibirErro("Cliente não encontrado!");
            aguardarEnter();
            return;
        }
        
        System.out.println(YELLOW + "\nDados atuais:" + RESET);
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("E-mail: " + cliente.getEmail());
        System.out.println();
        
        String novoNome = lerStringOpcional("Novo nome (Enter para manter): ");
        String novoEmail = lerStringOpcional("Novo e-mail (Enter para manter): ");
        
        if (!novoNome.isEmpty()) {
            cliente.setNome(novoNome);
        }
        if (!novoEmail.isEmpty()) {
            if (validarEmail(novoEmail)) {
                cliente.setEmail(novoEmail);
            } else {
                exibirErro("E-mail inválido! Mantendo e-mail anterior.");
            }
        }
        
        try {
            clienteDAO.atualizar(cliente);
            exibirSucesso("Cliente atualizado com sucesso!");
        } catch (Exception e) {
            exibirErro("Erro ao atualizar cliente: " + e.getMessage());
        }
        
        aguardarEnter();
    }
    
    private static void removerCliente() {
        exibirTitulo("REMOVER CLIENTE");
        
        String cpf = lerCpfValido("CPF do cliente: ");
        Cliente cliente = clienteDAO.buscarPorCpf(cpf);
        
        if (cliente == null) {
            exibirErro("Cliente não encontrado!");
            aguardarEnter();
            return;
        }
        
        System.out.println(YELLOW + "\nCliente a ser removido:" + RESET);
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + formatarCpf(cliente.getCpf()));
        System.out.println();
        
        if (confirmarAcao("Confirma a remoção deste cliente? (S/N): ")) {
            try {
                clienteDAO.remover(cliente.getId());
                exibirSucesso("Cliente removido com sucesso!");
            } catch (Exception e) {
                exibirErro("Erro ao remover cliente: " + e.getMessage());
            }
        } else {
            exibirAviso("Operação cancelada.");
        }
        
        aguardarEnter();
    }
    
    // ==================== FUNCIONALIDADES - CORRETORES ====================
    
    private static void cadastrarCorretor() {
        exibirTitulo("CADASTRAR NOVO CORRETOR");
        
        String nome = lerStringObrigatoria("Nome completo: ");
        String cpf = lerCpfValido("CPF (apenas números): ");
        
        // Verifica duplicidade
        if (corretorDAO.buscarPorCpf(cpf) != null) {
            exibirErro("CPF já cadastrado no sistema!");
            aguardarEnter();
            return;
        }
        
        String creci = lerCreciValido("CRECI: ");
        
        try {
            Corretor corretor = new Corretor(nome, cpf, creci);
            corretorDAO.salvar(corretor);
            exibirSucesso("Corretor cadastrado com sucesso!");
            System.out.println(CYAN + "Nome: " + RESET + nome);
            System.out.println(CYAN + "CPF: " + RESET + formatarCpf(cpf));
            System.out.println(CYAN + "CRECI: " + RESET + creci);
        } catch (Exception e) {
            exibirErro("Erro ao cadastrar corretor: " + e.getMessage());
        }
        
        aguardarEnter();
    }
    
    private static void listarCorretores() {
        exibirTitulo("LISTA DE CORRETORES");
        
        List<Corretor> corretores = corretorDAO.listarTodos();
        
        if (corretores.isEmpty()) {
            exibirAviso("Nenhum corretor cadastrado no sistema.");
        } else {
            System.out.println(CYAN + "Total de corretores: " + RESET + corretores.size());
            System.out.println();
            for (Corretor c : corretores) {
                System.out.println(BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
                System.out.println(CYAN + "ID: " + RESET + c.getId());
                System.out.println(CYAN + "Nome: " + RESET + c.getNome());
                System.out.println(CYAN + "CPF: " + RESET + formatarCpf(c.getCpf()));
                System.out.println(CYAN + "CRECI: " + RESET + c.getCreci());
            }
            System.out.println(BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        }
        
        aguardarEnter();
    }
    
    private static void buscarCorretorPorCpf() {
        exibirTitulo("BUSCAR CORRETOR POR CPF");
        
        String cpf = lerCpfValido("CPF (apenas números): ");
        Corretor corretor = corretorDAO.buscarPorCpf(cpf);
        
        if (corretor == null) {
            exibirErro("Corretor não encontrado com o CPF: " + formatarCpf(cpf));
        } else {
            System.out.println(BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
            System.out.println(CYAN + "ID: " + RESET + corretor.getId());
            System.out.println(CYAN + "Nome: " + RESET + corretor.getNome());
            System.out.println(CYAN + "CPF: " + RESET + formatarCpf(corretor.getCpf()));
            System.out.println(CYAN + "CRECI: " + RESET + corretor.getCreci());
            System.out.println(BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        }
        
        aguardarEnter();
    }
    
    private static void atualizarCorretor() {
        exibirTitulo("ATUALIZAR DADOS DO CORRETOR");
        
        String cpf = lerCpfValido("CPF do corretor: ");
        Corretor corretor = corretorDAO.buscarPorCpf(cpf);
        
        if (corretor == null) {
            exibirErro("Corretor não encontrado!");
            aguardarEnter();
            return;
        }
        
        System.out.println(YELLOW + "\nDados atuais:" + RESET);
        System.out.println("Nome: " + corretor.getNome());
        System.out.println("CRECI: " + corretor.getCreci());
        System.out.println();
        
        String novoNome = lerStringOpcional("Novo nome (Enter para manter): ");
        String novoCreci = lerStringOpcional("Novo CRECI (Enter para manter): ");
        
        if (!novoNome.isEmpty()) {
            corretor.setNome(novoNome);
        }
        if (!novoCreci.isEmpty()) {
            if (validarCreci(novoCreci)) {
                corretor.setCreci(novoCreci);
            } else {
                exibirErro("CRECI inválido! Mantendo CRECI anterior.");
            }
        }
        
        try {
            corretorDAO.atualizar(corretor);
            exibirSucesso("Corretor atualizado com sucesso!");
        } catch (Exception e) {
            exibirErro("Erro ao atualizar corretor: " + e.getMessage());
        }
        
        aguardarEnter();
    }
    
    private static void removerCorretor() {
        exibirTitulo("REMOVER CORRETOR");
        
        String cpf = lerCpfValido("CPF do corretor: ");
        Corretor corretor = corretorDAO.buscarPorCpf(cpf);
        
        if (corretor == null) {
            exibirErro("Corretor não encontrado!");
            aguardarEnter();
            return;
        }
        
        System.out.println(YELLOW + "\nCorretor a ser removido:" + RESET);
        System.out.println("Nome: " + corretor.getNome());
        System.out.println("CPF: " + formatarCpf(corretor.getCpf()));
        System.out.println("CRECI: " + corretor.getCreci());
        System.out.println();
        
        if (confirmarAcao("Confirma a remoção deste corretor? (S/N): ")) {
            try {
                corretorDAO.remover(corretor.getId());
                exibirSucesso("Corretor removido com sucesso!");
            } catch (Exception e) {
                exibirErro("Erro ao remover corretor: " + e.getMessage());
            }
        } else {
            exibirAviso("Operação cancelada.");
        }
        
        aguardarEnter();
    }
    
    // ==================== FUNCIONALIDADES - IMÓVEIS ====================
    
    private static void cadastrarImovel() {
        exibirTitulo("CADASTRAR NOVO IMÓVEL");
        
        System.out.println(CYAN + "Tipo de imóvel:" + RESET);
        System.out.println("  [1] Casa");
        System.out.println("  [2] Apartamento");
        int tipo = lerInteiro("Escolha o tipo: ", 1, 2);
        
        System.out.println(YELLOW + "\n--- Dados do Endereço ---" + RESET);
        String rua = lerStringObrigatoria("Rua: ");
        String numero = lerStringObrigatoria("Número: ");
        String bairro = lerStringObrigatoria("Bairro: ");
        String cidade = lerStringObrigatoria("Cidade: ");
        String cep = lerCepValido("CEP (apenas números): ");
        
        Endereco endereco = new Endereco(rua, numero, bairro, cidade, cep);
        
        try {
            enderecoDAO.salvar(endereco);
            
            System.out.println(YELLOW + "\n--- Dados do Imóvel ---" + RESET);
            double valor = lerDoublePositivo("Valor do imóvel (R$): ");
            
            Imovel imovel;
            if (tipo == 1) {
                int quartos = lerInteiro("Número de quartos: ", 1, 50);
                imovel = new Casa(endereco, valor, quartos);
            } else {
                int andar = lerInteiro("Andar: ", 0, 200);
                double valorCondominio = lerDoublePositivo("Valor do condomínio (R$): ");
                imovel = new Apartamento(endereco, valor, andar, valorCondominio);
            }
            
            imovelDAO.salvar(imovel);
            exibirSucesso("Imóvel cadastrado com sucesso!");
            System.out.println(CYAN + "ID: " + RESET + imovel.getId());
            System.out.println(CYAN + "Tipo: " + RESET + (tipo == 1 ? "Casa" : "Apartamento"));
            System.out.println(CYAN + "Endereço: " + RESET + endereco);
            System.out.println(CYAN + "Valor: " + RESET + formatarValor(valor));
            
        } catch (Exception e) {
            exibirErro("Erro ao cadastrar imóvel: " + e.getMessage());
        }
        
        aguardarEnter();
    }
    
    private static void listarTodosImoveis() {
        exibirTitulo("LISTA DE TODOS OS IMÓVEIS");
        
        List<Imovel> imoveis = imovelDAO.listarTodos();
        
        if (imoveis.isEmpty()) {
            exibirAviso("Nenhum imóvel cadastrado no sistema.");
        } else {
            System.out.println(CYAN + "Total de imóveis: " + RESET + imoveis.size());
            System.out.println();
            for (Imovel i : imoveis) {
                exibirDetalhesImovel(i);
            }
        }
        
        aguardarEnter();
    }
    
    private static void listarImoveisDisponiveis() {
        exibirTitulo("IMÓVEIS DISPONÍVEIS");
        
        List<Imovel> imoveis = imovelDAO.listarDisponiveis();
        
        if (imoveis.isEmpty()) {
            exibirAviso("Nenhum imóvel disponível no momento.");
        } else {
            System.out.println(CYAN + "Total de imóveis disponíveis: " + RESET + imoveis.size());
            System.out.println();
            for (Imovel i : imoveis) {
                exibirDetalhesImovel(i);
            }
        }
        
        aguardarEnter();
    }
    
    private static void buscarImovelPorId() {
        exibirTitulo("BUSCAR IMÓVEL POR ID");
        
        long id = lerLong("ID do imóvel: ");
        Imovel imovel = imovelDAO.buscarPorId(id);
        
        if (imovel == null) {
            exibirErro("Imóvel não encontrado com o ID: " + id);
        } else {
            exibirDetalhesImovel(imovel);
        }
        
        aguardarEnter();
    }
    
    private static void buscarImoveisPorCidade() {
        exibirTitulo("BUSCAR IMÓVEIS POR CIDADE");
        
        String cidade = lerStringObrigatoria("Nome da cidade: ");
        List<Imovel> imoveis = imovelDAO.buscarPorCidade(cidade);
        
        if (imoveis.isEmpty()) {
            exibirAviso("Nenhum imóvel encontrado na cidade: " + cidade);
        } else {
            System.out.println(CYAN + "Total de imóveis em " + cidade + ": " + RESET + imoveis.size());
            System.out.println();
            for (Imovel i : imoveis) {
                exibirDetalhesImovel(i);
            }
        }
        
        aguardarEnter();
    }
    
    private static void atualizarImovel() {
        exibirTitulo("ATUALIZAR DADOS DO IMÓVEL");
        
        long id = lerLong("ID do imóvel: ");
        Imovel imovel = imovelDAO.buscarPorId(id);
        
        if (imovel == null) {
            exibirErro("Imóvel não encontrado!");
            aguardarEnter();
            return;
        }
        
        System.out.println(YELLOW + "\nValor atual: " + RESET + formatarValor(imovel.getValor()));
        
        String valorStr = lerStringOpcional("Novo valor (Enter para manter): ");
        if (!valorStr.isEmpty()) {
            try {
                double novoValor = Double.parseDouble(valorStr.replace(",", "."));
                if (novoValor > 0) {
                    imovel.setValor(novoValor);
                } else {
                    exibirErro("Valor deve ser positivo! Mantendo valor anterior.");
                }
            } catch (NumberFormatException e) {
                exibirErro("Valor inválido! Mantendo valor anterior.");
            }
        }
        
        try {
            imovelDAO.atualizar(imovel);
            exibirSucesso("Imóvel atualizado com sucesso!");
        } catch (Exception e) {
            exibirErro("Erro ao atualizar imóvel: " + e.getMessage());
        }
        
        aguardarEnter();
    }
    
    private static void removerImovel() {
        exibirTitulo("REMOVER IMÓVEL");
        
        long id = lerLong("ID do imóvel: ");
        Imovel imovel = imovelDAO.buscarPorId(id);
        
        if (imovel == null) {
            exibirErro("Imóvel não encontrado!");
            aguardarEnter();
            return;
        }
        
        System.out.println(YELLOW + "\nImóvel a ser removido:" + RESET);
        exibirDetalhesImovel(imovel);
        
        if (!imovel.isDisponivel()) {
            exibirAviso("ATENÇÃO: Este imóvel possui contrato ativo!");
        }
        
        if (confirmarAcao("\nConfirma a remoção deste imóvel? (S/N): ")) {
            try {
                imovelDAO.remover(id);
                exibirSucesso("Imóvel removido com sucesso!");
            } catch (Exception e) {
                exibirErro("Erro ao remover imóvel: " + e.getMessage());
            }
        } else {
            exibirAviso("Operação cancelada.");
        }
        
        aguardarEnter();
    }
    
    // ==================== FUNCIONALIDADES - CONTRATOS ====================
    
    private static void criarContrato() {
        exibirTitulo("CRIAR NOVO CONTRATO");
        
        // Buscar imóvel
        long idImovel = lerLong("ID do imóvel: ");
        Imovel imovel = imovelDAO.buscarPorId(idImovel);
        
        if (imovel == null) {
            exibirErro("Imóvel não encontrado com o ID: " + idImovel);
            aguardarEnter();
            return;
        }
        
        if (!imovel.isDisponivel()) {
            exibirErro("Imóvel não está disponível! Já possui contrato ativo.");
            aguardarEnter();
            return;
        }
        
        // Buscar cliente
        String cpfCliente = lerCpfValido("CPF do cliente: ");
        Cliente cliente = clienteDAO.buscarPorCpf(cpfCliente);
        
        if (cliente == null) {
            exibirErro("Cliente não encontrado com o CPF: " + formatarCpf(cpfCliente));
            aguardarEnter();
            return;
        }
        
        // Buscar corretor
        String cpfCorretor = lerCpfValido("CPF do corretor: ");
        Corretor corretor = corretorDAO.buscarPorCpf(cpfCorretor);
        
        if (corretor == null) {
            exibirErro("Corretor não encontrado com o CPF: " + formatarCpf(cpfCorretor));
            aguardarEnter();
            return;
        }
        
        // Tipo de contrato
        System.out.println(CYAN + "\nTipo de contrato:" + RESET);
        System.out.println("  [1] Venda");
        System.out.println("  [2] Aluguel");
        int tipoContrato = lerInteiro("Escolha o tipo: ", 1, 2);
        
        TipoContrato tipo = (tipoContrato == 1) ? TipoContrato.VENDA : TipoContrato.ALUGUEL;
        
        // Valor final
        System.out.println(YELLOW + "\nValor base do imóvel: " + RESET + formatarValor(imovel.getValor()));
        double valorFinal = lerDoublePositivo("Valor final do contrato (R$): ");
        
        // Confirmar criação
        System.out.println(YELLOW + "\n--- Resumo do Contrato ---" + RESET);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Corretor: " + corretor.getNome());
        System.out.println("Imóvel ID: " + imovel.getId());
        System.out.println("Tipo: " + tipo);
        System.out.println("Valor: " + formatarValor(valorFinal));
        System.out.println();
        
        if (confirmarAcao("Confirma a criação do contrato? (S/N): ")) {
            try {
                Contrato contrato = new Contrato(imovel, cliente, corretor, valorFinal, tipo);
                contratoDAO.salvar(contrato);
                exibirSucesso("Contrato criado com sucesso!");
                System.out.println(CYAN + "ID do Contrato: " + RESET + contrato.getId());
                System.out.println(CYAN + "Data: " + RESET + contrato.getData());
            } catch (Exception e) {
                exibirErro("Erro ao criar contrato: " + e.getMessage());
            }
        } else {
            exibirAviso("Operação cancelada.");
        }
        
        aguardarEnter();
    }
    
    private static void listarContratos() {
        exibirTitulo("LISTA DE CONTRATOS");
        
        List<Contrato> contratos = contratoDAO.listarTodos();
        
        if (contratos.isEmpty()) {
            exibirAviso("Nenhum contrato registrado no sistema.");
        } else {
            System.out.println(CYAN + "Total de contratos: " + RESET + contratos.size());
            System.out.println();
            for (Contrato c : contratos) {
                System.out.println(BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
                System.out.println(CYAN + "ID do Contrato: " + RESET + c.getId());
                System.out.println(CYAN + "Tipo: " + RESET + c.getTipo());
                System.out.println(CYAN + "Data: " + RESET + c.getData());
                System.out.println(CYAN + "Cliente: " + RESET + c.getCliente().getNome());
                System.out.println(CYAN + "Corretor: " + RESET + c.getCorretor().getNome());
                System.out.println(CYAN + "Imóvel ID: " + RESET + c.getImovel().getId());
                System.out.println(CYAN + "Valor: " + RESET + formatarValor(c.getValorFinal()));
            }
            System.out.println(BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        }
        
        aguardarEnter();
    }
    
    private static void buscarContratoPorId() {
        exibirTitulo("BUSCAR CONTRATO POR ID");
        
        long id = lerLong("ID do contrato: ");
        Contrato contrato = contratoDAO.buscarPorId(id);
        
        if (contrato == null) {
            exibirErro("Contrato não encontrado com o ID: " + id);
        } else {
            System.out.println(BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
            System.out.println(CYAN + "ID do Contrato: " + RESET + contrato.getId());
            System.out.println(CYAN + "Tipo: " + RESET + contrato.getTipo());
            System.out.println(CYAN + "Data: " + RESET + contrato.getData());
            System.out.println(CYAN + "Cliente: " + RESET + contrato.getCliente().getNome() + 
                             " (CPF: " + formatarCpf(contrato.getCliente().getCpf()) + ")");
            System.out.println(CYAN + "Corretor: " + RESET + contrato.getCorretor().getNome() + 
                             " (CRECI: " + contrato.getCorretor().getCreci() + ")");
            System.out.println(CYAN + "Valor: " + RESET + formatarValor(contrato.getValorFinal()));
            System.out.println(YELLOW + "\nDetalhes do Imóvel:" + RESET);
            exibirDetalhesImovel(contrato.getImovel());
        }
        
        aguardarEnter();
    }
    
    private static void cancelarContrato() {
        exibirTitulo("CANCELAR CONTRATO");
        
        long id = lerLong("ID do contrato: ");
        Contrato contrato = contratoDAO.buscarPorId(id);
        
        if (contrato == null) {
            exibirErro("Contrato não encontrado!");
            aguardarEnter();
            return;
        }
        
        System.out.println(YELLOW + "\nContrato a ser cancelado:" + RESET);
        System.out.println("ID: " + contrato.getId());
        System.out.println("Cliente: " + contrato.getCliente().getNome());
        System.out.println("Imóvel ID: " + contrato.getImovel().getId());
        System.out.println("Tipo: " + contrato.getTipo());
        System.out.println();
        
        if (confirmarAcao("Confirma o cancelamento deste contrato? (S/N): ")) {
            try {
                // Liberar imóvel
                Imovel imovel = contrato.getImovel();
                imovel.setDisponivel(true);
                imovelDAO.atualizar(imovel);
                
                // Remover contrato
                contratoDAO.remover(id);
                
                exibirSucesso("Contrato cancelado com sucesso!");
                exibirAviso("O imóvel ID " + imovel.getId() + " voltou a ficar disponível.");
            } catch (Exception e) {
                exibirErro("Erro ao cancelar contrato: " + e.getMessage());
            }
        } else {
            exibirAviso("Operação cancelada.");
        }
        
        aguardarEnter();
    }
    
    // ==================== UTILITÁRIOS DE LEITURA ====================
    
    private static int lerInteiro(String mensagem, int min, int max) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer
                
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    exibirErro("Valor deve estar entre " + min + " e " + max + "!");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Limpar buffer
                exibirErro("Entrada inválida! Digite um número inteiro.");
            }
        }
    }
    
    private static long lerLong(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                long valor = scanner.nextLong();
                scanner.nextLine(); // Limpar buffer
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Limpar buffer
                exibirErro("Entrada inválida! Digite um número.");
            }
        }
    }
    
    private static double lerDoublePositivo(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String input = scanner.nextLine().replace(",", ".");
                double valor = Double.parseDouble(input);
                
                if (valor > 0) {
                    return valor;
                } else {
                    exibirErro("Valor deve ser maior que zero!");
                }
            } catch (NumberFormatException e) {
                exibirErro("Entrada inválida! Digite um valor numérico.");
            }
        }
    }
    
    private static String lerStringObrigatoria(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = scanner.nextLine().trim();
            
            if (!valor.isEmpty()) {
                return valor;
            } else {
                exibirErro("Este campo é obrigatório!");
            }
        }
    }
    
    private static String lerStringOpcional(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }
    
    private static String lerCpfValido(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String cpf = scanner.nextLine().replaceAll("[^0-9]", "");
            
            if (validarCPF(cpf)) {
                return cpf;
            } else {
                exibirErro("CPF inválido! Digite 11 dígitos numéricos.");
            }
        }
    }
    
    private static String lerEmailValido(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String email = scanner.nextLine().trim();
            
            if (validarEmail(email)) {
                return email;
            } else {
                exibirErro("E-mail inválido! Use o formato: exemplo@dominio.com");
            }
        }
    }
    
    private static String lerCepValido(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String cep = scanner.nextLine().replaceAll("[^0-9]", "");
            
            if (validarCEP(cep)) {
                return cep;
            } else {
                exibirErro("CEP inválido! Digite 8 dígitos numéricos.");
            }
        }
    }
    
    private static String lerCreciValido(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String creci = scanner.nextLine().trim();
            
            if (validarCreci(creci)) {
                return creci;
            } else {
                exibirErro("CRECI inválido! Deve conter pelo menos 3 caracteres.");
            }
        }
    }
    
    private static boolean confirmarAcao(String mensagem) {
        while (true) {
            System.out.print(YELLOW + mensagem + RESET);
            String resposta = scanner.nextLine().trim().toUpperCase();
            
            if (resposta.equals("S") || resposta.equals("SIM")) {
                return true;
            } else if (resposta.equals("N") || resposta.equals("NAO") || resposta.equals("NÃO")) {
                return false;
            } else {
                exibirErro("Responda com S (sim) ou N (não).");
            }
        }
    }
    
    // ==================== VALIDAÇÕES ====================
    
    private static boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }
        
        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        try {
            // Valida primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int digito1 = 11 - (soma % 11);
            if (digito1 > 9) digito1 = 0;
            
            if (digito1 != Character.getNumericValue(cpf.charAt(9))) {
                return false;
            }
            
            // Valida segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int digito2 = 11 - (soma % 11);
            if (digito2 > 9) digito2 = 0;
            
            return digito2 == Character.getNumericValue(cpf.charAt(10));
            
        } catch (Exception e) {
            return false;
        }
    }
    
    private static boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(regex, email);
    }
    
    private static boolean validarCEP(String cep) {
        return cep != null && cep.matches("\\d{8}");
    }
    
    private static boolean validarCreci(String creci) {
        return creci != null && !creci.isEmpty() && creci.length() >= 3;
    }
    
    // ==================== FORMATAÇÃO ====================
    
    private static String formatarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf;
        }
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + 
               cpf.substring(6, 9) + "-" + cpf.substring(9);
    }
    
    private static String formatarValor(double valor) {
        return String.format("R$ %,.2f", valor);
    }
    
    // ==================== EXIBIÇÃO ====================
    
    private static void exibirTitulo(String titulo) {
        limparTela();
        System.out.println(GREEN + "╔════════════════════════════════════════════╗" + RESET);
        System.out.println(GREEN + "║ " + WHITE_BOLD + String.format("%-42s", titulo) + GREEN + " ║" + RESET);
        System.out.println(GREEN + "╚════════════════════════════════════════════╝" + RESET);
        System.out.println();
    }
    
    private static void exibirSucesso(String mensagem) {
        System.out.println(GREEN + "✓ " + mensagem + RESET);
    }
    
    private static void exibirErro(String mensagem) {
        System.out.println(RED + "✗ " + mensagem + RESET);
    }
    
    private static void exibirAviso(String mensagem) {
        System.out.println(YELLOW + "⚠ " + mensagem + RESET);
    }
    
    private static void exibirDetalhesImovel(Imovel imovel) {
        System.out.println(BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        
        if (imovel instanceof Casa casa) {
            System.out.println(CYAN + "Tipo: " + RESET + "Casa");
            System.out.println(CYAN + "ID: " + RESET + casa.getId());
            System.out.println(CYAN + "Quartos: " + RESET + casa.getNumeroQuartos());
        } else if (imovel instanceof Apartamento apt) {
            System.out.println(CYAN + "Tipo: " + RESET + "Apartamento");
            System.out.println(CYAN + "ID: " + RESET + apt.getId());
            System.out.println(CYAN + "Andar: " + RESET + apt.getAndar());
            System.out.println(CYAN + "Condomínio: " + RESET + formatarValor(apt.getValorCondominio()));
        }
        
        System.out.println(CYAN + "Endereço: " + RESET + imovel.getEndereco());
        System.out.println(CYAN + "Valor: " + RESET + formatarValor(imovel.getValor()));
        
        String disponibilidade = imovel.isDisponivel() ? 
            GREEN + "Disponível" + RESET : RED + "Indisponível" + RESET;
        System.out.println(CYAN + "Status: " + RESET + disponibilidade);
    }
    
    private static void limparTela() {
        // Limpa a tela (funciona na maioria dos terminais)
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    private static void aguardarEnter() {
        System.out.println();
        System.out.print(CYAN + "Pressione ENTER para continuar..." + RESET);
        scanner.nextLine();
    }
}
