package L6Q4;

public class Mago extends Personagem {
    private int mana;

    public Mago(String nome) {
        super(50, nome, 30);
        this.mana = 30;
    }

    @Override
    public void atacar(Personagem alvo) {
        if (this.mana > 0) {
            this.mana -= 10;
            super.atacar(alvo);
        } else {
            System.out.println("Mana insuficiente para atacar. Regenerando mana...");
            this.regenerarMana();
        }
    }

    public void regenerarMana() {
        this.mana = 30;
    }
}
