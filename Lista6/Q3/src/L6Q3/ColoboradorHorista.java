package L6Q3;

public class ColoboradorHorista extends ColobaradorAssalariado {
    private double valorHoraTrabalhada;

    public ColoboradorHorista(String codigo, String nome, int horasTrabalhadas, double valorHoraTrabalhada) {
        super(codigo, nome, horasTrabalhadas);
        this.valorHoraTrabalhada = valorHoraTrabalhada;
    }

    public double getValorHoraTrabalhada() {
        return valorHoraTrabalhada;
    }

    public void setValorHoraTrabalhada(double valorHoraTrabalhada) {
        this.valorHoraTrabalhada = valorHoraTrabalhada;
    }

    @Override
    public double calcularSalario() {
        double salario = getHorasTrabalhadas() * valorHoraTrabalhada;
        return salario;
    }

    
}
