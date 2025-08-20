package br.edu.ifpi.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "corretor")
public class Corretor extends Pessoa {
    @Column(nullable = false)
    private String creci;

    public Corretor() {}
    public Corretor(String nome, String cpf, String creci) {
        super(nome, cpf);
        this.creci = creci;
    }
    public String getCreci() { return creci; }
    public void setCreci(String creci) { this.creci = creci; }
}
