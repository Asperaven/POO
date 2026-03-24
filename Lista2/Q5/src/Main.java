import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        Elevador elevador = new Elevador();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número total de andares do prédio:");
        elevador.totalAndares = sc.nextInt();
        System.out.println("Menu de opcoes: 1 - Entrar, 2 - Sair, 3 - Subir, 4 - Descer, 5 - Subir para andar específico, 6 - Descer para andar específico, 0 - Sair do programa");
        int opcao = sc.nextInt();
        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    elevador.entra();
                    break;
                case 2:
                    elevador.sai();
                    break;
                case 3:
                    elevador.sobe();
                    break;
                case 4:
                    elevador.desce();
                    break;
                case 5:
                    System.out.println("Digite o andar para o qual deseja subir:");
                    int andarAlvo = sc.nextInt();
                    elevador.sobeAndar(andarAlvo);
                    break;
                case 6:
                    System.out.println("Digite o andar para o qual deseja descer:");
                    int andarAlvoDescer = sc.nextInt();
                    elevador.desceAndar(andarAlvoDescer);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println(elevador.boasVindas());
            System.out.println("Menu de opcoes: 1 - Entrar, 2 - Sair, 3 - Subir, 4 - Descer, 5 - Subir para andar específico, 6 - Descer para andar específico, 0 - Sair do programa");
            opcao = sc.nextInt();
        }
        sc.close();
    }
}
