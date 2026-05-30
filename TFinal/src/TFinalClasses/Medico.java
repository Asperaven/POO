package TFinalClasses;
import java.util.*;

public abstract class Medico {
    private String nome;
    private String especialidade;
    private double valorConsulta;
    private ArrayList<Avaliacao> avalicoes;

    public Medico(String nome, String especialidade, double valorConsulta) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.valorConsulta = valorConsulta;
        this.avalicoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public ArrayList<Avaliacao> getAvalicoes() {
        return avalicoes;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        avalicoes.add(avaliacao);
    }

}
