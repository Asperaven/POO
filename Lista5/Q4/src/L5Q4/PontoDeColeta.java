package L5Q4;

public class PontoDeColeta {
    private String nome;
    private int p;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }
    

    public PontoDeColeta(String nome, float p) {
        this.nome = nome;
        this.p = (int) p;
    }

    public void definirPreco(int p, String demandaAtual) {
        this.p = (int) (p * 5);
    }
}
