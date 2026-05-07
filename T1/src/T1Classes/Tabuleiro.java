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
            } else if (i == 16 || i == 26) {
                casas[i] = new CasaVoltarCompetidor(i);
            } else if (i == 19 || i == 34) {
                casas[i] = new CasaMagica(i);
            } else {
                casas[i] = new Casa(i);
            }
        }
    }

    //tabuleiro borda de quadrado
    public void imprimirTabuleiro(Jogador[] jogadores){
        for (int i = 0; i <= 10; i++) {
            System.out.print(mostrarSimbolo(i, jogadores));
        }
        System.out.println();

        for (int i = 1; i <= 9; i++){
            System.out.print(mostrarSimbolo(40 - i, jogadores));
            for(int j = 0; j < 9; j++){
                System.out.print("    ");
            }
            System.out.println(mostrarSimbolo(10 + i, jogadores));
        }

        for(int i = 30; i >= 20; i--){
            System.out.print(mostrarSimbolo(i, jogadores));
        }
        System.out.println("\n");
    }

    public String mostrarSimbolo(int indice, Jogador[] jogadores){
        for(Jogador j : jogadores){
            if(j.getPosicao() == indice){
                if(j.isSortudo()){
                    return "[+]";
                } else if (j.isAzarado()){
                    return "[-]";
                } else {
                    return "[#]";
                }
            }
        }
        return String.format("[%02d]", indice + 1);
    }
}
