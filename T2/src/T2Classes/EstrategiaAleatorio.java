package T2Classes;

import java.util.Random;

public class EstrategiaAleatorio implements EstrategiaMovimento {

    @Override
    public void executar(Robo robo, Alimento alimento) throws MovimentoInvalidoException {
        String[] direcoes = {"up", "down", "left", "right"};
        robo.mover(direcoes[new Random().nextInt(4)]);
    }

}
