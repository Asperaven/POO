import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entre com o numero de macas: ");
        int numMaca = scanner.nextInt();
        scanner.close();
        float preco;
        if (numMaca < 12) {
            preco = numMaca * 1.30f;
        } else if (numMaca >= 12) {
            preco = numMaca * 1.00f;
        } else {
            System.out.println("Numero de macas invalido.");
            return;
        }
        System.out.println("Voce comprou " + numMaca + " macas por R$ " + String.format("%.2f", preco) + ".");
    }
}
