package Modelo;

import java.time.LocalDate;

public class Pessoa {

    private String nome;
    private LocalDate nascimento;

    public Pessoa(LocalDate nascimento, String nome) {
        this.nascimento = nascimento;
        this.nome = nome;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
}
