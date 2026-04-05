package L5Q3;

import java.util.ArrayList;

public class AlunoMedio extends Aluno {
    private ArrayList<Livro> livrosEmprestados;

    public AlunoMedio(String nome, String matricula) {
        super(nome, matricula);
        this.livrosEmprestados = new ArrayList<>();
    }

    public ArrayList<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void setLivrosEmprestados(ArrayList<Livro> livrosEmprestados) {
        this.livrosEmprestados = livrosEmprestados;
    }

}
