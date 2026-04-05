package L5Q2;

public class Enfermeiro extends Funcionario {
    
    private int horasExtrasTrabalhadas;

    public Enfermeiro(String nome, int horasExtrasTrabalhadas) {
        super(nome, 2000, "INTEGRAL");
        this.horasExtrasTrabalhadas = horasExtrasTrabalhadas;
    }

    @Override
    public double exibirSalario() {
        double salario = 2000;
        if (horasExtrasTrabalhadas > 0) {
            salario += horasExtrasTrabalhadas * 50;
        }
        return salario;
    }

}
