package L5Q2;

public class Funcionario {
    private String nome;
    private double salario;
    private String turno;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public double exibirSalario() {
        return salario;
    }

    public Funcionario(String nome, double salario, String turno) {
        this.nome = nome;
        this.salario = 1200;
        this.turno = "INTEGRAL";
    }

}
