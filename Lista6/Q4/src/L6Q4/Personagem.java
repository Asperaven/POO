package L6Q4;
public class Personagem {
    private int vida;
    private String nome;
    private int ataque;

    public Personagem(int vida, String nome, int ataque) {
        this.vida = vida;
        this.nome = nome;
        this.ataque = ataque;
    }

    public void atacar(Personagem alvo) {
        if (this.vida > 0) {
            alvo.receberDano(this.ataque);
        }
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    public int getVida() {
        return this.vida;
    }

    public String getNome() {
        return this.nome;
    }

    public int getAtaque() {
        return this.ataque;
    }
}
