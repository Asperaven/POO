package L5Q1;

public class Professor extends Pessoa {
    private boolean isDoutor;
    
    public boolean isDoutor() {
        return isDoutor;
    }
    
    public void setDoutor(boolean isDoutor) {
        this.isDoutor = isDoutor;
    }
    
    public boolean pedirEmprestado(Livro livro) {
        if (getLivrosEmprestados().size() < 4) {
            getLivrosEmprestados().add(livro);
            return true;
        } else {
            return false;
        }
    }

    public double devolverLivro(Livro livro, double diasAtraso) {
        if (getLivrosEmprestados().remove(livro)) {
            if (isDoutor) {
                return diasAtraso;
            } else {
                return diasAtraso * 0.8;
            }
        } else {
            return -1;
        }
    }

    public Professor(String nome, String matricula, boolean isDoutor) {
        super();
        this.setNome(nome);
        this.setMatricula(matricula);
        this.isDoutor = isDoutor;
    }

}
