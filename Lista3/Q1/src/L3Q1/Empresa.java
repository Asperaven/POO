package L3Q1;

public class Empresa {
    String nome;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Funcionario[] Funcionarios;

    public void darAumento(String nome,double valor) {
        for (int i = 0; i < Funcionarios.length; i++) {
            if (Funcionarios[i] != null && Funcionarios[i].getNome().equals(nome)) {
                Funcionarios[i].setSalario(Funcionarios[i].getSalario() + valor);
            }
        }
    }

    public void demitirFuncionario(String nome) {
        for (int i = 0; i < Funcionarios.length; i++) {
            if (Funcionarios[i] != null && Funcionarios[i].getNome().equals(nome)) {
                Funcionarios[i] = null;
            }
        }
    }

    public void promoverFuncionario(String nome, String novoCargo, double valor) {
        for (int i = 0; i < Funcionarios.length; i++) {
            if (Funcionarios[i] != null && Funcionarios[i].getNome().equals(nome)) {
                Funcionarios[i].setCargo(novoCargo);
                Funcionarios[i].setSalario(Funcionarios[i].getSalario() + valor);
            }
        }
    }

    public void registrarNovoFuncionario(String nome, String cargo, double salario) {
        Funcionario novoFuncionario = new Funcionario(nome, cargo, salario);
        novoFuncionario.setNome(nome);
        novoFuncionario.setCargo(cargo);

        for (int i = 0; i < Funcionarios.length; i++) {
            if (Funcionarios[i] == null) {
                Funcionarios[i] = novoFuncionario;
                break;
            }
        }
    }

    public void registrarFuncionarioTransferido(String nome, String cargo, double salario) {
        Funcionario novoFuncionario = new Funcionario(nome, cargo, salario);
        novoFuncionario.setNome(nome);
        novoFuncionario.setCargo(cargo);
        novoFuncionario.setSalario(salario);

        for (int i = 0; i < Funcionarios.length; i++) {
            if (Funcionarios[i] == null) {
                Funcionarios[i] = novoFuncionario;
                break;
            }
        }
    }

    public void mostrarListaDeFuncionarios() {
        for (int i = 0; i < Funcionarios.length; i++) {
            if (Funcionarios[i] != null) {
                System.out.println("Nome: " + Funcionarios[i].getNome());
                System.out.println("Cargo: " + Funcionarios[i].getCargo());
                System.out.println("Salário: " + Funcionarios[i].getSalario());
                System.out.println();
            }
        }
    }

    public Empresa(String nome, Funcionario[] funcionarios) {
        this.nome = nome;
        this.Funcionarios = funcionarios;
    }

}
