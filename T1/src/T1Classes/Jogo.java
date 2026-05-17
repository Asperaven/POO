package T1Classes;

import java.util.Scanner;

public class Jogo {
    private Jogador[] jogadores;
    private Tabuleiro tabuleiro;
    private int numCasas = 40;
    private int[] rodadas;
    private int rodada = 1;
    private boolean debug = false;

    public Jogo(Jogador[] jogadores) {
        this.jogadores = jogadores;
        this.rodadas = new int[jogadores.length];
        this.tabuleiro = new Tabuleiro(numCasas);
    }

    public void jogar() throws InterruptedException {

        boolean jogoRodando = true;
        Scanner input = new Scanner(System.in);

        while (jogoRodando) {
            System.out.println(rodada + " RODADA:\n");

            for (int i = 0; i < jogadores.length; i++) {
                Jogador jogadorAtual = jogadores[i];
                boolean continuarJogando = true;

                while (continuarJogando) {
                    System.out.println("\n-POSICOES-");
                    for (Jogador j : jogadores) {
                        System.out.println(j.getCor() + " na casa " + (j.getPosicao() + 1));
                    }
                    System.out.println("VEZ DO " + jogadorAtual.getCor().toUpperCase() + "!\n");
                    this.tabuleiro.imprimirTabuleiro(jogadores);
                    Thread.sleep(1000);
                    boolean resultadosIguais = false;
                    int posicaoAnterior = jogadorAtual.getPosicao();
                    
                    if(debug == true){
                        System.out.println("Modo Debug - Digite o numero da casa que o jogador " + jogadorAtual.getCor() + " deve ir: ");
                        int posicaoEscolhida = input.nextInt();
                        jogadorAtual.setPosicao(posicaoEscolhida - 1);
                    } else {
                        resultadosIguais = jogadorAtual.mover(true);
                    }
                    System.out.println("Soma dos dados do jogador " + jogadorAtual.getCor() + ": " + jogadorAtual.getUltimaSomaDados() + "\n");
                    rodadas[i]++;


                    int indiceAtual = jogadorAtual.getPosicao();
                    if (indiceAtual >= 39){
                        System.out.println("O jogador " + jogadorAtual.getCor() + " venceu o jogo!\n");
                        jogoRodando = false;
                        continuarJogando = false;
                        break;
                    } else {
                        ContextoExecucao contexto = new ContextoExecucao(jogadorAtual, jogadores, 0, input, posicaoAnterior);
                        Casa casaAtual = tabuleiro.getCasa(indiceAtual);
                        casaAtual.acao(contexto);
                    }
                    Thread.sleep(1000);

                    if (resultadosIguais) {
                        System.out.println("Dados iguais! Pode jogar novamente.");
                    } else {
                        continuarJogando = false;
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
            System.out.printf("Jogador: %s - Cor: %s - Jogadas: %d - Posicao: %d\n", jogadores[i].getNome(), jogadores[i].getCor(),
                    rodadas[i], (jogadores[i].getPosicao() + 1));
        }
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}