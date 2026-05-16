package T1Classes;

public class CasaParada extends Casa {
    public CasaParada(int posicao) {
        super(posicao);
    }

    @Override
    public void acao(ContextoExecucao contexto) {
        super.acao(contexto);
        System.out.println("Casa Parada! Voce perdeu a vez!");
        pararJogador(contexto.getJogador());
    }

    public void pararJogador(Jogador jogador) {
        jogador.setSuaVez(false); // O jogador perde a vez
    }

}
