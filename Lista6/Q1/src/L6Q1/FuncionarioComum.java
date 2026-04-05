package L6Q1;

public class FuncionarioComum extends Funcionario {

    private int horasExtras;

    public FuncionarioComum(String nome, String cargo, double salario) {
        super(nome, cargo, salario);
    }

    @Override 
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Horas Extras: " + horasExtras);
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

}
