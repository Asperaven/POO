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
        TabuleiroCartesianoIndividual tabuleiro = new TabuleiroCartesianoIndividual(robo, alimento);
        robo.settabuleiroObstaculos(tabuleiro.getObstaculos());
        robo.setAlimento(alimento);
        System.out.println("\nAlimento posicionado em: (" + alimento.getX() + ", " + alimento.getY() + ")");
        System.out.println("Gostaria de adicionar um obstaculo? 0 para nao, 1 para sim.");
        int resposta = sc.nextInt();
        while (true) {
            if (resposta == 0) {
                break;
            } else if (resposta == 1) {
                int id = 0;
                System.out.println("Digite a posicao do obstaculo no eixo X:");
                int ox = sc.nextInt();
                System.out.println("Digite a posicao do obstaculo no eixo Y:");
                int oy = sc.nextInt();
                System.out.println("Digite o tipo do obstaculo (1 para rocha, 2 para bomba):");
                int tipo = sc.nextInt();
                Obstaculo obstaculo = null;
                switch (tipo) {
                    case 1:
                        obstaculo = new Rocha(id, ox, oy);
                        id++;
                        break;
                    case 2:
                        obstaculo = new Bomba(id, ox, oy);
                        id++;
                        break;
                    default:
                        System.out.println("Tipo de obstaculo invalido.");
                        continue;
                }
                robo.adicionarObstaculo(obstaculo);
                System.out.println("Obstaculo adicionado. Gostaria de adicionar outro? 0 para nao, 1 para sim.");
                resposta = sc.nextInt();
            } else {
                System.out.println("Resposta invalida. Digite 0 para nao ou 1 para sim.");
                resposta = sc.nextInt();
            }
        }
        
        System.out.println("\nRobo " + robo.getCor() + " se movendo para encontrar o alimento...\n");
        tabuleiro.exibirTabuleiro();
        
        int contadorMovimentos = 0;
        int movimentosComEstrategia = 0;
        boolean encontrouAlimento = false;
        String estrategiaAtual = "";
        
        while (!encontrouAlimento && !robo.isEliminado()) {
            if (movimentosComEstrategia == 0) {
                estrategiaAtual = selecionarEstrategia(sc);
                robo.setEstrategia(obterEstrategia(estrategiaAtual));
                System.out.println("\nEstrategia definida: " + estrategiaAtual + ". Executando 5 movimentos...\n");
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
            System.out.println("Estrategia utilizada no momento da eliminacao: " + estrategiaAtual);
        } else if (encontrouAlimento) {
            System.out.println("Estrategia utilizada na vitoria: " + estrategiaAtual);
        }
        System.out.println("\nTotal de movimentos: " + contadorMovimentos);
        sc.close();
    }
    
    private static String selecionarEstrategia(Scanner sc) {
        System.out.println("Escolha a estrategia de movimento:");
        System.out.println("1 - Aleatorio");
        System.out.println("2 - Inteligente");
        System.out.println("3 - Memoria");
        System.out.println("4 - Estrategico");
        System.out.print("Opcao: ");
        
        int opcao = sc.nextInt();
        
        switch (opcao) {
            case 1:
                return "Aleatorio";
            case 2:
                return "Inteligente";
            case 3:
                return "Memoria";
            case 4:
                return "Estrategico";
            default:
                System.out.println("Opcao invalida. Usando Aleatorio como padrao.");
                return "Aleatorio";
        }
    }

    private static EstrategiaMovimento obterEstrategia(String estrategia) {
        switch (estrategia) {
            case "Aleatorio":
                return new EstrategiaAleatorio();
            case "Inteligente":
                return new EstrategiaInteligente();
            case "Memoria":
                return new EstrategiaMemoria();
            case "Estrategico":
                return new EstrategiaEstrategico();
            default:
                return new EstrategiaAleatorio();
        }
    }
}
