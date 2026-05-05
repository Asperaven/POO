import T2Classes.*;
import java.util.Scanner;

public class MainRoboMutavel {
    public static void main(String[] args) {
        System.out.println("Digite a posicao do alimento no eixo X:");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println("Digite a posicao do alimento no eixo Y:");
        int y = sc.nextInt();
        Alimento alimento = new Alimento(x, y);
        System.out.println("Digite a cor do robo:");
        String cor = sc.next();
        Robo robo;
        try {
            robo = new Robo(cor);
        } catch (RoboCorInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
            sc.close();
            return;
        }
        System.out.println("Gostaria de adicionar um obstaculo? 0 para nao, 1 para sim.");
        int adicionarObstaculo = sc.nextInt();
        TabuleiroCartesianoIndividual tabuleiro = new TabuleiroCartesianoIndividual(robo, alimento);
        if (adicionarObstaculo == 1) {
            int id = 0;
            System.out.println("Digite a posicao do obstaculo no eixo X:");
            int xObstaculo = sc.nextInt();
            System.out.println("Digite a posicao do obstaculo no eixo Y:");
            int yObstaculo = sc.nextInt();
            System.out.println("Digite o tipo de obstaculo (1 para Bomba, 2 para Rocha):");
            int tipoObstaculo = sc.nextInt();
            Obstaculo obstaculo;
            switch (tipoObstaculo) {
                case 1:
                    obstaculo = new Bomba(id, xObstaculo, yObstaculo);
                    id++;
                    break;
                case 2:
                    obstaculo = new Rocha(id, xObstaculo, yObstaculo);
                    id++;
                    break;
                default:
                    System.out.println("Tipo de obstaculo invalido.");
                    return;
            }
            tabuleiro.adicionarObstaculo(obstaculo);
        }
        System.out.println("\nAlimento posicionado em: (" + alimento.getX() + ", " + alimento.getY() + ")");
        System.out.println("Robo (" + robo.getCor() + ") pronto para se mover.\n");
        
        robo.setAlimento(alimento); 
        
        int contadorMovimentos = 0;
        int movimentosComEstrategia = 0;
        boolean encontrouAlimento = false;
        
        while (!encontrouAlimento && !robo.isEliminado()) {
            if (movimentosComEstrategia == 0) {
                EstrategiaMovimento estrategia = selecionarEstrategia(sc);
                robo.setEstrategia(estrategia);
                System.out.println("\nEstrategia definida. Executando 5 movimentos...\n");
            }
            
            try {
                robo.moverComEstrategia(alimento);
                contadorMovimentos++;
                movimentosComEstrategia++;
                tabuleiro.exibirTabuleiro();
                
                if (robo.encontrouAlimento(alimento)) {
                    encontrouAlimento = true;
                    System.out.println("\nRobo " + robo.getCor() + " encontrou o alimento em " + contadorMovimentos + " movimentos!");
                }
                
                if (movimentosComEstrategia >= 5) {
                    movimentosComEstrategia = 0;
                }
                
                // Pausa para visualização
                Thread.sleep(500);
            } catch (MovimentoInvalidoException e) {
                System.out.println("Movimento invalido: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        if (robo.isEliminado()) {
            System.out.println("\nRobo foi eliminado!");
        }
        System.out.println("\nTotal de movimentos: " + contadorMovimentos);
        sc.close();
    }
    
    private static EstrategiaMovimento selecionarEstrategia(Scanner sc) {
        System.out.println("Escolha a estrategia de movimento:");
        System.out.println("1 - Aleatorio");
        System.out.println("2 - Inteligente");
        System.out.println("3 - Memoria");
        System.out.println("4 - Estrategico");
        System.out.print("Opcao: ");
        
        int opcao = sc.nextInt();
        
        switch (opcao) {
            case 1:
                return new EstrategiaAleatorio();
            case 2:
                return new EstrategiaInteligente();
            case 3:
                return new EstrategiaMemoria();
            case 4:
                return new EstrategiaEstrategico();
            default:
                System.out.println("Opcao invalida. Usando Aleatorio como padrao.");
                return new EstrategiaAleatorio();
        }
    }
}
