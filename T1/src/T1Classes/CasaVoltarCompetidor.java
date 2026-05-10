package T1Classes;
import java.util.Scanner;

public class CasaVoltarCompetidor extends Casa{

    public CasaVoltarCompetidor(int posicao){
        super(posicao);
    }

    @Override
    public void acao(ContextoExecucao contexto) {
        super.acao(contexto);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha um jogador para voltar ao início (0-" + (contexto.getJogadores().length - 1) + "):");
        int escolha = scanner.nextInt();
        voltarCompetidorInicio(contexto.getJogador(), contexto.getJogadores(), escolha);
    }

    public void voltarCompetidorInicio(Jogador jogadorNaCasa, Jogador[] jogadores, int escolha){
        for (int i = 0; i < jogadores.length; i++){
            if(i == escolha){
                Jogador jogadorEscolhido = jogadores[i];
                jogadorEscolhido.setPosicao(0);
                System.out.println(jogadorEscolhido.getNome() + "voltou para o inicio do jogo!");
                return;
            }
        }
        System.out.println("Jogador inválido!");
    }
}
