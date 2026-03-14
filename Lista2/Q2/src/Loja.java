public class Loja {
    String nome;
    Produto[] itensDeVenda;
    Produto[] carrinhoDeCompras;

    float vender(Produto produto) {
        return produto.preco;
    }

    void gerarNotaFiscal(Produto produto) {
        System.out.println("Nota Fiscal");
        System.out.println("Loja: " + nome);
        System.out.println("Produto: " + produto.nome);
        System.out.println("Descrição: " + produto.descricao);
        System.out.println("Preço: R$ " + produto.preco);
    }

}
