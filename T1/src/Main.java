import T1Classes.CasaDaSorte;
import T1Classes.Jogador;
import T1Classes.Jogo;
import T1Classes.Tabuleiro;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        Tabuleiro tabuleiro = new Tabuleiro(40);

        System.out.println("---JOGO - INICIO---");
        int qtdJogadores;
        while (true) {
            System.out.println("Quantos jogadores jogarão a partida? (min: 2, max: 6)");
            qtdJogadores = input.nextInt();
            input.nextLine();
            if (qtdJogadores < 2 || qtdJogadores > 6) {
                System.out.println("Quantidade inválida! (min: 2, max: 6)");
            } else {
                break;
            }
        }

        Jogador[] jogadores = new Jogador[qtdJogadores];
        int[] rodadas = new int[qtdJogadores];

        int tipoAzarado = 0;
        int tipoSortudo = 0;
        int tipoNormal = 0;

        for (int i = 0; i < qtdJogadores; i++) {
            System.out.println("Jogador " + (i + 1));
            System.out.println("Nome: ");
            String nome = input.nextLine();
            System.out.println("Cor: ");
            String cor = input.nextLine();
            System.out.println("Escolha o tipo do jogador: (1-Normal 2- Sortudo 3 - Azarado): ");
            int tipoJogador = input.nextInt();
            input.nextLine();

            jogadores[i] = new Jogador(cor, nome, 0);

            if (tipoJogador == 2) {
                jogadores[i].setSortudo(true);
                tipoSortudo++;
            } else if (tipoJogador == 3) {
                jogadores[i].setAzarado(true);
                tipoAzarado++;
            } else {
                tipoNormal++;
            }

        }

        int tipos = 0;
        if (tipoNormal > 0) tipos++;
        if (tipoAzarado > 0) tipos++;
        if (tipoSortudo > 0) tipos++;

        if (tipos < 2) {
            System.out.println("O jogo deve ter pelo menos 2 tipos de jogadores diferentes!");
            for (int j = 0; j < jogadores.length; j++) {
                String tipo = "Normal";
                if (jogadores[j].isSortudo()) {
                    tipo = "Sortudo";
                } else if (jogadores[j].isAzarado()) {
                    tipo = "Azarado";
                }
                System.out.println((j + 1) + " - " + jogadores[j].getNome() + " - Tipo: " + tipo);
            }

            System.out.println("Digite o numero do jogador que deseja alterar o tipo: ");
            int indiceAlterar = input.nextInt();
            input.nextLine();

            System.out.println("Digite o novo tipo do jogador: \n1 - Normal, 2 - Sortudo, 3 - Azarado): ");
            int novoTipo = input.nextInt();
            input.nextLine();

            jogadores[indiceAlterar - 1].setAzarado(false);
            jogadores[indiceAlterar - 1].setSortudo(false);
            if (novoTipo == 2) {
                jogadores[indiceAlterar - 1].setSortudo(true);
            } else if (novoTipo == 3) {
                jogadores[indiceAlterar - 1].setAzarado(true);
            }
            System.out.println("Tipo alterado!");
        }
        Jogo jogo = new Jogo(jogadores);
        jogo.jogar(tabuleiro);
    }
}
