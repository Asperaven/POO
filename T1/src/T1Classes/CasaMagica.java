package T1Classes;

public class CasaMagica extends Casa {
    public CasaMagica(int posicao) {
        super(posicao);
    }

    @Override
    public void acao(ContextoExecucao contexto) {
        super.acao(contexto);
        trocarPosicao(contexto.getJogadores());
    }

    public void trocarPosicao(Jogador[] jogador) {
        int i;
        int posicaoMinima = 0;
        Jogador jogadorNaCasa = null;
        for (i = 0; i < jogador.length; i++) {
            jogadorNaCasa = jogador[i];
            posicaoMinima = 0;
            for (int j = 0; j< jogador.length; j++) {
                if (jogador[j].getPosicao() < jogador[posicaoMinima].getPosicao()) {
                    posicaoMinima = j;
                }
            }
        }

        int indiceJogadorNaCasa = i - 1;
        Jogador ultimoJogador = jogador[posicaoMinima];
        if (indiceJogadorNaCasa != posicaoMinima) {
            int posicaoTemp = jogador[indiceJogadorNaCasa].getPosicao();
            jogador[indiceJogadorNaCasa].setPosicao(jogador[posicaoMinima].getPosicao());
            jogador[posicaoMinima].setPosicao(posicaoTemp);
            System.out.println(jogadorNaCasa.getNome() + " trocou de posição com " + jogador[posicaoMinima].getNome());
        } else {
            System.out.println(jogadorNaCasa.getNome() + " está no ultimo lugar e não pode trocar de posição.");
        }

    }

}
