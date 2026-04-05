package L6Q4;
import java.util.ArrayList;

public class Jogo {
    private Monstro monstro;
    private ArrayList<Personagem> personagens;
    private int ataqueEspecial = 3;

    public Jogo(Monstro monstro) {
        this.monstro = monstro;
        this.personagens = new ArrayList<>();
    }

    public void adicionarJogador(Personagem personagem) {
        if (this.personagens.size() < 4) {
            this.personagens.add(personagem);
        } else {
            System.out.println("Limite de personagens atingido.");
        }
    }

    public void removerJogador(Personagem personagem) {
        this.personagens.remove(personagem);
    }

    public void gameOver() {
        if (this.monstro.getVida() <= 0) {
            System.out.println("Heróis venceram!");
        } else if (this.personagens.stream().allMatch(p -> p.getVida() <= 0)) {
            System.out.println("Monstro venceu!");
        }
    }

    public void jogar() {
        while (this.monstro.getVida() > 0 && this.personagens.stream().anyMatch(p -> p.getVida() > 0)) {
            // Heróis atacam o monstro
            for (Personagem personagem : this.personagens) {
                if (personagem.getVida() > 0) {
                    personagem.atacar(this.monstro);
                }
            }

            // Monstro ataca um herói aleatório
            if (this.ataqueEspecial == 0) {
                System.out.println("Monstro usa ataque especial!");
                this.monstro.ataqueEmArea(this.personagens);
                this.ataqueEspecial = 3;
            } else {
                Personagem alvo = this.personagens.stream().filter(p -> p.getVida() > 0).findAny().orElse(null);
                if (alvo != null) {
                    this.monstro.atacar(alvo);
                }
                this.ataqueEspecial--;
            }

            // Curandeiro cura um herói aleatório
            for (Personagem personagem : this.personagens) {
                if (personagem instanceof Curandeiro && personagem.getVida() > 0) {
                    Curandeiro curandeiro = (Curandeiro) personagem;
                    Personagem alvo = this.personagens.stream().filter(p -> p.getVida() > 0).findAny().orElse(null);
                    if (alvo != null) {
                        curandeiro.curar(alvo);
                    }
                }
            }

            // Atraso para visualização
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        gameOver();
    }

    public Monstro getMonstro() {
        return monstro;
    }

    public void setMonstro(Monstro monstro) {
        this.monstro = monstro;
    }

    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(ArrayList<Personagem> personagens) {
        this.personagens = personagens;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(int ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }


}
