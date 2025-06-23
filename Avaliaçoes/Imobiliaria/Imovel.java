public abstract class Imovel {
    protected int id;
    protected double valor;
    protected boolean disponivel = true;
    protected Endereco endereco;

    public Imovel(int id, Endereco endereco, double valor) {
        this.id = id;
        this.endereco = endereco;
        this.valor = valor;
    }

    public abstract void exibirDetalhes();

    public int getId() {
        return id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public double getValor() {
        return valor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
