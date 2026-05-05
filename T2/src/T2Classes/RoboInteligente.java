package T2Classes;
import java.util.*;

public class RoboInteligente extends Robo {
    private Set<String> movimentosExaustos;
    private int ultimoX;
    private int ultimoY;
    private int tentativasInvalidas; 
    
    public RoboInteligente(String cor) throws RoboCorInvalidaException {
        super(cor);
        this.movimentosExaustos = new HashSet<>();
        this.ultimoX = getX();
        this.ultimoY = getY();
        this.tentativasInvalidas = 0;
    }

    @Override
    public void mover(String direcao) throws MovimentoInvalidoException {
        tentativasInvalidas = 0;

        if (getX() != ultimoX || getY() != ultimoY) {
            movimentosExaustos.clear();
            ultimoX = getX();
            ultimoY = getY();
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
        int novoX = getX();
        int novoY = getY();
        String cor = getCor();
        int anteriorX = novoX;
        int anteriorY = novoY;

        switch (direcao) {
            case "up":
                if (novoY + 1 < 0 || novoY + 1 > 4 || novoX < 0 || novoX > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para cima do plano cartesiano.");
                }
                novoY++;
                System.out.println("Robo " + cor + " se moveu para cima. Posicao atual: (" + novoX + ", " + novoY + ")");
                break;
            case "down":
                if (novoY - 1 < 0 || novoY - 1 > 4 || novoX < 0 || novoX > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para baixo do plano cartesiano.");
                }
                novoY--;
                System.out.println("Robo " + cor + " se moveu para baixo. Posicao atual: (" + novoX + ", " + novoY + ")");
                break;
            case "right":
                if (novoX + 1 < 0 || novoX + 1 > 4 || novoY < 0 || novoY > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para a direita do plano cartesiano.");
                }
                novoX++;
                System.out.println("Robo " + cor + " se moveu para a direita. Posicao atual: (" + novoX + ", " + novoY + ")");
                break;
            case "left":
                if (novoX - 1 < 0 || novoX - 1 > 4 || novoY < 0 || novoY > 4) {
                    throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para a esquerda do plano cartesiano.");
                }
                novoX--;
                System.out.println("Robo " + cor + " se moveu para a esquerda. Posicao atual: (" + novoX + ", " + novoY + ")");
                break;
            default:
                throw new MovimentoInvalidoException("Direcao invalida: " + direcao);
        }

        setX(novoX);
        setY(novoY);
        verificarObstaculoAposMover(anteriorX, anteriorY);
    }

}
