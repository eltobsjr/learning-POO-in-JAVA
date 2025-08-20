package br.edu.ifpi.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {
    @Column(nullable = false)
    private String email;

    public Cliente() {}
    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf);
        this.email = email;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
