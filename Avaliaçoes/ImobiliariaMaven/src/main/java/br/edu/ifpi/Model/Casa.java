package br.edu.ifpi.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "casa")
public class Casa extends Imovel {
    private int numeroQuartos;

    public Casa() {}
    public Casa(Endereco endereco, double valor, int numeroQuartos) {
        super(endereco, valor);
        this.numeroQuartos = numeroQuartos;
    }
    public int getNumeroQuartos() { return numeroQuartos; }
    public void setNumeroQuartos(int numeroQuartos) { this.numeroQuartos = numeroQuartos; }
    @Override
    public void exibirDetalhes() {
        System.out.println("Casa ID: " + getId());
        System.out.println("Endereco: " + getEndereco());
        System.out.println("Valor: R$" + getValor());
        System.out.println("Quartos: " + numeroQuartos);
        System.out.println("Disponível: " + isDisponivel());
    }
}
