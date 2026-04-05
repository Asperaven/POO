package L5Q2;
import java.util.ArrayList;

public class Hospital {
    private Medico diretor;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<String> cirurgiasRealizadas;

    public Hospital(Medico diretor) {
        this.diretor = diretor;
        this.funcionarios = new ArrayList<>();
        this.cirurgiasRealizadas = new ArrayList<>();
    }

    public Medico getDiretor() {
        return diretor;
    }

    public void setDiretor(Medico diretor) {
        this.diretor = diretor;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public ArrayList<String> getCirurgiasRealizadas() {
        return cirurgiasRealizadas;
    }

    public void setCirurgiasRealizadas(ArrayList<String> cirurgiasRealizadas) {
        this.cirurgiasRealizadas = cirurgiasRealizadas;
    }

    public void contratarFuncionario(Funcionario funcionario) {
        if (diretor != null && "DIRETOR".equals(diretor.getCargo())) {
            funcionarios.add(funcionario);
            System.out.println("Funcionario contratado com sucesso!");
        } else {
            System.out.println("Erro: Apenas o diretor pode contratar funcionarios!");
        }
    }

    public void demitirFuncionario(Funcionario funcionario) {
        if (diretor != null && "DIRETOR".equals(diretor.getCargo())) {
            if (funcionarios.remove(funcionario)) {
                System.out.println("Funcionario demitido com sucesso!");
            } else {
                System.out.println("Erro: Funcionario nao encontrado!");
            }
        } else {
            System.out.println("Erro: Apenas o diretor pode demitir funcionarios!");
        }
    }

    public void realizarCirurgia(String cirurgia, Medico medico) {
        if (medico.isCirurgiao()) {
            cirurgiasRealizadas.add(cirurgia);
            System.out.println("Cirurgia '" + cirurgia + "' realizada com sucesso pelo Dr. " + medico);
        } else {
            System.out.println("Erro: O medico " + medico + " nao e um cirurgiao!");
        }
    }

}
