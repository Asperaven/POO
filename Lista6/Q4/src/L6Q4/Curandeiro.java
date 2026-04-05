package L6Q4;

public class Curandeiro extends Personagem {

    public Curandeiro(String nome) {
        super(25, nome, 0);
    }

    public void curar(Personagem alvo) {
        if (alvo.getVida() <= 0) {
            alvo.receberDano(-alvo.getVida() / 2);
        } else {
            alvo.receberDano(-25);
        }
    }

}
