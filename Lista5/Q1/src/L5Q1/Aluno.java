package L5Q1;

public class Aluno extends Pessoa {

    public Aluno(String nome, String matricula) {
        super();
        this.setNome(nome);
        this.setMatricula(matricula);
    }
    
    public boolean pedirEmprestado(Livro livro) {
        if (getLivrosEmprestados().size() < 3) {
            getLivrosEmprestados().add(livro);
            return true;
        } else {
            return false;
        }
    }

    public double devolverLivro(Livro livro, double diasAtraso) {
        if (getLivrosEmprestados().remove(livro)) {
            return diasAtraso * 0.5;
        } else {
            return -1;
        }
    }

}
