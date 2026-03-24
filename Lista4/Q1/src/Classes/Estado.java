package Classes;

public class Estado {
    private String nome;
    private String sigla;
    private float valorMedioAlcool;
    private float valorMedioGasolina;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public float getValorMedioGasolina() {
        return valorMedioGasolina;
    }

    public void setValorMedioGasolina(float valorMedioGasolina) {
        this.valorMedioGasolina = valorMedioGasolina;
    }

    public float getValorMedioAlcool() {
        return valorMedioAlcool;
    }

    public void setValorMedioAlcool(float valorMedioAlcool) {
        this.valorMedioAlcool = valorMedioAlcool;
    }

    public Estado(String nome, String sigla, float valorMedioAlcool, float valorMedioGasolina) {
        this.nome = nome;
        this.sigla = sigla;
        this.valorMedioAlcool = valorMedioAlcool;
        this.valorMedioGasolina = valorMedioGasolina;
    }

    public String menorPreco(Estado estado, float valorMedioAlcool, float valorMedioGasolina) {
        if (valorMedioAlcool < valorMedioGasolina) {
            return sigla + " - Alcool";
        } else {
            return sigla + " - Gasolina";
        }
    }

}
