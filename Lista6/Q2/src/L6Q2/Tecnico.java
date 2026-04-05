package L6Q2;

public class Tecnico extends Funcionario {
    private int horasExtras;

    public Tecnico(String nome, String matricula, double salario, int horasExtras) {
        super(nome, matricula, salario);
        this.horasExtras = horasExtras;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    @Override
    public void calcularSalario() {
        double salarioBase = getSalario();
        double valorHorasExtras = horasExtras * 15; // Supondo que cada hora extra vale 15
        double salarioTotal = salarioBase + valorHorasExtras;
        setSalario(salarioTotal);
        System.out.println("Salário calculado: " + getSalario());
    }

}
