import T3Classes.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Bem-vindo ao sistema de correcao de provas!");
        
        try (Scanner sc = new Scanner(System.in)) {
            // Registra alunos
            System.out.println("Digite o numero de alunos:");
            int numeroDeAlunos = sc.nextInt();
            sc.nextLine();
            
            String[] nomesAlunos = new String[numeroDeAlunos];
            for (int i = 0; i < numeroDeAlunos; i++) {
                System.out.println("Digite o nome do aluno " + (i + 1) + ":");
                nomesAlunos[i] = sc.nextLine();
            }
            
            // Registra disciplinas e provas
            System.out.println("Digite o numero de disciplinas:");
            int numeroDeDisciplinas = sc.nextInt();
            sc.nextLine();
            
            Disciplina[] disciplinas = new Disciplina[numeroDeDisciplinas];
            for (int i = 0; i < numeroDeDisciplinas; i++) {
                System.out.println("\n=== Disciplina " + (i + 1) + " ===");
                System.out.println("Digite o nome da disciplina:");
                String nomeDisciplina = sc.nextLine();
                
                System.out.println("Digite as respostas do gabarito (10 respostas, V ou F):");
                String[] respostasGabarito = sc.nextLine().split("");
                Gabarito gabarito = new Gabarito(respostasGabarito);
                gabarito.salvarGabarito(nomeDisciplina);
                
                // Registrar provas dos alunos para cada disciplina
                Aluno[] provas = new Aluno[numeroDeAlunos];
                for (int j = 0; j < numeroDeAlunos; j++) {
                    System.out.println("Digite as respostas de " + nomesAlunos[j] + " para " + nomeDisciplina + " (10 respostas, V ou F):");
                    String[] respostasAluno = sc.nextLine().split("");
                    provas[j] = new Aluno(respostasAluno, nomesAlunos[j]);
                }
                
                disciplinas[i] = new Disciplina(nomeDisciplina, provas, gabarito);
            }
            
            // Menu e visualizacao de resultados
            while (true) {
                System.out.println("\n=== Menu Principal ===");
                System.out.println("Selecione uma opcao:");
                System.out.println("1 - Selecionar disciplina");
                System.out.println("2 - Sair");
                int opcaoPrincipal = sc.nextInt();
                sc.nextLine();
                
                if (opcaoPrincipal == 1) {
                    System.out.println("Disciplinas disponiveis:");
                    for (int i = 0; i < disciplinas.length; i++) {
                        System.out.println((i + 1) + " - " + disciplinas[i].getNome());
                    }
                    System.out.println("Escolha uma disciplina:");
                    int escolhaDisciplina = sc.nextInt() - 1;
                    sc.nextLine();
                    
                    if (escolhaDisciplina < 0 || escolhaDisciplina >= disciplinas.length) {
                        System.out.println("Opcao invalida.");
                        continue;
                    }
                    
                    Disciplina disciplinaAtual = disciplinas[escolhaDisciplina];
                    while (true) {
                        System.out.println("\n=== Menu da Disciplina: " + disciplinaAtual.getNome() + " ===");
                        System.out.println("1 - Gabarito");
                        System.out.println("2 - Resultados Alfabeticamente");
                        System.out.println("3 - Resultados por Acertos");
                        System.out.println("4 - Voltar ao menu principal");
                        int opcao = sc.nextInt();
                        sc.nextLine();
                        
                        if (opcao == 1) {
                            disciplinaAtual.getGabarito().lerGabarito(disciplinaAtual.getNome());
                        } else if (opcao == 2) {
                            disciplinaAtual.ordenarAlfabeticamente();
                            disciplinaAtual.lerProvasDeArquivo(disciplinaAtual.getNome());
                        } else if (opcao == 3) {
                            disciplinaAtual.ordernarPorAcertos();
                            disciplinaAtual.lerProvasDeArquivo(disciplinaAtual.getNome());
                        } else if (opcao == 4) {
                            break;
                        } else {
                            System.out.println("Opcao invalida. Tente novamente.");
                        }
                    }
                } else if (opcaoPrincipal == 2) {
                    System.out.println("Saindo do sistema. Obrigado por usar!");
                    break;
                } else {
                    System.out.println("Opcao invalida. Tente novamente.");
                }
            }
        }
    }
}
