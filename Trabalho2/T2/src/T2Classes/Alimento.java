package T2Classes;

public class Alimento {
    private int x;
    private int y;

    public Alimento(int x, int y) {
        if (x < 0 || x > 4 || y < 0 || y > 4) {
            throw new IllegalArgumentException("Posicao invalida: o alimento deve estar dentro do plano cartesiano de 5x5.");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 0 || x > 4) {
            throw new IllegalArgumentException("Posicao invalida: o alimento deve estar dentro do plano cartesiano de 5x5.");
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < 0 || y > 4) {
            throw new IllegalArgumentException("Posicao invalida: o alimento deve estar dentro do plano cartesiano de 5x5.");
        }
        this.y = y;
    }

}
