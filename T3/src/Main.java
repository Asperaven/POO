import T3Classes.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Bem-vindo ao sistema de correcao de provas!");
        System.out.println("Digite o nome da disciplina:");
        try (Scanner sc = new Scanner(System.in)) {
            String nomeDisciplina = sc.nextLine();
            System.out.println("Digite as respostas do gabarito (10 respostas, V ou F):");
            String[] respostasGabarito = sc.nextLine().split(" ");
            Gabarito gabarito = new Gabarito(respostasGabarito);
            gabarito.salvarGabarito(nomeDisciplina);
            System.out.println("Digite o numero de alunos:");
            int numeroDeAlunos = sc.nextInt();
            sc.nextLine(); 
            Provas[] provas = new Provas[numeroDeAlunos];
            for (int i = 0; i < numeroDeAlunos; i++) {
                System.out.println("Digite o nome do aluno " + (i + 1) + ":");
                String nomeAluno = sc.nextLine();
                System.out.println("Digite as respostas do aluno " + (i + 1) + " (10 respostas, V ou F):");
                String[] respostasAluno = sc.nextLine().split(" ");
                provas[i] = new Provas(respostasAluno, nomeAluno);
            }
            Disciplina disciplina = new Disciplina(nomeDisciplina, provas, gabarito);
            while (true) {
                System.out.println("Imprimir: 1 - Gabarito, 2 - Resultados Alfabeticamente, 3 - Resultados por Acertos, 4 - Sair:");
                int opcao = sc.nextInt();
                sc.nextLine(); 
                if (opcao == 1) {
                    gabarito.lerGabarito(nomeDisciplina);
                } else if (opcao == 2) {
                    disciplina.ordenarAlfabeticamente();
                    disciplina.lerProvasDeArquivo(nomeDisciplina);
                } else if (opcao == 3) {
                    disciplina.ordernarPorAcertos();
                    disciplina.lerProvasDeArquivo(nomeDisciplina);
                } else if (opcao == 4) {
                    System.out.println("Saindo do sistema. Obrigado por usar!");
                    break;
                } else {
                    System.out.println("Opcao invalida. Tente novamente.");
                }
            }
        }
    }
}
