package br.edu.ifpi.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contrato")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Imovel imovel;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Corretor corretor;
    private double valorFinal;
    @Enumerated(EnumType.STRING)
    private TipoContrato tipo;
    private LocalDate data;

    public Contrato() {}
    public Contrato(Imovel imovel, Cliente cliente, Corretor corretor, double valorFinal, TipoContrato tipo) {
        this.imovel = imovel;
        this.cliente = cliente;
        this.corretor = corretor;
        this.valorFinal = valorFinal;
        this.tipo = tipo;
        this.data = LocalDate.now();
        imovel.setDisponivel(false);
    }
    public void imprimir() {
        System.out.println("Contrato ID: " + id);
        System.out.println("Tipo: " + tipo);
        System.out.println("Data: " + data);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Corretor: " + corretor.getNome());
        System.out.println("Imóvel: ");
        imovel.exibirDetalhes();
        System.out.println("Valor Final: R$" + valorFinal);
    }
    // Getters e setters
    public Long getId() { return id; }
    public Imovel getImovel() { return imovel; }
    public Cliente getCliente() { return cliente; }
    public Corretor getCorretor() { return corretor; }
    public double getValorFinal() { return valorFinal; }
    public TipoContrato getTipo() { return tipo; }
    public LocalDate getData() { return data; }
}
