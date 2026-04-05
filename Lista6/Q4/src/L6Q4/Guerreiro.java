package L6Q4;

public class Guerreiro extends Personagem {
    private int defesa = 3;

    public Guerreiro(String nome) {
        super(100, nome, 20);
    }

    @Override
    public void receberDano(int dano) {
        dano -= this.defesa;
        if (dano < 0) {
            dano = 0;
        }
        super.receberDano(dano);
    }
}
