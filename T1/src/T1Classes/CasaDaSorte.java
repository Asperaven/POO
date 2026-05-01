package T1Classes;

public class CasaDaSorte extends Casa {
    public CasaDaSorte(int posicao) {
        super(posicao);
    }

    public void adiantarJogador(Jogador jogador) {
        int novaPosicao = jogador.getPosicao() + 3;
        jogador.setPosicao(novaPosicao);
        System.out.println(jogador.getNome() + " avançou para a casa " + novaPosicao);
    }

}
