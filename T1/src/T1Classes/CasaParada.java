package T1Classes;

public class CasaParada extends Casa {
    public CasaParada(int posicao) {
        super(posicao);
    }

    @Override
    public void acao(ContextoExecucao contexto) {
        super.acao(contexto);
        pararJogador(contexto.getJogador());
    }

    public void pararJogador(Jogador jogador) {
        jogador.setSuaVez(false); // O jogador perde a vez
        System.out.println(jogador.getNome() + " parou na casa " + getPosicao());
    }

}
