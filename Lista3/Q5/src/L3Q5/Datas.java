package L3Q5;

public class Datas {
    int dia;
    int mes;
    int ano;

    public Datas(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public void toStringData() {
        System.out.println(this.dia + "/" + this.mes + "/" + this.ano);
    }

    public void avancarData() {
        this.dia++;
        if (this.dia > 30) {
            this.dia = 1;
            this.mes++;
            if (this.mes > 12) {
                this.mes = 1;
                this.ano++;
            }
        }
    }

    public boolean eMaisAntiga(Datas data) {
        if (this.ano < data.ano || (this.ano == data.ano && this.mes < data.mes) || (this.ano == data.ano && this.mes == data.mes && this.dia < data.dia)) {
            return true;
        } else {
            return false; 
        }
    }

}
