package T1Classes;
import java.util.Random;

public class Jogador {
    private String cor;
    private String nome;
    private int posicao;
    private boolean suaVez = false;
    private boolean sortudo = false;
    private boolean azarado = false;
    private int ultimaSomaDados = 0;
    private static final String[] CORES_VALIDAS = {"vermelho", "verde", "azul", "amarelo", "roxo", "rosa"};

    public Jogador(String cor, String nome, int posicao) throws JogadorCorInvalidaException {
        this.cor = cor;
        validarCor(cor);
        this.nome = nome;
        this.posicao = posicao;
    }

    private void validarCor(String cor) throws JogadorCorInvalidaException {
        for (String corValida : CORES_VALIDAS) {
            if (corValida.equalsIgnoreCase(cor)) {
                return;
            }
        }
        throw new JogadorCorInvalidaException("Cor invalida: " + cor + ". Cores válidas: vermelho, verde, azul, amarelo, roxo, rosa");
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
        
        this.ultimaSomaDados = total;
        posicao += total;

        return dado1 == dado2;
    }

    public int getUltimaSomaDados() {
        return ultimaSomaDados;
    }
}
