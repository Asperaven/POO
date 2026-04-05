package L3Q1;

public class Funcionario {
    String nome;
    String cargo;
    double salario;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        if (salario <= 0) {
            System.out.println("Salário não pode ser negativo ou zero.");
            return;
        }
        this.salario = salario;
    }

    public Funcionario(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        setSalario(salario); 
    }

    public Funcionario(String nome, String cargo) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = 1509.00;
    }
}
