import T2Classes.*;
import java.util.Scanner;
import java.util.Random;

public class MainAutomaticoMemoriaEstrategia {
    // Cores em ANSI para exibição no console
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";
    
    public static void main(String[] args) throws Exception {
        System.out.println("Determine a posicao do alimento no eixo X.");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        System.out.println("Determine a posicao do alimento no eixo Y.");
        int y = scanner.nextInt();
        Alimento alimento = new Alimento(x, y);
        System.out.println("Determine a cor do robo 1.");
        String cor1 = scanner.next();
        RoboMemoria robo1;
        try {
            robo1 = new RoboMemoria(cor1);
        } catch (RoboCorInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
            scanner.close();
            return;
        }
        System.out.println("Determine a cor do robo 2.");
        String cor2 = scanner.next();
        RoboEstrategico robo2;
        try {
            robo2 = new RoboEstrategico(cor2);
        } catch (RoboCorInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
            scanner.close();
            return;
        }

        TabuleiroCartesianoMultiplo tabuleiro = new TabuleiroCartesianoMultiplo(robo1, robo2, alimento);
        robo1.setAlimento(alimento);
        robo2.setAlimento(alimento);
        System.out.println("\nAlimento posicionado em: (" + alimento.getX() + ", " + alimento.getY() + ")");
        System.out.println("Robo 1 (" + robo1.getCor() + ") e Robo 2 (" + robo2.getCor() + ") se movendo automaticamente para encontrar o alimento...\n");
        tabuleiro.exibirTabuleiro();
        
        Random random = new Random();
        int movimentosValidos1 = 0;
        int movimentosInvalidos1 = 0;
        int movimentosValidos2 = 0;
        int movimentosInvalidos2 = 0;
        
        String[] direcoes = {"", "up", "down", "right", "left"};
        Robo vencedor = null;
        int rodada = 0;
        
        while (vencedor == null && (!robo1.isEliminado() || !robo2.isEliminado())) {
            rodada++;
            
            // Alterna as vezes: rodadas ímpares são do Robo 1, rodadas pares são do Robo 2
            if (rodada % 2 == 1) {
                // Vez do Robo 1 (Memoria - usa String)
                System.out.println("RODADA " + String.format("%2d", rodada) + " - Vez do Robo 1 (" + robo1.getCor() + ")...");
                
                if (!robo1.isEliminado()) {
                    String[] direcoesPossiveis = {"up", "down", "right", "left"};
                    String direcao1 = direcoesPossiveis[random.nextInt(4)];
                    int[] tentativa1 = robo1.calcularProxima(random.nextInt(4) + 1);
                    try {
                        robo1.mover(direcao1);
                        movimentosValidos1++;
                        tabuleiro.setTentativaRobo1(null);
                        if (robo1.encontrouAlimento(alimento)) {
                            vencedor = robo1;
                        }
                    } catch (MovimentoInvalidoException e) {
                        movimentosInvalidos1++;
                        tabuleiro.setTentativaRobo1(tentativa1);
                        System.out.println(RED + "✗ Robo " + robo1.getCor() + " tentou se mover para " + direcao1 + " - INVALIDO" + RESET);
                    }
                } else {
                    System.out.println("Robo 1 (" + robo1.getCor() + ") foi eliminado e não pode se mover.");
                }
            } else {
                // Vez do Robo 2 (Estratégico - usa String)
                System.out.println("RODADA " + String.format("%2d", rodada) + " - Vez do Robo 2 (" + robo2.getCor() + ")...");
                
                if (!robo2.isEliminado()) {
                    String[] direcoesPossiveis = {"up", "down", "right", "left"};
                    String direcao2 = direcoesPossiveis[random.nextInt(4)];
                    int[] tentativa2 = robo2.calcularProxima(random.nextInt(4) + 1);
                    try {
                        robo2.mover(direcao2);
                        movimentosValidos2++;
                        tabuleiro.setTentativaRobo2(null);
                        if (robo2.encontrouAlimento(alimento)) {
                            vencedor = robo2;
                        }
                    } catch (MovimentoInvalidoException e) {
                        movimentosInvalidos2++;
                        tabuleiro.setTentativaRobo2(tentativa2);
                        System.out.println(RED + "✗ Robo " + robo2.getCor() + " tentou se mover para " + direcao2 + " - INVALIDO" + RESET);
                    }
                } else {
                    System.out.println("Robo 2 (" + robo2.getCor() + ") foi eliminado e não pode se mover.");
                }
            }
            
            tabuleiro.exibirTabuleiro();
            
            // Pausa de 500ms para melhor legibilidade
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Exibir resultados
        System.out.println("\n════════════════════════════════════════");
        System.out.println("           RESULTADO FINAL              ");
        System.out.println("════════════════════════════════════════\n");
        
        if (vencedor != null) {
            System.out.println("Robo " + vencedor.getCor() + " encontrou o alimento!\n");
        } else {
            System.out.println("Nenhum robô encontrou o alimento. Todos foram eliminados ou o jogo terminou.\n");
        }
        
        System.out.println("Estatísticas:\n");
        System.out.println("Numero de rodadas: " + rodada + "\n");
        System.out.println("Robo 1 (" + robo1.getCor() + "):");
        System.out.println("  - Movimentos válidos: " + movimentosValidos1);
        System.out.println("  - Movimentos inválidos: " + movimentosInvalidos1);
        System.out.println("  - Status: " + (robo1.isEliminado() ? "ELIMINADO" : "ATIVO"));
        System.out.println();
        System.out.println("Robo 2 (" + robo2.getCor() + "):");
        System.out.println("  - Movimentos válidos: " + movimentosValidos2);
        System.out.println("  - Movimentos inválidos: " + movimentosInvalidos2);
        System.out.println("  - Status: " + (robo2.isEliminado() ? "ELIMINADO" : "ATIVO"));
        
        scanner.close();
    }
}
