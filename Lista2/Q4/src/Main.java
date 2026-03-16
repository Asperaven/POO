import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Digite uma palavra:");
        Scanner sc = new Scanner(System.in);
        String palavra = sc.nextLine();
        Palavra p = new Palavra();
        System.out.println("Menu de opcoes: 1 - Inverter a palavra, 2 - Quantidade de vogais, 3 - Concatenar palavra, 4 - Verificar palavra");
        int opcao = sc.nextInt();
        sc.nextLine(); // Limpar o buffer do scanner
        switch (opcao) {
            case 1:
                String palavraInvertida = p.inverterPalavra(palavra);
                System.out.println("Palavra invertida: " + palavraInvertida);
                break;
            case 2:
                String resultadoVogais = p.quantVogais(palavra);
                System.out.println(resultadoVogais);
                break;
            case 3:
                System.out.println("Digite a nova palavra para concatenar:");
                String novaPalavra = sc.nextLine();
                String palavraConcatenada = p.concatenarPalavra(palavra, novaPalavra);
                System.out.println("Palavra concatenada: " + palavraConcatenada);
                break;
            case 4:
                System.out.println("Digite a nova palavra para verificar:");
                String novaPalavraVerificar = sc.nextLine();
                String resultadoVerificacao = p.verificarPalavra(palavra, novaPalavraVerificar);
                System.out.println(resultadoVerificacao);
                break;
            default:
                System.out.println("Opcao invalida.");
        }
        sc.close();
    }
}
