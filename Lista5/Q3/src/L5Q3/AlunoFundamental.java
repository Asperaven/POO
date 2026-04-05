package L5Q3;

public class AlunoFundamental extends Aluno {
    private Livro livroEmprestado;

    public AlunoFundamental(String nome, String matricula) {
        super(nome, matricula);
    }

    public Livro getLivroEmprestado() {
        return livroEmprestado;
    }

    public void setLivroEmprestado(Livro livroEmprestado) {
        this.livroEmprestado = livroEmprestado;
    }

}
