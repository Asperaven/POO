package T1Classes;

public class Jogo {
    private Jogador[] jogadores;
    private Tabuleiro tabuleiro;
    private int numCasas = 40;
    private int[] rodadas;
    private int rodada = 1;

    public Jogo(Jogador[] jogadores) {
        this.jogadores = jogadores;
        this.rodadas = new int[jogadores.length];
        this.tabuleiro = new Tabuleiro(numCasas);
    }

    public void jogar(Tabuleiro tabuleiro){

        boolean jogoRodando = true;

        while (jogoRodando) {
            System.out.println(rodada + " RODADA:\n");

            for (int i = 0; i < jogadores.length; i++) {
                Jogador jogadorAtual = jogadores[i];
                boolean continuarJogando = true;

                while (continuarJogando) {
                    System.out.println("-POSIÇÕES-");
                    for (Jogador j : jogadores) {
                        System.out.println(j.getCor() + " na casa " + j.getPosicao());
                    }
                    System.out.println("VEZ DO " + jogadorAtual.getCor().toUpperCase() + "!\n");
                    tabuleiro.imprimirTabuleiro(jogadores);

                    boolean resultadosIguais = jogadorAtual.mover(true);
                    rodadas[i]++;

                    if (resultadosIguais) {
                        System.out.println("Dados iguais! Pode jogar novamente.");
                    } else {
                        continuarJogando = false;
                    }

                    if (jogadorAtual.getPosicao() >= 40) {
                        System.out.println("O jogador " + jogadorAtual.getCor() + " venceu o jogo!\n");
                        jogoRodando = false;
                        continuarJogando = false;
                        break;
                    }
                }
                if (!jogoRodando) {
                    break;
                }
            }
            rodada++;
        }
        System.out.println("---FIM DE JOGO---");
        for (int i = 0; i < jogadores.length; i++) {
            System.out.printf("Jogador: %s - Cor: %s - Jogadas: %d - Posição: %d\n", jogadores[i].getNome(), jogadores[i].getCor(),
                    rodadas[i], jogadores[i].getPosicao());
        }
    }
}