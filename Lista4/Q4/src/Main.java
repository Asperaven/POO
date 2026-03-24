import Classes.Cruzamento;
import Classes.Semaforo;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Semaforo semaforo = new Semaforo();
        Cruzamento cruzamento = new Cruzamento("Sentido Horizontal", "Sentido Vertical", semaforo);
        System.out.println("Menu de gerenciamento de transito:");
        System.out.println("1. Preencher sentidos do cruzamento");
        System.out.println("2. Definir preferencia de passagem");
        System.out.println("3. Gerar transito");
        System.out.println("0. Sair");
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();
        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    cruzamento.preencherSentidos(null, null);
                    break;
                case 2:
                    cruzamento.definirPreferencia();
                    break;
                case 3:
                    cruzamento.gerarTransito();
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
            System.out.println("Menu de gerenciamento de transito:");
            System.out.println("1. Preencher sentidos do cruzamento");  
            System.out.println("2. Definir preferencia de passagem");
            System.out.println("3. Gerar transito");
            System.out.println("0. Sair");
            opcao = scanner.nextInt();
    }
    scanner.close();
}
}
