package L4Q2;
import java.util.ArrayList;

public class Hotel {
    private float valorDiaria;
    private boolean cafeDaManha;
    private ArrayList<Quarto> quartos;
    private int notaDeAvaliacao;

    public float getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(float valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public boolean isCafeDaManha() {
        return cafeDaManha;
    }

    public void setCafeDaManha(boolean cafeDaManha) {
        this.cafeDaManha = cafeDaManha;
    }

    public ArrayList<Quarto> getQuartos() {
        return quartos;
    }

    public void setQuartos(ArrayList<Quarto> quartos) {
        this.quartos = quartos;
    }

    public int getNotaDeAvaliacao() {
        return notaDeAvaliacao;
    }

    public void setNotaDeAvaliacao(int notaDeAvaliacao) {
        this.notaDeAvaliacao = notaDeAvaliacao;
    }

    public Hotel(float valorDiaria, boolean cafeDaManha, ArrayList<Quarto> quartos, int notaDeAvaliacao) {
        this.valorDiaria = valorDiaria;
        this.cafeDaManha = cafeDaManha;
        this.quartos = quartos;
        this.notaDeAvaliacao = notaDeAvaliacao;
    }

    public float calcularHospedagem(Quarto quarto) {
        if (!quarto.isVago()) {
            float hospedagem = valorDiaria * (float) quarto.getQuantidadeDeDias();
            return hospedagem;
        } else {
            System.out.println("Erro: Quarto vago.");
            return 0;
        }
    }

    public float cobrarConsumo(boolean cafeDaManha, float hospedagem) {
        if (cafeDaManha) {
            return hospedagem + 20; // Supondo que o café da manhã custa 20
        } else {
            return hospedagem;
        }
    }

    public float totalAPagar(float hospedagem, int tipoDePagamento) {
        float total = cobrarConsumo(cafeDaManha, hospedagem);
        if (tipoDePagamento == 1) {
            total *= 0.9; // 10% de desconto para pagamento à vista
        } else if (tipoDePagamento == 2) {
            total *= 1.00; // Sem desconto para pagamento parcelado
    }
        return total;
    }

    public void deixarAvaliacao(int nota) {
        if (nota >= 1 && nota <= 5) {
            this.notaDeAvaliacao = nota;
        } else {
            System.out.println("Erro: Nota de avaliação deve ser entre 1 e 5.");
        }
    }

    public void gerarCupomFiscal(Quarto quarto, int tipoDePagamento, int notaDeAvaliacao) {
        float hospedagem = calcularHospedagem(quarto);
        
        if (hospedagem == 0) {
            return; 
        }
        
        float total = totalAPagar(hospedagem, tipoDePagamento);

        System.out.println("\n========== CUPOM FISCAL ==========");
        System.out.println("Valor da Diaria: R$ " + valorDiaria);
        System.out.println("Quantidade de Dias: " + quarto.getQuantidadeDeDias());
        System.out.println("Hospedagem (subtotal): R$ " + hospedagem);
        System.out.println("Cafe da Manhã: " + (cafeDaManha ? "Sim (+R$ 20)" : "Não"));
        System.out.println("Tipo de Pagamento: " + (tipoDePagamento == 1 ? "A vista (10% desconto)" : "Parcelado (sem desconto)"));
        System.out.println("TOTAL A PAGAR: R$ " + total);
        
        deixarAvaliacao(notaDeAvaliacao);
        System.out.println("Avaliação registrada: " + notaDeAvaliacao + "/5");
        
        quarto.descuparQuarto();
        System.out.println("Quarto liberado com sucesso.");
        System.out.println("==================================\n");
    }

    public void mostrarListaDeQuartos() {
        System.out.println("Lista de Quartos:");
        for (int i = 0; i < quartos.size(); i++) {
            Quarto quarto = quartos.get(i);
            if (quarto.isVago()) {
                System.out.println("Quarto " + (i + 1) + ": Vago");
            } else {
                System.out.println("Quarto " + (i + 1) + ": Ocupado por " + quarto.getQuantidadeDeDias() + " dias");
            }
        }
    }


}

