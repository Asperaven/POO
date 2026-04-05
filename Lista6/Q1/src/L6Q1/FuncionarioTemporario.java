package L6Q1;

public class FuncionarioTemporario extends Funcionario{

    private int contratoDeTrabalho;

    public FuncionarioTemporario(String nome, String cargo, double salario) {
        super(nome, cargo, salario);
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Dias remanescentes do contrato: " + contratoDeTrabalho);
    }

    public int getContratoDeTrabalho() {
        return contratoDeTrabalho;
    }

    public void setContratoDeTrabalho(int contratoDeTrabalho) {
        this.contratoDeTrabalho = contratoDeTrabalho;
    }
}
