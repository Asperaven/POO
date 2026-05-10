package T1Classes;

public class CasaRetrocede extends Casa {
    public CasaRetrocede(int posicao) {
        super(posicao);
    }

    @Override
    public void acao(ContextoExecucao contexto) {
        super.acao(contexto);
        retrocederJogador(contexto.getJogador());
    }

    public void retrocederJogador(Jogador jogador) {
        jogador.setPosicao(0);
        System.out.println(jogador.getNome() + " retrocedeu para o inicio do tabuleiro!");
    }

}
