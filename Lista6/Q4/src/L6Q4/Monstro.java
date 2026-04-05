package L6Q4;
import java.util.ArrayList;

public class Monstro extends Personagem {
    public Monstro(String nome) {
        super(400, nome, 25);
    }

    public void ataqueEmArea(ArrayList<Personagem> alvos) {
        for (Personagem alvo : alvos) {
            this.atacar(alvo);
        }
    }

}
