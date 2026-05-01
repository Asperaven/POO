package T2Classes;

public class Bomba extends Obstaculo {
    private boolean explodiu;
    
    public Bomba(int id, int x, int y) {
        super(id, x, y);
        this.explodiu = false;
    }

    @Override
    public void bater() throws MovimentoInvalidoException {
        explodir();
    }

    public void explodir() {
        if (!explodiu) {
            explodiu = true;
            System.out.println("A bomba " + getId() + " explodiu!");
        }
    }

    public boolean jáExplodiu() {
        return explodiu;
    }

}
