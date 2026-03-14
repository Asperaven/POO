import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a quantia de dinheiro em R$ no caixa: ");
        double dinheiroCaixa = scanner.nextDouble();
        System.out.print("Digite a quantidade de unidades a serem compradas: ");
        int unidadesCompradas = scanner.nextInt();
        System.out.print("Digite o preço em R$ por unidade: ");
        double precoPorUnidade = scanner.nextDouble();
        double valorTotalCompra = unidadesCompradas * precoPorUnidade;
        if (valorTotalCompra >= 0.8 * dinheiroCaixa) {
            System.out.println("Compra sera realizada por parcelamento em 3 vezes, com juros de 10%. Valor total da compra com juros: " + (valorTotalCompra * 1.10) + " R$");
        } else {
            System.out.println("Compra autorizada a vista. Desconto de 5% aplicado. Valor total da compra com desconto: " + (valorTotalCompra * 0.95) + " R$");
        }
        scanner.close();
    }
}
