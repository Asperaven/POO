import L4Q2.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Sistema de gerenciamento de hotel");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o valor da diária: ");
        float valorDiaria = scanner.nextFloat();
        System.out.print("O hotel oferece café da manhã? (true/false): ");
        boolean cafeDaManha = scanner.nextBoolean();
        Hotel hotel = new Hotel(valorDiaria, cafeDaManha, null, 0);
        System.out.println("Menu de opcoes:");
        System.out.println("1. Registrar estadia");
        System.out.println("2. Pagamento de estadia");
        System.out.println("3. Sair");
        int opcao = scanner.nextInt();
        while (opcao != 3) {
            switch (opcao) {
                case 1:
                    System.out.println("---Check in---");
                    System.out.print("Digite a quantidade de dias: ");
                    double quantidadeDeDias = scanner.nextDouble();
                    Quarto quarto = new Quarto(quantidadeDeDias, false);
                    hotel.getQuartos().add(quarto);
                    break;
                case 2:
                    System.out.println("---Check out---");
                    System.out.print("Digite o número do quarto: ");
                    int numeroQuarto = scanner.nextInt();
                    if (numeroQuarto < hotel.getQuartos().size()) {
                        System.out.println("Tipo de pagamento (1 para à vista, 2 para parcelado): ");
                        int tipoPagamento = scanner.nextInt();
                        System.out.print("Digite a avaliação (1-5): ");
                        int avaliacao = scanner.nextInt();
                        hotel.gerarCupomFiscal(hotel.getQuartos().get(numeroQuarto), tipoPagamento, avaliacao);
                    } else {
                        System.out.println("Número de quarto inválido.");
                    }
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
            System.out.println("Menu de opcoes:");
            System.out.println("1. Registrar estadia");
            System.out.println("2. Pagamento de estadia");
            System.out.println("3. Sair");
            opcao = scanner.nextInt();
        }
        scanner.close();
        System.out.println("Saindo do sistema...");
    }
}
