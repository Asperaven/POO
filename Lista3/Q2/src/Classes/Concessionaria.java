package Classes;

public class Concessionaria {
    public String nome;
    public Veiculo[] carrosAVenda;
    public String[] vendas;

    public Concessionaria() {
        this.carrosAVenda = new Veiculo[0];
        this.vendas = new String[0];
    }

    public void venderVeiculo() {
        if (carrosAVenda.length == 0) {
            System.out.println("Nenhum veiculo disponivel para venda!");
            return;
        }
        System.out.println("Veiculos disponiveis:");
        for (int i = 0; i < carrosAVenda.length; i++) {
            System.out.println((i + 1) + " - " + carrosAVenda[i].nome + " (" + carrosAVenda[i].tipo + ")");
        }
        System.out.println("Qual veiculo deseja vender? (numero)");
        int escolha = Integer.parseInt(System.console().readLine()) - 1;
        
        if (escolha < 0 || escolha >= carrosAVenda.length) {
            System.out.println("Escolha invalida!");
            return;
        }
        
        Veiculo veiculo = carrosAVenda[escolha];
        System.out.println("Qual o tipo de pagamento? 1 - A vista, 2 - Parcelado");
        int tipoDePagamento = Integer.parseInt(System.console().readLine());
        int numParcelas = 1;
        
        if (tipoDePagamento == 2) {
            System.out.println("Quantas parcelas?");
            numParcelas = Integer.parseInt(System.console().readLine());
        }
        
        gerarCupomFiscal(veiculo, tipoDePagamento, numParcelas);
        
        String[] novasVendas = new String[vendas.length + 1];
        for (int i = 0; i < vendas.length; i++) {
            novasVendas[i] = vendas[i];
        }
        String tipoVenda = (tipoDePagamento == 1) ? "A vista" : numParcelas + "x parcelado";
        double precoVenda = (tipoDePagamento == 1) ? veiculo.precoAVista : veiculo.precoTotalParcelado;
        novasVendas[vendas.length] = veiculo.nome + " - " + tipoVenda + " - R$ " + precoVenda;
        vendas = novasVendas;
    }

    public void gerarCupomFiscal(Veiculo veiculo, int tipoDePagamento, int numParcelas) {
        System.out.println("Cupom Fiscal");
        System.out.println("Veiculo: " + veiculo.nome);
        System.out.println("Tipo de pagamento: " + (tipoDePagamento == 1 ? "A vista" : "Parcelado"));
        if (tipoDePagamento == 2) {
            System.out.println("Numero de parcelas: " + numParcelas);
            System.out.println("Valor parcelado: R$ " + veiculo.getPrecoTotalParcelado());
            System.out.println("Valor de cada parcela: R$ " + (veiculo.getPrecoTotalParcelado() / numParcelas));
        } else {
            System.out.println("Valor a vista: R$ " + veiculo.getPrecoAVista());
        }
    }

    public void gerarExtratoDeVendas() {
        System.out.println("Extrato de vendas:");
        for (String venda : vendas) {
            System.out.println(venda);
        }
    }

}
