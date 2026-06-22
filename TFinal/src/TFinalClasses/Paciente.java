package TFinalClasses;

import java.util.*;

public class Paciente {

    private String nome;
    private int idade;
    private String planoSaude;
    private String senha;
    private double creditos;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getCreditos() {
        return creditos;
    }

    public void setCreditos(double creditos) {
        this.creditos = creditos;
    }

    public Paciente(String nome, int idade, String planoSaude, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.planoSaude = planoSaude;
        this.senha = senha;
        this.creditos = 0.0;
        this.historico = new ArrayList<>();
    }

    public void adicionarConsultaHistorico(Consulta consulta) {
        this.historico.add(consulta);
    }
}
