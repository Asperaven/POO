package T1Classes;

public class ContextoExecucao {
    private Jogador jogador;
    private Jogador[] jogadores;
    private int escolha;

    public ContextoExecucao(Jogador jogador) {
        this.jogador = jogador;
    }

    public ContextoExecucao(Jogador[] jogadores) {
        this.jogadores = jogadores;
    }

    public ContextoExecucao(Jogador jogador, Jogador[] jogadores, int escolha) {
        this.jogador = jogador;
        this.jogadores = jogadores;
        this.escolha = escolha;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Jogador[] getJogadores() {
        return jogadores;
    }

    public int getEscolha() {
        return escolha;
    }

}
