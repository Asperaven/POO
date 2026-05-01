package T1Classes;

public class CasaMagica extends Casa {
    public CasaMagica(int posicao) {
        super(posicao);
    }

    public void trocarPosicao(Jogador[] jogador) {
        for (int i = 0; i < jogador.length; i++) {
            jogadorNaCasa = jogador[i];
            int posicaoMinima = 0;
            for (int j = 0; j< jogador.length; j++) {
                if (jogador[j].getPosicao() < jogador[posicaoMinima].getPosicao()) {
                    posicaoMinima = j;
                }
            }
        }

        ultimoJogador = jogador[posicaoMinima];
        if (i != posicaoMinima) {
            int posicaoTemp = jogador[i].getPosicao();
            jogador[i].setPosicao(jogador[posicaoMinima].getPosicao());
            jogador[posicaoMinima].setPosicao(posicaoTemp);
            System.out.println(jogador[i].getNome() + " trocou de posição com " + jogador[posicaoMinima].getNome());
        } else {
            System.out.println(jogador[i].getNome() + " está no ultimo lugar e não pode trocar de posição.");
        }

    }

}
