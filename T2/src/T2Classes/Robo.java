package T2Classes;

import java.util.*;

public class Robo {
    private int x;
    private int y;
    private String cor;
    private boolean isEliminado;
    private List<Obstaculo> obstaculos;
    private List<Obstaculo> tabuleiroObstaculos; // Referência à lista de obstáculos do tabuleiro
    private EstrategiaMovimento estrategia; // Estratégia de movimento do robô
    protected Alimento alimento; // Referência ao alimento para estratégias que precisam
    
    private static final String[] CORES_VALIDAS = {"vermelho", "verde", "azul", "amarelo", "roxo", "rosa"};

    public Robo(String cor) throws RoboCorInvalidaException {
        this.x = 0;
        this.y = 0;
        validarCor(cor);
        this.cor = cor;
        this.isEliminado = false;
        this.obstaculos = new ArrayList<>();
        this.estrategia = null;
    }
    
    private void validarCor(String cor) throws RoboCorInvalidaException {
        for (String corValida : CORES_VALIDAS) {
            if (corValida.equalsIgnoreCase(cor)) {
                return;
            }
        }
        throw new RoboCorInvalidaException("Cor inválida: " + cor + ". Cores válidas: vermelho, verde, azul, amarelo, roxo, rosa");
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isEliminado() {
        return isEliminado;
    }

    public void eliminar() {
        this.isEliminado = true;
    }

    public void adicionarObstaculo(Obstaculo obstaculo) {
        obstaculos.add(obstaculo);
    }

    // Recebe a referência à lista de obstáculos do tabuleiro para que o robô possa remover bombas detonadas completamente
    public void settabuleiroObstaculos(List<Obstaculo> tabuleiroObstaculos) {
        this.tabuleiroObstaculos = tabuleiroObstaculos;
    }

    public void setEstrategia(EstrategiaMovimento estrategia) {
        this.estrategia = estrategia;
    }

    public void moverComEstrategia(Alimento alimento) throws MovimentoInvalidoException {
        if (estrategia == null) {
            throw new MovimentoInvalidoException("Nenhuma estrategia foi definida para o robo.");
        }
        estrategia.executar(this, alimento);
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    private Obstaculo verificarObstaculoEm(int posX, int posY) {
        for (Obstaculo obstaculo : obstaculos) {
            if (obstaculo.getX() == posX && obstaculo.getY() == posY) {
                return obstaculo;
            }
        }
        return null;
    }

    protected void verificarObstaculoAposMover(int anteriorX, int anteriorY) throws MovimentoInvalidoException {
        Obstaculo obstaculo = verificarObstaculoEm(x, y);
        
        if (obstaculo == null) {
            return; // Sem obstáculo, movimento é válido
        }
        
        if (obstaculo instanceof Rocha) {
            // Reverte o movimento do robô
            x = anteriorX;
            y = anteriorY;
            obstaculo.bater(); // Bateu na rocha, movimento inválido
        } else if (obstaculo instanceof Bomba) {
            // Entrou em contato com a bomba
            Bomba bomba = (Bomba) obstaculo;
            if (!bomba.jáExplodiu()) {
                // Bomba explode e elimina o robô
                bomba.bater();
                System.out.println("Robo " + cor + " foi eliminado pela bomba " + bomba.getId() + "!");
                this.eliminar();
                // Remove a bomba do tabuleiro e da lista local - completamente removida do jogo
                obstaculos.remove(bomba);
                if (tabuleiroObstaculos != null) {
                    tabuleiroObstaculos.remove(bomba);
                }
            }
        }
    }

    public void mover(String direcao) throws MovimentoInvalidoException {
        try {
            int direcaoInt = Integer.parseInt(direcao);
            mover(direcaoInt);
            return;
        } catch (NumberFormatException e) {
        }
        
        int anteriorX = x;
        int anteriorY = y;
        
        switch (direcao.toLowerCase()) {
            case "up":
                if (y + 1 < 0 || y + 1 > 4 || x < 0 || x > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para cima do plano cartesiano.");
                }
                y++;
                System.out.println("Robo " + cor + " se moveu para cima. Posicao atual: (" + x + ", " + y + ")");
                break;
            case "down":
                if (y - 1 < 0 || y - 1 > 4 || x < 0 || x > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para baixo do plano cartesiano.");
                }
                y--;
                System.out.println("Robo " + cor + " se moveu para baixo. Posicao atual: (" + x + ", " + y + ")");
                break;
            case "right":
                if (x + 1 < 0 || x + 1 > 4 || y < 0 || y > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para a direita do plano cartesiano.");
                }
                x++;
                System.out.println("Robo " + cor + " se moveu para a direita. Posicao atual: (" + x + ", " + y + ")");
                break;
            case "left":
                if (x - 1 < 0 || x - 1 > 4 || y < 0 || y > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para a esquerda do plano cartesiano.");
                }
                x--;
                System.out.println("Robo " + cor + " se moveu para a esquerda. Posicao atual: (" + x + ", " + y + ")");
                break;
            default:
                throw new MovimentoInvalidoException("Direcao invalida: " + direcao);
        }
        
        verificarObstaculoAposMover(anteriorX, anteriorY);
    }

    public void mover(int direcao) throws MovimentoInvalidoException {
        int anteriorX = x;
        int anteriorY = y;
        
        switch (direcao) {
            case 1: // up
                if (y + 1 < 0 || y + 1 > 4 || x < 0 || x > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para cima do plano cartesiano.");
                }
                y++;
                System.out.println("Robo " + cor + " se moveu para cima. Posicao atual: (" + x + ", " + y + ")");
                break;
            case 2: // down
                if (y - 1 < 0 || y - 1 > 4 || x < 0 || x > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para baixo do plano cartesiano.");
                }
                y--;
                System.out.println("Robo " + cor + " se moveu para baixo. Posicao atual: (" + x + ", " + y + ")");
                break;
            case 3: // right
                if (x + 1 < 0 || x + 1 > 4 || y < 0 || y > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para a direita do plano cartesiano.");
                }
                x++;
                System.out.println("Robo " + cor + " se moveu para a direita. Posicao atual: (" + x + ", " + y + ")");
                break;
            case 4: // left
                if (x - 1 < 0 || x - 1 > 4 || y < 0 || y > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para a esquerda do plano cartesiano.");
                }
                x--;
                System.out.println("Robo " + cor + " se moveu para a esquerda. Posicao atual: (" + x + ", " + y + ")");
                break;
            default:
                throw new MovimentoInvalidoException("Direcao invalida: " + direcao);
        }
        
        verificarObstaculoAposMover(anteriorX, anteriorY);
    }

    public boolean encontrouAlimento(Alimento alimento) {
        if (this.x == alimento.getX() && this.y == alimento.getY()) {
            return true;
        }
        return false;
    }
    
    // Calcula a próxima posição sem realmente se mover
    public int[] calcularProxima(int direcao) {
        int novoX = x;
        int novoY = y;
        
        switch (direcao) {
            case 1: // up
                novoY++;
                break;
            case 2: // down
                novoY--;
                break;
            case 3: // right
                novoX++;
                break;
            case 4: // left
                novoX--;
                break;
        }
        
        return new int[]{novoX, novoY};
    }

}
