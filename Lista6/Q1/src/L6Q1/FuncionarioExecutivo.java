package L6Q1;

public class FuncionarioExecutivo extends Funcionario {

    public FuncionarioExecutivo(String nome, String cargo, double salario) {
        super(nome, cargo, salario);
        super.setSalario(10000);
    }


    public void contratarFuncionario(Funcionario funcionario, Empresa empresa) {
        empresa.getFuncionarios().add(funcionario);
        System.out.println(funcionario.getNome() + " contratado com sucesso!");
    }

    public void demitirFuncionario(Funcionario funcionario, Empresa empresa) {
        empresa.getFuncionarios().remove(funcionario);
        System.out.println(funcionario.getNome() + " demitido com sucesso!");
    }

    public void promoverFuncionario(Funcionario funcionario, String novoCargo, double novoSalario, Empresa empresa) {
        funcionario.setCargo(novoCargo);
        funcionario.setSalario(novoSalario);
        System.out.println(funcionario.getNome() + " promovido!");
    }



}


