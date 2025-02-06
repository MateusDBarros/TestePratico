package Modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa{

    private BigDecimal salario;
    private String funcao;

    public Funcionario(LocalDate nascimento, String nome, BigDecimal salario, String funcao) {
        super(nascimento, nome);
        this.salario = salario;
        this.funcao = funcao;
    }

    // Getters
    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    // Setters
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

}
