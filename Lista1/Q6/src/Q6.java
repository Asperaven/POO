import java.util.Scanner;

public class Q6 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entre com o numero de linhas e colunas de uma matriz quadrada: ");
        int n = scanner.nextInt();
        int[][] matriz = new int[n][n];
        int soma = 0;
        int somaDiagonal = 0;
        int maiorElemento = Integer.MIN_VALUE;
        int menorElemento = Integer.MAX_VALUE;
        boolean diagonal = true;
        System.out.println("Entre com os elementos da matriz: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = scanner.nextInt();
                soma += matriz[i][j];
                if (matriz[i][j] > maiorElemento) {
                    maiorElemento = matriz[i][j];
                }
                if (matriz[i][j] < menorElemento) {
                    menorElemento = matriz[i][j];
                }
                if (i == j) {
                    somaDiagonal += matriz[i][j];
                    if (matriz[i][j] == 0) {
                        diagonal = false;
                    }
                } else {
                    if (matriz[i][j] != 0) {
                        diagonal = false;
                    }
                }
            }
        }
        scanner.close();

        System.out.println();
        System.out.println("A matriz digitada e: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("A diagonal principal da matriz e: ");
        for (int i = 0; i < n; i++) {
            System.out.print(matriz[i][i] + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("A soma dos elementos da matriz e: " + soma);
        System.err.println();
        System.out.println("A soma dos elementos da diagonal principal e: " + somaDiagonal);
        System.out.println();
        System.out.println("O maior elemento da matriz e: " + maiorElemento);
        System.out.println();
        System.out.println("O menor elemento da matriz e: " + menorElemento);
        System.out.println();
        if (diagonal) {
            System.out.println("A matriz e diagonal.");
        } else {
            System.out.println("A matriz nao e diagonal.");
        }
    }
}
