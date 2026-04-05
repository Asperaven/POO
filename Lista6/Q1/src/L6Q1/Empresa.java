package L6Q1;
import java.util.ArrayList;

public class Empresa {
    private String nome;
    private ArrayList<Funcionario> funcionarios;

    public Empresa(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void exibirFuncionarios() {
        System.out.println("Funcionarios executivos:");
        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof FuncionarioExecutivo) {
                funcionario.exibirInformacoes();
            }
        }
        System.out.println("Funcionarios comuns:");
        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof FuncionarioComum) {
                funcionario.exibirInformacoes();
            }
        }
        System.out.println("Funcionarios temporarios:");
        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof FuncionarioTemporario) {
                funcionario.exibirInformacoes();
            }
        }
    }

    public Funcionario buscarFuncionario(String nome) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().equalsIgnoreCase(nome)) {
                return funcionario;
            }
        }
        return null; 
    }

}
