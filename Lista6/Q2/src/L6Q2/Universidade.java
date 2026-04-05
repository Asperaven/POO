package L6Q2;
import java.util.ArrayList;

public class Universidade {
    private String nome; 
    private ArrayList<Funcionario> funcionarios;

    public Universidade(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void gerarFolhaPagamento() {
        int valorTotalPago = 0;
        System.out.println("Folha de Pagamento da Universidade " + nome + ":");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Funcionário: " + funcionario.getNome());
            funcionario.calcularSalario();
            valorTotalPago += funcionario.getSalario();
        }
        System.out.println("Valor total pago pela Universidade: " + valorTotalPago);
    }

}
