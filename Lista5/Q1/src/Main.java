import java.util.Scanner;
import java.util.ArrayList;
import L5Q1.*;

public class Main {
    private static ArrayList<Professor> professores = new ArrayList<>();
    private static ArrayList<Aluno> alunos = new ArrayList<>();
    private static ArrayList<Livro> livros = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Sistema de gerenciamento bibliotecario");
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nMenu de opcoes:");
            System.out.println("1. Cadastrar Professor");
            System.out.println("2. Cadastrar Aluno");
            System.out.println("3. Cadastrar Livro");
            System.out.println("4. Emprestar Livro");
            System.out.println("5. Devolver Livro");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opcao:");
            int opcao = scanner.nextInt();
            
            if (opcao == 0) {
                System.out.println("Encerrando o programa...");
                break;
            }
            
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do professor:");
                    String nomeProfessor = scanner.next();
                    System.out.println("Digite a matricula do professor:");
                    String matriculaProfessor = scanner.next();
                    System.out.println("O professor e doutor? (TRUE / FALSE):");
                    boolean isDoutor = scanner.nextBoolean();
                    Professor professor = new Professor(nomeProfessor, matriculaProfessor, isDoutor);
                    professores.add(professor);
                    System.out.println("Professor cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println("Digite o nome do aluno:");
                    String nomeAluno = scanner.next();
                    System.out.println("Digite a matricula do aluno:");
                    String matriculaAluno = scanner.next();
                    Aluno aluno = new Aluno(nomeAluno, matriculaAluno);
                    alunos.add(aluno);
                    System.out.println("Aluno cadastrado com sucesso!");
                    break;
                case 3:
                    System.out.println("Digite o titulo do livro:");
                    String tituloLivro = scanner.next();
                    System.out.println("Digite o autor do livro:");
                    String autorLivro = scanner.next();
                    Livro livro = new Livro(tituloLivro, autorLivro);
                    livros.add(livro);
                    System.out.println("Livro cadastrado com sucesso!");
                    break;
                case 4:
                    System.out.println("Digite a matricula da pessoa:");
                    String matriculaPessoa = scanner.next();
                    System.out.println("Digite o titulo do livro:");
                    String tituloLivroEmprestado = scanner.next();
                    
                    Livro livroEncontrado = encontrarLivroPorTitulo(tituloLivroEmprestado);
                    
                    if (livroEncontrado == null) {
                        System.out.println("Livro nao encontrado!");
                        break;
                    }
                    
                    Professor profEmprestimo = encontrarProfessorPorMatricula(matriculaPessoa);
                    if (profEmprestimo != null) {
                        if (profEmprestimo.pedirEmprestado(livroEncontrado)) {
                            System.out.println("Livro emprestado com sucesso!");
                        } else {
                            System.out.println("Limite de livros emprestados atingido!");
                        }
                    } else {
                        Aluno alunoEmprestimo = encontrarAlunoPorMatricula(matriculaPessoa);
                        if (alunoEmprestimo != null) {
                            if (alunoEmprestimo.pedirEmprestado(livroEncontrado)) {
                                System.out.println("Livro emprestado com sucesso!");
                            } else {
                                System.out.println("Limite de livros emprestados atingido!");
                            }
                        } else {
                            System.out.println("Pessoa nao encontrada!");
                        }
                    }
                    break;
                    
                case 5:
                    System.out.println("Digite a matricula da pessoa:");
                    String matriculaPessoaDevolucao = scanner.next();
                    System.out.println("Digite o titulo do livro:");
                    String tituloLivroDevolucao = scanner.next();
                    System.out.println("Digite os dias de atraso:");
                    double diasAtraso = scanner.nextDouble();
                    
                    Livro livroDevolucao = encontrarLivroPorTitulo(tituloLivroDevolucao);
                    
                    if (livroDevolucao == null) {
                        System.out.println("Livro nao encontrado!");
                        break;
                    }
                    
                    Professor profDevolucao = encontrarProfessorPorMatricula(matriculaPessoaDevolucao);
                    if (profDevolucao != null) {
                        double multa = profDevolucao.devolverLivro(livroDevolucao, diasAtraso);
                        if (multa >= 0) {
                            System.out.println("Livro devolvido com sucesso! Multa: " + multa);
                        } else {
                            System.out.println("Livro nao encontrado!");
                        }
                    } else {
                        Aluno alunoDevolucao = encontrarAlunoPorMatricula(matriculaPessoaDevolucao);
                        if (alunoDevolucao != null) {
                            double multa = alunoDevolucao.devolverLivro(livroDevolucao, diasAtraso);
                            if (multa >= 0) {
                                System.out.println("Livro devolvido com sucesso! Multa: " + multa);
                            } else {
                                System.out.println("Livro nao encontrado!");
                            }
                        } else {
                            System.out.println("Pessoa nao encontrada!");
                        }
                    }
                    break;
                    
                default:
                    System.out.println("Opcao invalida!");
            }
        }
        scanner.close();
    }
    
    private static Professor encontrarProfessorPorMatricula(String matricula) {
        for (Professor prof : professores) {
            if (prof.getMatricula().equals(matricula)) {
                return prof;
            }
        }
        return null;
    }
    
    private static Aluno encontrarAlunoPorMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }
    
    private static Livro encontrarLivroPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(titulo)) {
                return livro;
            }
        }
        return null;
    }
}

