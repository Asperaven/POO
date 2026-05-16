package T1Classes;

public class CasaMagica extends Casa {
    public CasaMagica(int posicao) {
        super(posicao);
    }

    @Override
    public void acao(ContextoExecucao contexto) {
        super.acao(contexto);
        System.out.println("Casa magica! Trocou de posicao com o jogador mais atrasado!");
        trocarPosicao(contexto.getJogador(), contexto.getJogadores());
    }

    public void trocarPosicao(Jogador jogadorEmCasa, Jogador[] jogadores) {
        int posicaoMinima = 0;
        
        // Find the player with minimum position
        for (int j = 0; j < jogadores.length; j++) {
            if (jogadores[j].getPosicao() < jogadores[posicaoMinima].getPosicao()) {
                posicaoMinima = j;
            }
        }

        Jogador ultimoJogador = jogadores[posicaoMinima];
        if (jogadorEmCasa != ultimoJogador) {
            int posicaoTemp = jogadorEmCasa.getPosicao();
            jogadorEmCasa.setPosicao(ultimoJogador.getPosicao());
            ultimoJogador.setPosicao(posicaoTemp);
            System.out.println(jogadorEmCasa.getNome() + " trocou de posicao com " + ultimoJogador.getNome());
        } else {
            System.out.println(jogadorEmCasa.getNome() + " esta no ultimo lugar e nao pode trocar de posicao.");
        }
    }

}
