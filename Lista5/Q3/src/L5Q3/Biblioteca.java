package L5Q3;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Livro> livrosDisponiveis;
    private ArrayList<Aluno> alunos;
    private ArrayList<String> historico; 

    public Biblioteca() {
        this.livrosDisponiveis = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livrosDisponiveis.add(livro);
        System.out.println("Livro '" + livro.getNome() + "' adicionado a biblioteca.");
    }

    public void registrarAluno(Aluno aluno) {
        if (buscarMatricula(aluno.getMatricula()) == null) {
            alunos.add(aluno);
            System.out.println("Aluno registrado com sucesso!");
        } else {
            System.out.println("Erro: Aluno com essa matricula ja registrado.");
        }
    }

    public void emprestarLivro(String nomeAluno, String nomeLivro) {
        ArrayList<Aluno> alunosComNome = buscarNomeMultiplos(nomeAluno);
        
        if (alunosComNome.isEmpty()) {
            System.out.println("Erro: Aluno nao encontrado.");
            return;
        }

        if (alunosComNome.size() > 1) {
            System.out.println("Erro: Multiplos alunos com esse nome. Use o metodo com matricula.");
            return;
        }

        Aluno aluno = alunosComNome.get(0);
        emprestarLivroInterno(aluno, nomeLivro);
    }

    public void emprestarLivro(String nomeAluno, String matricula, String nomeLivro) {
        Aluno aluno = buscarMatricula(matricula);
        
        if (aluno == null) {
            System.out.println("Erro: Aluno com essa matricula nao encontrado.");
            return;
        }

        if (!aluno.getNome().equals(nomeAluno)) {
            System.out.println("Erro: Matricula nao corresponde ao nome fornecido.");
            return;
        }

        emprestarLivroInterno(aluno, nomeLivro);
    }

    private void emprestarLivroInterno(Aluno aluno, String nomeLivro) {
        if (aluno instanceof AlunoFundamental) {
            AlunoFundamental alunoFund = (AlunoFundamental) aluno;
            if (alunoFund.getLivroEmprestado() != null) {
                System.out.println("Erro: Aluno do ensino fundamental ja possui um livro emprestado. Devolva o livro anterior.");
                return;
            }
        }

        Livro livro = buscarLivro(nomeLivro);
        if (livro == null) {
            System.out.println("Erro: Livro nao disponivel na biblioteca.");
            return;
        }

        livrosDisponiveis.remove(livro);

        if (aluno instanceof AlunoFundamental) {
            ((AlunoFundamental) aluno).setLivroEmprestado(livro);
        } else if (aluno instanceof AlunoMedio) {
            ((AlunoMedio) aluno).getLivrosEmprestados().add(livro);
        }

        historico.add(aluno.getNome() + " (Matricula: " + aluno.getMatricula() + ") emprestou '" + livro.getNome() + "'");
        System.out.println("Livro '" + livro.getNome() + "' emprestado com sucesso!");
    }

    public void receberLivro(String nomeAluno, String nomeLivro) {
        ArrayList<Aluno> alunosComNome = buscarNomeMultiplos(nomeAluno);
        
        if (alunosComNome.isEmpty()) {
            System.out.println("Erro: Aluno nao encontrado.");
            return;
        }

        if (alunosComNome.size() > 1) {
            System.out.println("Erro: Multiplos alunos com esse nome. Use o metodo com matricula.");
            return;
        }

        Aluno aluno = alunosComNome.get(0);
        receberLivroInterno(aluno, nomeLivro);
    }

    public void receberLivro(String nomeAluno, String matricula, String nomeLivro) {
        Aluno aluno = buscarMatricula(matricula);
        
        if (aluno == null) {
            System.out.println("Erro: Aluno com essa matricula nao encontrado.");
            return;
        }

        if (!aluno.getNome().equals(nomeAluno)) {
            System.out.println("Erro: Matricula nao corresponde ao nome fornecido.");
            return;
        }

        receberLivroInterno(aluno, nomeLivro);
    }

    private void receberLivroInterno(Aluno aluno, String nomeLivro) {
        Livro livro = null;
        if (aluno instanceof AlunoMedio) {
            ArrayList<Livro> livros = ((AlunoMedio) aluno).getLivrosEmprestados();
            for (Livro l : livros) {
                if (l.getNome().equals(nomeLivro)) {
                    livro = l;
                    livros.remove(l);
                    break;
                }
            }
        } else if (aluno instanceof AlunoFundamental) {
            AlunoFundamental alunoFund = (AlunoFundamental) aluno;
            if (alunoFund.getLivroEmprestado() != null && alunoFund.getLivroEmprestado().getNome().equals(nomeLivro)) {
                livro = alunoFund.getLivroEmprestado();
                alunoFund.setLivroEmprestado(null);
            }
        }

        if (livro == null) {
            System.out.println("Erro: Este aluno nao tem este livro emprestado.");
            return;
        }

        livrosDisponiveis.add(livro);
        historico.add(aluno.getNome() + " (Matricula: " + aluno.getMatricula() + ") devolveu '" + livro.getNome() + "'");
        System.out.println("Livro '" + livro.getNome() + "' devolvido com sucesso!");
    }

    public void mostrarAlunosRegistrados() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno registrado.");
            return;
        }
        System.out.println("Alunos Registrados");
        for (Aluno aluno : alunos) {
            String tipo = (aluno instanceof AlunoFundamental) ? "Fundamental" : "Medio";
            System.out.println("Nome: " + aluno.getNome() + " | Matricula: " + aluno.getMatricula() + " | Tipo: " + tipo);
        }
    }

    public void mostrarLivros() {
        System.out.println("Livros Disponiveis");
        if (livrosDisponiveis.isEmpty()) {
            System.out.println("Nenhum livro disponivel no momento.");
        } else {
            for (Livro livro : livrosDisponiveis) {
                System.out.println("Titulo: " + livro.getNome() + " | Autor: " + livro.getAutor());
            }
        }

        System.out.println("Livros Emprestados:");
        boolean temEmprestados = false;
        for (Aluno aluno : alunos) {
            if (aluno instanceof AlunoMedio) {
                ArrayList<Livro> livros = ((AlunoMedio) aluno).getLivrosEmprestados();
                for (Livro livro : livros) {
                    System.out.println("'" + livro.getNome() + "' - emprestado para " + aluno.getNome());
                    temEmprestados = true;
                }
            } else if (aluno instanceof AlunoFundamental) {
                AlunoFundamental alunoFund = (AlunoFundamental) aluno;
                if (alunoFund.getLivroEmprestado() != null) {
                    System.out.println("'" + alunoFund.getLivroEmprestado().getNome() + "' - emprestado para " + aluno.getNome());
                    temEmprestados = true;
                }
            }
        }
        if (!temEmprestados) {
            System.out.println("Nenhum livro emprestado.");
        }
    }

    public Aluno buscarMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    public ArrayList<Aluno> buscarNomeMultiplos(String nome) {
        ArrayList<Aluno> resultado = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equals(nome)) {
                resultado.add(aluno);
            }
        }
        return resultado;
    }

    public Livro buscarLivro(String nome) {
        for (Livro livro : livrosDisponiveis) {
            if (livro.getNome().equals(nome)) {
                return livro;
            }
        }
        return null;
    }


    public void mostrarHistorico() {
        System.out.println("Historico de transacoes:");
        if (historico.isEmpty()) {
            System.out.println("Historico vazio.");
        } else {
            for (String transacao : historico) {
                System.out.println(transacao);
            }
        }
    }
}
