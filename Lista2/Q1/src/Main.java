import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Aniversario aniversario = new Aniversario();
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 5) {
            System.out.println("Menu de opcoes: 1 adicionar amigo 2 atualizar amigo 3 remover amigo 4 listar amigos 5 sair  ");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    aniversario.adicionarAmigo();
                    break;
                case 2:
                    aniversario.atualizarAmigo();
                    break;
                case 3:
                    aniversario.removerAmigo();
                    break;
                case 4:
                    aniversario.listarAmigos();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }
        sc.close();
    }
}
