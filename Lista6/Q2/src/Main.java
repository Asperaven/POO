import java.util.Scanner;
import L6Q2.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Sistema de gerenciamento universitario");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome da universidade:");
        String nomeUniversidade = scanner.nextLine();
        Universidade universidade = new Universidade(nomeUniversidade);
        boolean menu = true;
        while (menu) {
            System.out.println("\n Menu de opcoes:");
            System.out.println("1. Adicionar funcionário");
            System.out.println("2. Gerar folha de pagamento");
            System.out.println("3. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do funcionário:");
                    String nomeFuncionario = scanner.nextLine();
                    System.out.println("Digite a matrícula do funcionário:");
                    String matriculaFuncionario = scanner.nextLine();
                    System.out.println("Digite o cargo do funcionário (1 - Professor, 2 - Secretario, 3 - Tecnico):");
                    int cargoFuncionario = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner
                    switch (cargoFuncionario) {
                        case 1:
                            System.out.println("O professor e coordenador? (TRUE / FALSE):");
                            boolean isCoordenador = scanner.nextBoolean();
                            System.out.println("O professor e Mestre ou Doutor?");
                            String nivelFormacao = scanner.next();
                            System.out.println("Digite a carga horária do professor:");
                            int cargaHoraria = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer do scanner
                            Professor professor = new Professor(nomeFuncionario, matriculaFuncionario, 0, nivelFormacao, cargaHoraria, isCoordenador);
                            universidade.adicionarFuncionario(professor);
                            break;
                        case 2:
                            System.out.println("Qual a carga horária do secretário?");
                            int cargaHorariaSecretario = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer do scanner
                            Secretario secretario = new Secretario(nomeFuncionario, matriculaFuncionario, 0, cargaHorariaSecretario);
                            universidade.adicionarFuncionario(secretario);
                            break;
                        case 3:
                            System.out.println("Digite o número de horas extras:");
                            int horasExtras = scanner.nextInt();
                            scanner.nextLine();
                            Tecnico tecnico = new Tecnico(nomeFuncionario, matriculaFuncionario, 0, horasExtras);
                            universidade.adicionarFuncionario(tecnico);
                            break;
                        default:
                            System.out.println("Cargo inválido.");
                    }
                    break;
                case 2:
                    universidade.gerarFolhaPagamento();
                    break;
                case 3:
                    menu = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        System.out.println("Encerrando o programa...");
        scanner.close();
    }
}
