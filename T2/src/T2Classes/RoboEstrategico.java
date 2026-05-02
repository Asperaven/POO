public class RoboEstrategico extends Robo {
    public RoboEstrategico(String cor) throws RoboCorInvalidaException {
        super(cor);
    }

    @Override
    public void mover(String direcao) throws MovimentoInvalidoException {
        try {
            objetivoX = alimento.getX();
            objetivoY = alimento.getY();
            int deltaX = objetivoX - getX();
            int deltaY = objetivoY - getY();
            String direcaoPrioritaria;
            if (deltaX > 0) {
                direcaoPrioritaria = "right";
            } else if (deltaX < 0) {
                direcaoPrioritaria = "left";
            } else if (deltaY > 0) {
                direcaoPrioritaria = "up";
            } else if (deltaY < 0) {
                direcaoPrioritaria = "down";
            } else {
                return;
            }
            super.mover(direcaoPrioritaria);
        } catch (MovimentoInvalidoException e) {
            throw new MovimentoInvalidoException("Direcao invalida: " + direcao);
        }
    }

}
