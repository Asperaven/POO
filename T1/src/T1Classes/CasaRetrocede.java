package T1Classes;

public class CasaRetrocede {
    public CasaRetrocede(int posicao) {
        super(posicao);
    }

    public void retrocederJogador(Jogador jogador) {
        jogador.setPosicao(0);
        System.out.println(jogador.getNome() + " retrocedeu para o inicio do tabuleiro!");
    }

}
