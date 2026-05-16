package T1Classes;

public class CasaRetrocede extends Casa {
    public CasaRetrocede(int posicao) {
        super(posicao);
    }

    @Override
    public void acao(ContextoExecucao contexto) {
        super.acao(contexto);
        System.out.println("Casa Retrocede! Voce retrocedeu para o inicio do tabuleiro!");
        retrocederJogador(contexto.getJogador());
    }

    public void retrocederJogador(Jogador jogador) {
        jogador.setPosicao(0);
    }

}
