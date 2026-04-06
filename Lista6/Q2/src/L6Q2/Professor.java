package L6Q2;

public class Professor extends Funcionario {
    private String titulacao;
    private int cargaHoraria;
    private boolean eCoordenador;

    public Professor(String nome, String matricula, double salario, String titulacao, int cargaHoraria, boolean eCoordenador) {
        super(nome, matricula, salario);
        this.titulacao = titulacao;
        this.cargaHoraria = cargaHoraria;
        this.eCoordenador = eCoordenador;
    }

    @Override
    public void calcularSalario() {
        if (eCoordenador) {
            if (titulacao.equalsIgnoreCase("Doutorado")) {
                int salarioPreBonus = 100 * cargaHoraria;
                setSalario(salarioPreBonus + (salarioPreBonus * 0.20));
                System.out.println("Salário calculado: " + getSalario());
            } else if (titulacao.equalsIgnoreCase("Mestrado")) {
                int salarioPreBonus = 80 * cargaHoraria;
                setSalario(salarioPreBonus + (salarioPreBonus * 0.20));
                System.out.println("Salário calculado: " + getSalario());
            } else {
                System.out.println("Titulação inválida. O salário não será ajustado.");
            }
        } else {
            if (titulacao.equalsIgnoreCase("Doutorado")) {
                int salarioPreBonus = 100 * cargaHoraria;
                setSalario(salarioPreBonus);
                System.out.println("Salário calculado: " + getSalario());
            } else if (titulacao.equalsIgnoreCase("Mestrado")) {
                int salarioPreBonus = 80 * cargaHoraria;
                setSalario(salarioPreBonus);
                System.out.println("Salário calculado: " + getSalario());
            } else {
                System.out.println("Titulação inválida. O salário não será ajustado.");
            }
        }
    }
        

}
