package TFinalClasses;

import java.util.*;

public class Paciente {
    private String nome;
    private int idade;
    private String planoSaude;
    private String senha;
    private ArrayList<Consulta> historico;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }

    public ArrayList<Consulta> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<Consulta> historico) {
        this.historico = historico;
    }

    public Paciente(String nome, int idade, String planoSaude) {
        this.nome = nome;
        this.idade = idade;
        this.planoSaude = planoSaude;
        this.senha = "";
        this.historico = new ArrayList<>();
    }

    public Paciente(String nome, int idade, String planoSaude, String senha) {
        this(nome, idade, planoSaude);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void adicionarConsulta(Consulta consulta) {
        this.historico.add(consulta);
    }

    @Override
    public String toString() {
        return nome + " (" + idade + " anos, plano: " + (planoSaude.isEmpty() ? "Nenhum" : planoSaude) + ")";
    }
}
