import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Loja loja = new Loja();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome da loja:");
        loja.nome = sc.nextLine();

        System.out.println("Digite o número de itens para venda:");
        int size = sc.nextInt();
        sc.nextLine(); 

        Produto itensDeVenda[] = new Produto[size];
        for (int i = 0; i < size; i++) {
            Produto produto = new Produto();
            System.out.println("Digite o nome do produto:");
            produto.nome = sc.nextLine();
            System.out.println("Digite a descrição do produto:");
            produto.descricao = sc.nextLine();
            System.out.println("Digite o preço do produto:");
            produto.preco = sc.nextFloat();
            sc.nextLine(); 
            itensDeVenda[i] = produto;
        }



        System.out.println("Deseja emitir nota fiscal? 1 para sim, 0 para não");
        int resposta = sc.nextInt();
        if (resposta == 1) {
            for (Produto produto : itensDeVenda) {
                loja.gerarNotaFiscal(produto);
            }
        } else {
            float precoTotal = 0;
            for (Produto produto : itensDeVenda) {
                precoTotal += loja.vender(produto);
            }
            System.out.println("Preço total: R$ " + precoTotal);
        }
        sc.close();

    }
}
