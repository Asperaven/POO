package T1Classes;

public class Tabuleiro {
    private Casa[] casas;

    public Tabuleiro(int numCasas) {
        this.casas = new Casa[numCasas];
        for (int i = 0; i < numCasas; i++) {
            if (i == 9 || i == 24 || i == 37) {
                casas[i] = new CasaParada(i);
            } else if (i == 12) {
                casas[i] = new CasaSurpresa(i);
            } else if (i == 4 || i == 14 || i == 29) {
                casas[i] = new CasaDaSorte(i);
            } else if (i == 19 || i == 34) {
                casas[i] = new CasaMagica(i);
            } else {
                casas[i] = new Casa(i);
            }
        }
    }

}
