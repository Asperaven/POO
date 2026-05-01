package T1Classes;

public class CasaParada extends Casa {
    public CasaParada(int posicao) {
        super(posicao);
    }

    public void pararJogador(Jogador jogador) {
        jogador.setSuaVez(false); // O jogador perde a vez
        System.out.println(jogador.getNome() + " parou na casa " + getPosicao());
    }

}
