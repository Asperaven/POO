package T2Classes;

public class EstrategiaMemoria implements EstrategiaMovimento {
    private int[] memoriaX;
    private int[] memoriaY;
    private int memoriaIndex;
    private boolean jaVisitado(int x, int y) {
        for (int i = 0; i < memoriaX.length; i++) {
            if (memoriaX[i] == x && memoriaY[i] == y) {
                return true;
            }
        }
        return false;
    }

    public EstrategiaMemoria() {
        this.memoriaX = new int[10];
        this.memoriaY = new int[10];
        this.memoriaIndex = 0;
    }

    @Override 
    public void executar(Robo robo, Alimento alimento) throws MovimentoInvalidoException {
        String[] direcoes = {"up", "down", "left", "right"};
        String direcao = direcoes[new java.util.Random().nextInt(4)];
        
        int direcaoInt = converterDirParaInt(direcao);
        int[] proximaPosicao = robo.calcularProxima(direcaoInt);
        int proximoX = proximaPosicao[0];
        int proximoY = proximaPosicao[1];
        
        if (jaVisitado(proximoX, proximoY)) {
            throw new MovimentoInvalidoException("Movimento invalido: o robo " + robo.getCor() + " ja visitou a posicao (" + proximoX + ", " + proximoY + "). O robo nao pode se mover para essa posicao.");
        }
        
        memoriaX[memoriaIndex] = proximoX;
        memoriaY[memoriaIndex] = proximoY;
        memoriaIndex = (memoriaIndex + 1) % memoriaX.length;

        robo.mover(direcao);
    }

    private int converterDirParaInt(String direcao) throws MovimentoInvalidoException {
        switch (direcao.toLowerCase()) {
            case "up":
                return 1;
            case "down":
                return 2;
            case "right":
                return 3;
            case "left":
                return 4;
            default:
                throw new MovimentoInvalidoException("Direcao invalida: " + direcao);
        }
    }
}
