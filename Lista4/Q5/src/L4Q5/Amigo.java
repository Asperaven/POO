package L4Q5;
import java.util.ArrayList;

public class Amigo {
    private String nome;
    private String dataNascimento;
    private ArrayList<String> presentesDados;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<String> getPresentesDados() {
        return presentesDados;
    }

    public void setPresentesDados(ArrayList<String> presentesDados) {
        this.presentesDados = presentesDados;
    }

    public Amigo(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.presentesDados = new ArrayList<>();
    }

    public void mostrarDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Data de Nascimento: " + dataNascimento);
        System.out.println("Presentes Dados: " + presentesDados);
    }

}
