package L3Q2;

public class Veiculo {
    public double precoAVista;
    public double precoTotalParcelado;
    public String nome;
    public String tipo;
    public int quantidadeDePortas;

    public double getPrecoAVista() {
        return precoAVista;
    }

    public void setPrecoAVista(double precoAVista) {
        this.precoAVista = precoAVista;
    }

    public double getPrecoTotalParcelado() {
        return precoTotalParcelado;
    }

    public void setPrecoTotalParcelado(double precoTotalParcelado) {
        this.precoTotalParcelado = precoTotalParcelado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidadeDePortas() {
        return quantidadeDePortas;
    }

    public void setQuantidadeDePortas(int quantidadeDePortas) {
        this.quantidadeDePortas = quantidadeDePortas;
    }

    public Veiculo(double precoAVista, String nome, int quantidadeDePortas) {
        this.precoAVista = precoAVista;
        this.nome = nome;
        this.quantidadeDePortas = quantidadeDePortas;

        this.precoTotalParcelado = precoAVista * 1.5;
        if (quantidadeDePortas == 0) {
            this.tipo = "Moto";
        } else if (quantidadeDePortas == 2) {
            this.tipo = "Caminhao";
        } else if (quantidadeDePortas == 4) {
            this.tipo = "Carro";
        } else {
            this.tipo = "Invalido";
        }
    }

}
