package L6Q2;

public class Secretario extends Funcionario {
    private int horasSemanais;

    public Secretario(String nome, String matricula, double salario, int horasSemanais) {
        super(nome, matricula, salario);
        this.horasSemanais = horasSemanais;
    }

    @Override
    public void calcularSalario() {
        switch (horasSemanais) {
            case 20:
                setSalario((8 * horasSemanais) * 4);
                System.out.println("Salario calculado: " + getSalario());
                break;
            case 30:
                setSalario((8 * horasSemanais) * 4);
                System.out.println("Salario calculado: " + getSalario());
                break;
            case 40:
                setSalario((10 * horasSemanais) * 4);
                System.out.println("Salario calculado: " + getSalario());
                break;
            default:
                System.out.println("Carga horária inválida. O salário não será ajustado.");
        }
    }
}
