package T1Classes;

import java.util.Random;

public class CasaSurpresa extends Casa {
    public CasaSurpresa(int posicao) {
        super(posicao);
    }

    @Override
    public void acao(ContextoExecucao contexto) {
        super.acao(contexto);
        System.out.println("Casa Surpresa! Tire uma carta surpresa para modificar sua sorte!");
        surpreenderJogador(contexto.getJogador());
    }

    public void surpreenderJogador(Jogador jogador) {
        Random rand = new Random();
        int carta = rand.nextInt(3); // Gera um número aleatório entre 0 e 2
        switch (carta) {
            case 0:
                System.out.println(jogador.getNome() + " tirou a carta azul: esta sortudo!");
                jogador.setAzarado(false);
                jogador.setSortudo(true);
                break;
            case 1:
                System.out.println(jogador.getNome() + " tirou a carta vermelha: esta azarado!");
                jogador.setSortudo(false);
                jogador.setAzarado(true);
                break;
            case 2:
                System.out.println(jogador.getNome() + " tirou a carta roxa: esta neutro!");
                jogador.setSortudo(false);
                jogador.setAzarado(false);
                break;
        }
    }

}
