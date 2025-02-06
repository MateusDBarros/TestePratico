import Modelo.Funcionario;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Formata a data para o padrão BR
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", decimalFormatSymbols);

        // Criando a lista e adicionando os funcionarios
        List<Funcionario> listFuncionarios = new ArrayList<>();
        listFuncionarios.add(new Funcionario(LocalDate.of(2000,10,18), "Maria", BigDecimal.valueOf(2009.44), "Operador"));
        listFuncionarios.add(new Funcionario(LocalDate.of(1990,5,12), "João", BigDecimal.valueOf(2284.38), "Operador"));
        listFuncionarios.add(new Funcionario(LocalDate.of(1961,5,2), "Caio", BigDecimal.valueOf(9836.14), "Coordenador"));
        listFuncionarios.add(new Funcionario(LocalDate.of(1988,10,14), "Miguel", BigDecimal.valueOf(19119.88), "Diretor"));
        listFuncionarios.add(new Funcionario(LocalDate.of(1995,1,5), "Alice", BigDecimal.valueOf(2234.68), "Recepcionista"));
        listFuncionarios.add(new Funcionario(LocalDate.of(1999,11,19), "Heitor", BigDecimal.valueOf(1582.72), "Operador"));
        listFuncionarios.add(new Funcionario(LocalDate.of(1993,3,31), "Arthur", BigDecimal.valueOf(4071.84), "Contador"));
        listFuncionarios.add(new Funcionario(LocalDate.of(1994,7,8), "Laura", BigDecimal.valueOf(3017.45), "Gerente"));
        listFuncionarios.add(new Funcionario(LocalDate.of(2003,5,24), "Heloísa", BigDecimal.valueOf(1606.85), "Eletricista"));
        listFuncionarios.add(new Funcionario(LocalDate.of(1996,9,2), "Helena", BigDecimal.valueOf(2799.93), "Gerente"));


        // Removendo o funcionario João
        listFuncionarios.removeIf(funcionario -> funcionario.getNome().compareTo("João") == 0);

        // Atualizar o salario
        for (Funcionario funcionario : listFuncionarios) {
            funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal("1.10")));
        }

        // Exibe os dados
        listar(listFuncionarios, formatter, decimalFormat);


        //Não me sento confortavel usando MAP então farei de outra forma
        // Imprime por Funçao
        separar("Função");

        separarPorFuncao(listFuncionarios, formatter, decimalFormat);

        // Imprime por aniversario
        separar("Mes de Aniversario");

        System.out.println("Funcionários com aniversário em Outubro ou Dezembro:");
        for (Funcionario funcionario : listFuncionarios) {
            int mes = funcionario.getNascimento().getMonthValue();

            // Verifica se o mês é 10 ou 12
            if (mes == 10 || mes == 12) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getNascimento().format(formatter));
                System.out.println("Salário: " + decimalFormat.format(funcionario.getSalario()));
                System.out.println();
            }
        }

        // Imprime por idade
        separar("Funcionario mais velho");
        maisVelho(listFuncionarios, formatter, decimalFormat);

        // Imprime em ordem alfabetica
        listFuncionarios.sort(Comparator.comparing(Funcionario::getNome));
        separar("Ordem Alfabetica");
        listar(listFuncionarios, formatter, decimalFormat);

        // Total dos salarios
        BigDecimal salarioTotal = new BigDecimal(0);
        for (Funcionario funcionario : listFuncionarios)
            salarioTotal = salarioTotal.add(funcionario.getSalario());

        separar("Total Salario");
        System.out.println("Salario total é de: " +decimalFormat.format(salarioTotal));

        // Salarios Minimos
        separar("Salarios Minimos");
        BigDecimal minimo = new BigDecimal("1212.00");
        for (Funcionario funcionario : listFuncionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(minimo, 2, RoundingMode.DOWN);
            System.out.println("Nome: " +funcionario.getNome());
            System.out.println("Salarios Minimos: " +salariosMinimos);
            System.out.println();
        }

    }


    // MEtodos utilizados

    public static void listar(List<Funcionario> lista, DateTimeFormatter formatter, DecimalFormat decimalFormat) {
        for (Funcionario funcionario : lista) {

            System.out.println("Nome: " +funcionario.getNome());
            System.out.println("Data de nascimento: " +funcionario.getNascimento().format(formatter));
            System.out.println("Salario: " +decimalFormat.format(funcionario.getSalario()));
            System.out.println("Função: " +funcionario.getFuncao());
            System.out.println();
        }
    }

    public static void separarPorFuncao(List<Funcionario> lista, DateTimeFormatter formatter, DecimalFormat decimalFormat) {

        // Cria listas para cada função
        List<Funcionario> operadores = new ArrayList<>();
        List<Funcionario> diretores = new ArrayList<>();
        List<Funcionario> coordenadores = new ArrayList<>();
        List<Funcionario> recepcionistas = new ArrayList<>();
        List<Funcionario> contadores = new ArrayList<>();
        List<Funcionario> gerentes = new ArrayList<>();
        List<Funcionario> eletricistas = new ArrayList<>();

        // Separa por função
        for (Funcionario funcionario : lista) {
            String funcao = funcionario.getFuncao().toLowerCase();
            switch (funcao) {
                case "operador":
                    operadores.add(funcionario);
                    break;
                case "diretor":
                    diretores.add(funcionario);
                    break;
                case "coordenador":
                    coordenadores.add(funcionario);
                    break;
                case "recepcionista":
                    recepcionistas.add(funcionario);
                    break;
                case "contador":
                    contadores.add(funcionario);
                    break;
                case "gerente":
                    gerentes.add(funcionario);
                    break;
                case "eletricista":
                    eletricistas.add(funcionario);
                    break;
                default:
                    break;
            }
        }

        // Exibe os funcionários agrupados por função
        System.out.println("Operadores:");
        for (Funcionario funcionario : operadores) {
            System.out.println("   Nome: " +funcionario.getNome());
            System.out.println("   Data de nascimento: " +funcionario.getNascimento().format(formatter));
            System.out.println("   Salario: " +decimalFormat.format(funcionario.getSalario()));
            System.out.println();

        }
        System.out.println();

        System.out.println("Diretores:");
        for (Funcionario funcionario : diretores) {
            System.out.println("   Nome: " +funcionario.getNome());
            System.out.println("   Data de nascimento: " +funcionario.getNascimento().format(formatter));
            System.out.println("   Salario: " +decimalFormat.format(funcionario.getSalario()));
            System.out.println();
        }
        System.out.println();

        System.out.println("Coordenadores:");
        for (Funcionario funcionario : coordenadores) {
            System.out.println("   Nome: " +funcionario.getNome());
            System.out.println("   Data de nascimento: " +funcionario.getNascimento().format(formatter));
            System.out.println("   Salario: " +decimalFormat.format(funcionario.getSalario()));
            System.out.println();
        }
        System.out.println();

        System.out.println("Recepcionistas:");
        for (Funcionario funcionario : recepcionistas) {
            System.out.println("   Nome: " +funcionario.getNome());
            System.out.println("   Data de nascimento: " +funcionario.getNascimento().format(formatter));
            System.out.println("   Salario: " +decimalFormat.format(funcionario.getSalario()));
            System.out.println();
        }
        System.out.println();

        System.out.println("Contadores:");
        for (Funcionario funcionario : contadores) {
            System.out.println("   Nome: " +funcionario.getNome());
            System.out.println("   Data de nascimento: " +funcionario.getNascimento().format(formatter));
            System.out.println("   Salario: " +decimalFormat.format(funcionario.getSalario()));
            System.out.println();
        }
        System.out.println();

        System.out.println("Gerentes:");
        for (Funcionario funcionario : gerentes) {
            System.out.println("   Nome: " +funcionario.getNome());
            System.out.println("   Data de nascimento: " +funcionario.getNascimento().format(formatter));
            System.out.println("   Salario: " +decimalFormat.format(funcionario.getSalario()));
            System.out.println();
        }
        System.out.println();

        System.out.println("Eletricistas:");
        for (Funcionario funcionario : eletricistas) {
            System.out.println("   Nome: " +funcionario.getNome());
            System.out.println("   Data de nascimento: " +funcionario.getNascimento().format(formatter));
            System.out.println("   Salario: " +decimalFormat.format(funcionario.getSalario()));
            System.out.println();
        }
        System.out.println();
    }


    public static void separar(String frase) {
        System.out.println();
        System.out.println();
        System.out.println("----------- Separando por " +frase+ " ----------------");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void maisVelho(List<Funcionario> lista, DateTimeFormatter formatter, DecimalFormat decimalFormat) {

        Funcionario maisVelho = lista.get(0);

        for (Funcionario funcionario : lista) {
            if (funcionario.getNascimento().isBefore(maisVelho.getNascimento()))
                maisVelho = funcionario;

        }
        System.out.println("Nome: " +maisVelho.getNome());
        System.out.println("Data de nascimento: " +maisVelho.getNascimento().format(formatter));
        System.out.println("Salario: " +decimalFormat.format(maisVelho.getSalario()));
        System.out.println("Função: " +maisVelho.getFuncao());
        System.out.println();
    }
}