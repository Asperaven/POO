package T1Classes;

public class Casa {
    private int posicao;

    public Casa(int posicao) {
        this.posicao = posicao;
    }

    public int getPosicao() {
        return posicao;
    }

    public void acao(ContextoExecucao contexto) {
        System.out.println("O jogador " + contexto.getJogador().getNome() + " alcancou a casa " + (getPosicao() + 1) + "!");
    }

}
