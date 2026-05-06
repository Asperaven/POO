package T2Classes;
import java.util.ArrayList;

public class TabuleiroCartesianoIndividual {
    private Robo robo;
    private Alimento alimento;
    private ArrayList<Obstaculo> obstaculos;
    private static final int TAMANHO_TABULEIRO = 5;
    
    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String GRAY = "\u001B[90m";
    private static final String WHITE = "\u001B[97m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";
    private static final String PURPLE = "\u001B[35m";
    private static final String LIGHT_MAGENTA = "\u001B[95m";
    
    public TabuleiroCartesianoIndividual(Robo robo, Alimento alimento) {
        this.robo = robo;
        this.alimento = alimento;
        this.obstaculos = new ArrayList<>();
    }

    public void adicionarObstaculo(Obstaculo obstaculo) {
        obstaculos.add(obstaculo);
        robo.adicionarObstaculo(obstaculo);
    }

    public ArrayList<Obstaculo> getObstaculos() {
        return obstaculos;
    }

    
    public void exibirTabuleiro() {
        System.out.println("\n" + criarTabuleiro() + "\n");
    }
    
    private String criarTabuleiro() {
        StringBuilder sb = new StringBuilder();
        
        // Título
        sb.append("╔═══════════════════════════════════╗\n");
        sb.append("║        TABULEIRO 5x5              ║\n");
        sb.append("╚═══════════════════════════════════╝\n\n");
        
        // Matriz (Y vai de 4 até 0, para exibir de cima para baixo)
        for (int y = TAMANHO_TABULEIRO - 1; y >= 0; y--) {
            for (int x = 0; x < TAMANHO_TABULEIRO; x++) {
                sb.append(obterCelula(x, y));
            }
            sb.append("\n");
        }

        sb.append("\nDigite a direcao para mover o robo (1/up, 2/down, 3/right, 4/left) ou 'sair' para encerrar:");
        return sb.toString();
    }
    
    private String obterCelula(int x, int y) {
        if (robo.getX() == x && robo.getY() == y) {
            // Célula com robô
            return obterCorRobo() + "| * |" + RESET;
        }

        Obstaculo obstaculo = verificarObstaculoEm(x, y);
        if (obstaculo != null) {
            if (obstaculo instanceof Bomba) {
                return WHITE + "| & |" + RESET;
            } else if (obstaculo instanceof Rocha) {
                return WHITE + "| O |" + RESET;
            }
        }

        if (alimento.getX() == x && alimento.getY() == y) {
            // Célula com alimento
            return WHITE + "| @ |" + RESET;
        } else {
            // Célula vazia
            return GRAY + "| # |" + RESET;
        }
    }

    private Obstaculo verificarObstaculoEm(int x, int y) {
        for (Obstaculo obstaculo : obstaculos) {
            if (obstaculo.getX() == x && obstaculo.getY() == y) {
                return obstaculo;
            }
        }
        return null;
    }
    
    private String obterCorRobo() {
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
