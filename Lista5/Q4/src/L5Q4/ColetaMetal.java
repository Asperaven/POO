package L5Q4;

public class ColetaMetal extends PontoDeColeta {

    public ColetaMetal(String nome, int p) {
        super(nome, p);

    }

    @Override
    public void definirPreco(int p, String demandaAtual) {
        if (demandaAtual.equalsIgnoreCase("PAPELAO")) {
            super.setP(p * 6);
        } else if (demandaAtual.equalsIgnoreCase("METAL")) {
            super.setP(p * 20);
        } else {
            super.setP(p * 10);
        }
    }
}
