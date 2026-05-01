package T2Classes;

import java.util.*;

public class RoboInteligente extends Robo {
    private Set<String> movimentosExaustos;
    private int ultimaX;
    private int ultimaY;
    private int tentativasInvalidas; 
    
    public RoboInteligente(String cor) throws RoboCorInvalidaException {
        super(cor);
        this.movimentosExaustos = new HashSet<>();
        this.ultimaX = getX();
        this.ultimaY = getY();
        this.tentativasInvalidas = 0;
    }

    @Override
    public void mover(String direcao) throws MovimentoInvalidoException {
        tentativasInvalidas = 0;

        if (getX() != ultimaX || getY() != ultimaY) {
            movimentosExaustos.clear();
            ultimaX = getX();
            ultimaY = getY();
        }

        try {
            tentarMover(direcao);
            return; 
        } catch (MovimentoInvalidoException e) {
            tentativasInvalidas++;
            movimentosExaustos.add(direcao);
        }

        String[] todasDirecoes = {"up", "down", "left", "right"};
        List<String> direcoesDisponiveis = new ArrayList<>();
        for (String dir : todasDirecoes) {
            if (!movimentosExaustos.contains(dir)) {
                direcoesDisponiveis.add(dir);
            }
        }

        while (!direcoesDisponiveis.isEmpty()) {
            Random random = new Random();
            String direcaoTentativa = direcoesDisponiveis.get(random.nextInt(direcoesDisponiveis.size()));

            try {
                tentarMover(direcaoTentativa);
                return;  
            } catch (MovimentoInvalidoException e) {
                tentativasInvalidas++;
                movimentosExaustos.add(direcaoTentativa);
                direcoesDisponiveis.remove(direcaoTentativa);
            }
        }
        throw new MovimentoInvalidoException("Todos os movimentos possíveis foram exaustos para o robo " + getCor() + ". O robo nao pode se mover.");
    }

    public int getTentativasInvalidas() {
        return tentativasInvalidas;
    }

    private void tentarMover(String direcao) throws MovimentoInvalidoException {
        int novaX = getX();
        int novaY = getY();
        String cor = getCor();
        int anteriorX = novaX;
        int anteriorY = novaY;

        switch (direcao) {
            case "up":
                if (novaY + 1 < 0 || novaY + 1 > 4 || novaX < 0 || novaX > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para cima do plano cartesiano.");
                }
                novaY++;
                System.out.println("Robo " + cor + " se moveu para cima. Posicao atual: (" + novaX + ", " + novaY + ")");
                break;
            case "down":
                if (novaY - 1 < 0 || novaY - 1 > 4 || novaX < 0 || novaX > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para baixo do plano cartesiano.");
                }
                novaY--;
                System.out.println("Robo " + cor + " se moveu para baixo. Posicao atual: (" + novaX + ", " + novaY + ")");
                break;
            case "right":
                if (novaX + 1 < 0 || novaX + 1 > 4 || novaY < 0 || novaY > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para a direita do plano cartesiano.");
                }
                novaX++;
                System.out.println("Robo " + cor + " se moveu para a direita. Posicao atual: (" + novaX + ", " + novaY + ")");
                break;
            case "left":
                if (novaX - 1 < 0 || novaX - 1 > 4 || novaY < 0 || novaY > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para a esquerda do plano cartesiano.");
                }
                novaX--;
                System.out.println("Robo " + cor + " se moveu para a esquerda. Posicao atual: (" + novaX + ", " + novaY + ")");
                break;
            default:
                throw new MovimentoInvalidoException("Direcao invalida: " + direcao);
        }

        setX(novaX);
        setY(novaY);
        verificarObstaculoAposMover(anteriorX, anteriorY);
    }

}
