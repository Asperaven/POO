package L5Q1;
import java.util.ArrayList;


public class Pessoa {
    private String nome;
    private String matricula;
    private ArrayList<Livro> livrosEmprestados;

    public Pessoa() {
        this.livrosEmprestados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public ArrayList<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }
    public void setLivrosEmprestados(ArrayList<Livro> livrosEmprestados) {
        this.livrosEmprestados = livrosEmprestados;
    }


}
