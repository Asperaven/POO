import L6Q4.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Bem-vindo ao RPG de Turnos!");
        boolean menu = true;
        while (menu) {
            System.out.println("1. Jogar");
            System.out.println("2. Sair");
            Scanner scanner = new Scanner(System.in);
            int escolha = scanner.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("Qual o nome do monstro?");
                    String nomeMonstro = scanner.next();
                    Monstro monstro = new Monstro(nomeMonstro);
                    Jogo jogo = new Jogo(monstro);
                    System.out.println("Criando personagens...");
                    boolean criacaoPersonagens = true;
                    while (criacaoPersonagens) {
                        System.out.println("Escolha o tipo de personagem:");
                        System.out.println("1. Guerreiro");
                        System.out.println("2. Mago");
                        System.out.println("3. Curandeiro");
                        System.out.println("4. Finalizar criação");
                        int tipoPersonagem = scanner.nextInt();
                        if (tipoPersonagem == 4) {
                            criacaoPersonagens = false;
                            continue;
                        }
                        System.out.println("Digite o nome do personagem:");
                        String nome = scanner.next();
                        Personagem personagem;
                        switch (tipoPersonagem) {
                            case 1:
                                personagem = new Guerreiro(nome);
                                break;
                            case 2:
                                personagem = new Mago(nome);
                                break;
                            case 3:
                                personagem = new Curandeiro(nome);
                                break;
                            default:
                                System.out.println("Tipo de personagem inválido. Tente novamente.");
                                continue;
                        }
                        jogo.adicionarJogador(personagem);
                    }
                    jogo.jogar();
                    break;
                case 2:
                    menu = false;
                    System.out.println("Saindo do jogo. Até a próxima!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            scanner.close();
        } 
    }
}
