package T2Classes;

public class EstrategiaEstrategico implements EstrategiaMovimento {

    @Override 
    public void executar(Robo robo, Alimento alimento) throws MovimentoInvalidoException {
        int objetivoX = alimento.getX();
        int objetivoY = alimento.getY();
        int deltaX = objetivoX - robo.getX();
        int deltaY = objetivoY - robo.getY();
        
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
        
        robo.mover(direcaoPrioritaria);
    }

}
