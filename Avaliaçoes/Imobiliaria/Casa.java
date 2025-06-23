public class Casa extends Imovel {
    private int numeroQuartos;

    public Casa(int id, Endereco endereco, double valor, int numeroQuartos) {
        super(id, endereco, valor);
        this.numeroQuartos = numeroQuartos;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Casa ID: " + id);
        System.out.println("Endereco: " + endereco);
        System.out.println("Valor: R$" + valor);
        System.out.println("Quartos: " + numeroQuartos);
        System.out.println("Disponível: " + disponivel);
    }

    public Contrato vender(Cliente cliente, Corretor corretor) {
        return new Contrato(this, cliente, corretor, valor, TipoContrato.VENDA);
    }

    public Contrato alugar(Cliente cliente, Corretor corretor) {
        return new Contrato(this, cliente, corretor, valor, TipoContrato.ALUGUEL);
    }

    public double calcularComissao() {
        return valor * 0.05;
    }
}