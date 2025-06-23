// Arquivo: Apartamento.java
public class Apartamento extends Imovel {
    private int andar;
    private double valorCondominio;

    public Apartamento(int id, Endereco endereco, double valor, int andar, double valorCondominio) {
        super(id, endereco, valor);
        this.andar = andar;
        this.valorCondominio = valorCondominio;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Apartamento ID: " + id);
        System.out.println("Endereco: " + endereco);
        System.out.println("Valor: R$" + valor);
        System.out.println("Andar: " + andar);
        System.out.println("Condomínio: R$" + valorCondominio);
        System.out.println("Disponível: " + disponivel);
    }

    public Contrato vender(Cliente cliente, Corretor corretor) {
        return new Contrato(this, cliente, corretor, valor, TipoContrato.VENDA);
    }

    public Contrato alugar(Cliente cliente, Corretor corretor) {
        return new Contrato(this, cliente, corretor, valor, TipoContrato.ALUGUEL);
    }

    public double calcularComissao() {
        return valor * 0.04;
    }
}