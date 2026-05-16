package T1Classes;

public class CasaDaSorte extends Casa {
    public CasaDaSorte(int posicao) {
        super(posicao);
    }

    @Override
    public void acao(ContextoExecucao contexto) {
        super.acao(contexto);
        System.out.println("Casa da Sorte! Avance 3 casas!");
        adiantarJogador(contexto.getJogador());
    }

    public void adiantarJogador(Jogador jogador) {
        int novaPosicao = jogador.getPosicao() + 3;
        jogador.setPosicao(novaPosicao);
        System.out.println(jogador.getNome() + " avancou para a casa " + (novaPosicao + 1));
    }

}
