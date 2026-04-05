package L6Q3;
import java.util.ArrayList;

public class Departamento {
    private String nome;
    private ArrayList<ColobaradorAssalariado> colaboradores;

    public Departamento(String nome, ArrayList<ColobaradorAssalariado> colaboradores) {
        this.nome = nome;
        this.colaboradores = colaboradores;
    }

    public void adicionarColaborador(ColobaradorAssalariado colaborador) {
        colaboradores.add(colaborador);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<ColobaradorAssalariado> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(ArrayList<ColobaradorAssalariado> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public void removerColaborador(ColobaradorAssalariado colaborador) {
        colaboradores.remove(colaborador);
    }

    public void gerarFolhaSalarial() {
        double totalSalarios = 0;
        System.out.println("Folha Salarial do Departamento: " + nome);
        for (ColobaradorAssalariado colaborador : colaboradores) {
            double salario = colaborador.calcularSalario();
            totalSalarios += salario;
            System.out.println("Colaborador: " + colaborador.getNome() + " - Salário: R$ " + salario);
        }
        System.out.println("Valor Total de Salários: R$ " + totalSalarios);
    }

}
