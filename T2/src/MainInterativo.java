import T2Classes.*;
import java.util.Scanner;

public class MainInterativo {
    public static void main(String[] args) throws Exception {
        System.out.println("Determine a posicao do alimento no eixo X.");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        System.out.println("Determine a posicao do alimento no eixo Y.");
        int y = scanner.nextInt();
        Alimento alimento = new Alimento(x, y);
        System.out.println("Determine a cor do robo.");
        String cor = scanner.next();
        Robo robo1;
        try {
            robo1 = new Robo(cor);
        } catch (RoboCorInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
            scanner.close();
            return;
        }
        
        TabuleiroCartesianoIndividual tabuleiro = new TabuleiroCartesianoIndividual(robo1, alimento);
        
        System.out.println("Alimento posicionado em: (" + alimento.getX() + ", " + alimento.getY() + ")");
        System.out.println("Menu de controle do robo. Digite a direcao para mover o robo (1/up, 2/down, 3/right, 4/left) ou 'sair' para encerrar:");
        
        tabuleiro.exibirTabuleiro();
        
        while (true) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando o programa.");
                break;
            }
            try {
                robo1.mover(input);
                tabuleiro.exibirTabuleiro();
                if (robo1.encontrouAlimento(alimento)) {
                    System.out.println("Parabens! O robo " + robo1.getCor() + " encontrou o alimento!");
                    break;
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
