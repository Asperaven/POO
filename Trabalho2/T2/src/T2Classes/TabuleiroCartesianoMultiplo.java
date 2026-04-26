package T2Classes;

import java.util.*;

public class TabuleiroCartesianoMultiplo {
    private Robo robo1;
    private Robo robo2;
    private Alimento alimento;
    private List<Obstaculo> obstaculos;
    private int[] tentadaRobo1 = null; // posição tentada do robo 1
    private int[] tentadaRobo2 = null; // posição tentada do robo 2
    private static final int TAMANHO_TABULEIRO = 5;
    
    // Cores em ANSI para exibição no console
    private static final String RESET = "\u001B[0m";
    private static final String GRAY = "\u001B[90m";
    private static final String WHITE = "\u001B[97m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";
    private static final String PURPLE = "\u001B[35m";
    private static final String LIGHT_MAGENTA = "\u001B[95m";
    
    public TabuleiroCartesianoMultiplo(Robo robo1, Robo robo2, Alimento alimento) {
        this.robo1 = robo1;
        this.robo2 = robo2;
        this.alimento = alimento;
        this.obstaculos = new ArrayList<>();
    }
    
    // Adiciona um obstáculo ao tabuleiro e registra com ambos os robôs
    public void adicionarObstaculo(Obstaculo obstaculo) {
        obstaculos.add(obstaculo);
        robo1.adicionarObstaculo(obstaculo);
        robo2.adicionarObstaculo(obstaculo);
    }
    
    // Fornece acesso à lista de obstáculos para que os robôs possam remover completamente as bombas detonadas
    public List<Obstaculo> getObstaculos() {
        return obstaculos;
    }
    
    public void setTentativaRobo1(int[] posicao) {
        this.tentadaRobo1 = posicao;
    }
    
    public void setTentativaRobo2(int[] posicao) {
        this.tentadaRobo2 = posicao;
    }
    
    public void limparTentativas() {
        this.tentadaRobo1 = null;
        this.tentadaRobo2 = null;
    }
    
    public void exibirTabuleiro() {
        System.out.println("\n" + criarTabuleiro() + "\n");
    }
    
    private String criarTabuleiro() {
        StringBuilder sb = new StringBuilder();
        
        // Título
        sb.append("════════════════════════════════════════════════════════════════════\n");
        sb.append("  Robo 1 (" + String.format("%-8s", robo1.getCor()) + ")      │      Robo 2 (" + String.format("%-8s", robo2.getCor()) + ")  \n");
        sb.append("════════════════════════════════════════════════════════════════════\n\n");
        
        // Matriz com dois tabuleiros lado a lado (Y vai de 4 até 0, para exibir de cima para baixo)
        for (int y = TAMANHO_TABULEIRO - 1; y >= 0; y--) {
            // Tabuleiro do Robo 1
            for (int x = 0; x < TAMANHO_TABULEIRO; x++) {
                sb.append(obterCelula(robo1, tentadaRobo1, x, y));
            }
            sb.append(" ");
            
            // Espaçamento entre os tabuleiros
            sb.append("    ");
            
            // Tabuleiro do Robo 2
            for (int x = 0; x < TAMANHO_TABULEIRO; x++) {
                sb.append(obterCelula(robo2, tentadaRobo2, x, y));
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    private String obterCelula(Robo robo, int[] tentada, int x, int y) {
        if (robo.getX() == x && robo.getY() == y) {
            // Célula com o robô
            // Se há uma tentativa de movimento, mostra com símbolo diferente
            if (tentada != null && (tentada[0] != robo.getX() || tentada[1] != robo.getY())) {
                // Robo tentou se mover mas falhou - marca com !
                return obterCorRobo(robo) + "| ! |" + RESET;
            } else {
                // Robo está parado normalmente
                return obterCorRobo(robo) + "| * |" + RESET;
            }
        }
        
        // Verificar se há um obstáculo nesta posição
        Obstaculo obstaculo = verificarObstaculoEm(x, y);
        if (obstaculo != null) {
            if (obstaculo instanceof Bomba) {
                return WHITE + "| & |" + RESET;
            } else if (obstaculo instanceof Rocha) {
                return WHITE + "| O |" + RESET;
            }
        }
        
        if (alimento.getX() == x && alimento.getY() == y) {
            // Célula com alimento (mesmo em ambos os tabuleiros)
            return WHITE + "| @ |" + RESET;
        } else {
            // Célula vazia
            return GRAY + "| # |" + RESET;
        }
    }
    
    // Encontra um obstáculo nas coordenadas fornecidas
    private Obstaculo verificarObstaculoEm(int posX, int posY) {
        for (Obstaculo obstaculo : obstaculos) {
            if (obstaculo.getX() == posX && obstaculo.getY() == posY) {
                return obstaculo;
            }
        }
        return null;
    }
    
    private String obterCorRobo(Robo robo) {
        String cor = robo.getCor().toLowerCase();
        switch (cor) {
            case "vermelho":
                return RED;
            case "verde":
                return GREEN;
            case "azul":
                return BLUE;
            case "amarelo":
                return YELLOW;
            case "roxo":
                return PURPLE;
            case "rosa":
                return LIGHT_MAGENTA;
            default:
                return WHITE; 
        }
    }
}
