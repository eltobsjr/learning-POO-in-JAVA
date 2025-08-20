package br.edu.ifpi.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "apartamento")
public class Apartamento extends Imovel {
    private int andar;
    private double valorCondominio;

    public Apartamento() {}
    public Apartamento(Endereco endereco, double valor, int andar, double valorCondominio) {
        super(endereco, valor);
        this.andar = andar;
        this.valorCondominio = valorCondominio;
    }
    public int getAndar() { return andar; }
    public void setAndar(int andar) { this.andar = andar; }
    public double getValorCondominio() { return valorCondominio; }
    public void setValorCondominio(double valorCondominio) { this.valorCondominio = valorCondominio; }
    @Override
    public void exibirDetalhes() {
        System.out.println("Apartamento ID: " + getId());
        System.out.println("Endereco: " + getEndereco());
        System.out.println("Valor: R$" + getValor());
        System.out.println("Andar: " + andar);
        System.out.println("Condomínio: R$" + valorCondominio);
        System.out.println("Disponível: " + isDisponivel());
    }
}
