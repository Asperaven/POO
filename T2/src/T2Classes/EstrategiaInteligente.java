package T2Classes;
import java.util.*;

public class EstrategiaInteligente implements EstrategiaMovimento {
    private Set<String> movimentosExaustos;
    private int ultimoX;
    private int ultimoY;
    private int tentativasInvalidas;

    public EstrategiaInteligente() {
        this.movimentosExaustos = new HashSet<>();
        this.ultimoX = -1;
        this.ultimoY = -1;
        this.tentativasInvalidas = 0;
    }

    @Override
    public void executar(Robo robo, Alimento alimento) throws MovimentoInvalidoException {
        tentativasInvalidas = 0;

        if (robo.getX() != ultimoX || robo.getY() != ultimoY) {
            movimentosExaustos.clear();
            ultimoX = robo.getX();
            ultimoY = robo.getY();
        }

        String[] todasDirecoes = {"up", "down", "left", "right"};
        String direcaoInicial = todasDirecoes[new Random().nextInt(4)];

        try {
            tentarMover(robo, direcaoInicial);
            return;
        } catch (MovimentoInvalidoException e) {
            tentativasInvalidas++;
            movimentosExaustos.add(direcaoInicial);
        }

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
                tentarMover(robo, direcaoTentativa);
                return;
            } catch (MovimentoInvalidoException e) {
                tentativasInvalidas++;
                movimentosExaustos.add(direcaoTentativa);
                direcoesDisponiveis.remove(direcaoTentativa);
            }
        }
        throw new MovimentoInvalidoException("Todos os movimentos possíveis foram exaustos para o robo " + robo.getCor() + ". O robo nao pode se mover.");
    }

    public int getTentativasInvalidas() {
        return tentativasInvalidas;
    }

    public void tentarMover(Robo robo, String direcao) throws MovimentoInvalidoException {
        robo.mover(direcao);
    }

}
