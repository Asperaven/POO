import L5Q3.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        boolean menu = true;
        while (menu) {
            System.out.println("Sistema de gerenciamento bibliotecario");
            System.out.println("1. Registrar Aluno");
            System.out.println("2. Registrar Livro");
            System.out.println("3. Emprestar Livro");
            System.out.println("4. Devolver Livro");
            System.out.println("5. Mostrar Alunos Registrados");
            System.out.println("6. Mostrar Livros");
            System.out.println("7. Mostrar Historico");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opcao:");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.println("\nDigite o nome do aluno:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite a matricula:");
                    String matricula = scanner.nextLine();
                    System.out.println("Tipo de aluno (1 = Fundamental, 2 = Medio):");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    Aluno novoAluno;
                    if (tipo == 1) {
                        novoAluno = new AlunoFundamental(nome, matricula);
                    } else {
                        novoAluno = new AlunoMedio(nome, matricula);
                    }
                    biblioteca.registrarAluno(novoAluno);
                    break;

                case 2:
                    System.out.println("\nDigite o titulo do livro:");
                    String titulo = scanner.nextLine();
                    System.out.println("Digite o autor do livro:");
                    String autor = scanner.nextLine();
                    biblioteca.adicionarLivro(new Livro(titulo, autor));
                    break;

                case 3:
                    System.out.println("\nDigite o nome do aluno:");
                    String nomeAluno = scanner.nextLine();
                    System.out.println("Digite o nome do livro:");
                    String nomeLivro = scanner.nextLine();
                    
                    ArrayList<Aluno> alunosEncontrados = biblioteca.buscarNomeMultiplos(nomeAluno);
                    
                    if (alunosEncontrados.size() > 1) {
                        System.out.println("Multiplos alunos com esse nome. Digite a matricula:");
                        String matriculaEmprrestimo = scanner.nextLine();
                        biblioteca.emprestarLivro(nomeAluno, matriculaEmprrestimo, nomeLivro);
                    } else {
                        biblioteca.emprestarLivro(nomeAluno, nomeLivro);
                    }
                    break;

                case 4:
                    System.out.println("\nDigite o nome do aluno:");
                    String nomeAlunoDev = scanner.nextLine();
                    System.out.println("Digite o nome do livro:");
                    String nomeLivroDev = scanner.nextLine();
                    
                    ArrayList<Aluno> alunosEncontradosDev = biblioteca.buscarNomeMultiplos(nomeAlunoDev);
                    
                    if (alunosEncontradosDev.size() > 1) {
                        System.out.println("Multiplos alunos com esse nome. Digite a matricula:");
                        String matriculaDevolucao = scanner.nextLine();
                        biblioteca.receberLivro(nomeAlunoDev, matriculaDevolucao, nomeLivroDev);
                    } else {
                        biblioteca.receberLivro(nomeAlunoDev, nomeLivroDev);
                    }
                    break;

                case 5:
                    biblioteca.mostrarAlunosRegistrados();
                    break;

                case 6:
                    biblioteca.mostrarLivros();
                    break;

                case 7:
                    biblioteca.mostrarHistorico();
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    menu = false;
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        }
        scanner.close();
    }
}
