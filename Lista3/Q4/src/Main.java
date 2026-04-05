import java.util.Scanner;
import L3Q4.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Disciplina: Programacao Orientada a Objetos");
        System.out.println("Quantidade maxima de alunos admitda?");
        int quantidadeMaxima = Integer.parseInt(scanner.nextLine());
        Disciplina disciplina = new Disciplina("POO", "Programacao Orientada a Objetos", quantidadeMaxima);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Matricular aluno");
            System.out.println("2. Atribuir notas");
            System.out.println("3. Alterar notas");
            System.out.println("4. Gerar estatísticas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opcao: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do aluno: ");
                    String nomeAluno = scanner.nextLine();
                    System.out.print("Digite a matricula do aluno: ");
                    String matriculaAluno1 = scanner.nextLine();
                    disciplina.matricularAluno(nomeAluno, matriculaAluno1);
                    break;
                case 2:
                    System.out.print("Digite a matricula do aluno: ");
                    String matriculaAluno2 = scanner.nextLine();
                    System.out.print("Digite a nota 1: ");
                    double nota1 = scanner.nextDouble();
                    System.out.print("Digite a nota 2: ");
                    double nota2 = scanner.nextDouble();
                    scanner.nextLine(); 
                    disciplina.atribuirNota(matriculaAluno2, nota1, nota2);
                    break;
                case 3:
                    System.out.print("Digite a matricula do aluno: ");
                    String matriculaAluno3 = scanner.nextLine();
                    System.out.print("Digite a nova nota 1: ");
                    double novaNota1 = scanner.nextDouble();
                    System.out.print("Digite a nova nota 2: ");
                    double novaNota2 = scanner.nextDouble();
                    scanner.nextLine(); 
                    disciplina.alterarNota(matriculaAluno3, novaNota1, novaNota2);
                    break;
                case 4:
                    disciplina.gerarEstatisticas();
                    break;
                case 5:
                    scanner.close();
                    return;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }
    }
}

