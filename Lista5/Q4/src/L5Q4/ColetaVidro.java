package L5Q4;

public class ColetaVidro extends PontoDeColeta {
    
    public ColetaVidro(String nome, int p) {
        super(nome, p);
    }

    @Override
    public void definirPreco(int p, String demandaAtual) {
        if (demandaAtual.equalsIgnoreCase("METAL")) {
            super.setP(p * 3);
        } else if (demandaAtual.equalsIgnoreCase("VIDRO")) {
            super.setP(p * 10);
        } else {
            super.setP(p * 5);
        }

    }

}
