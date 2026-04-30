package T1Classes;
import java.util.Random;

public class Jogador {
    private String cor;
    private String nome;
    private int posicao;
    private boolean suaVez = false;
    private boolean sortudo = false;
    private boolean azarado = false;

    public Jogador(String cor, String nome, int posicao) {
        this.cor = cor;
        this.nome = nome;
        this.posicao = posicao;
    }

    public boolean isSuaVez() {
        return suaVez;
    }

    public void setSuaVez(boolean suaVez) {
        this.suaVez = suaVez;
    }

    public boolean isSortudo() {
        return sortudo;
    }

    public void setSortudo(boolean sortudo) {
        this.sortudo = sortudo;
    }

    public boolean isAzarado() {
        return azarado;
    }

    public void setAzarado(boolean azarado) {
        this.azarado = azarado;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getPosicao() {
        return posicao;
    }

    public boolean mover(boolean suaVez) {
        if (!suaVez) return false;
        
        Random rand = new Random();
        int dado1, dado2, total;
        
        do {
            dado1 = rand.nextInt(6) + 1;
            dado2 = rand.nextInt(6) + 1;
            total = dado1 + dado2;
        } while ((sortudo && total < 7) || (azarado && total > 6));
        
        posicao += total;

        return dado1 == dado2;
    }
}
