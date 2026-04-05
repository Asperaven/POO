package L5Q4;

public class ColetaPapelao extends PontoDeColeta {

    public ColetaPapelao(String nome, int p) {
        super(nome, p);
    }

    @Override
    public void definirPreco(int p, String demandaAtual) {
        if (demandaAtual.equalsIgnoreCase("VIDRO")) {
            super.setP(p * 4);
        } else if (demandaAtual.equalsIgnoreCase("PAPELAO")) {
            super.setP(p * 14);
        } else {
            super.setP(p * 7);
        }

    }

}
