package L6Q3;

public class ColoboradorComissionado extends ColobaradorAssalariado {
    private double numeroDeVendas;

    public ColoboradorComissionado(String codigo, String nome, int horasTrabalhadas, double numeroDeVendas) {
        super(codigo, nome, horasTrabalhadas);
        this.numeroDeVendas = numeroDeVendas;
    }

    public double getNumeroDeVendas() {
        return numeroDeVendas;
    }

    public void setNumeroDeVendas(double numeroDeVendas) {
        this.numeroDeVendas = numeroDeVendas;
    }

    @Override
    public double calcularSalario() {
        double salario = (numeroDeVendas * super.getHorasTrabalhadas());
        return salario;
    }
    

}
