package L6Q3;

public class ColobaradorAssalariado {
    private String codigo;
    private String nome;
    private int horasTrabalhadas;

    public ColobaradorAssalariado(String codigo, String nome, int horasTrabalhadas) {
        this.codigo = codigo;
        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public double calcularSalario() {
        double salario = horasTrabalhadas * 20;
        return salario;
    }

    
}
