package T2Classes;

public class Rocha extends Obstaculo {
    public Rocha(int id, int x, int y) {
        super(id, x, y);
    }

    @Override
    public void bater() throws MovimentoInvalidoException {
        throw new MovimentoInvalidoException("Movimento invalido: o robo nao pode se mover para a posicao da rocha " + getId() + ".");
    }

}
