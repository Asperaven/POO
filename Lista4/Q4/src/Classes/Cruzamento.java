package Classes;
import java.util.ArrayList;

public class Cruzamento {
    private enum Veiculo { CARRO, MOTO, CAMINHAO, CARROCONVERSIVEL, CAMINHONETE, POLICIA, BOMBEIRO, AMBULANCIA }
    private ArrayList<String> sentidoHorizontal;
    private ArrayList<String> sentidoVertical;
    private Semaforo semaforo;

    public ArrayList<String> getSentidoHorizontal() {
        return sentidoHorizontal;
    }
    public void setSentidoHorizontal(ArrayList<String> sentidoHorizontal) {
        this.sentidoHorizontal = sentidoHorizontal;
    }
    public ArrayList<String> getSentidoVertical() {
        return sentidoVertical;
    }
    public void setSentidoVertical(ArrayList<String> sentidoVertical) {
        this.sentidoVertical = sentidoVertical;
    }
    public Semaforo getSemaforo() {
        return semaforo;
    }
    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public Cruzamento(String sentidoHorizontal, String sentidoVertical, Semaforo semaforo) {
        this.sentidoHorizontal = new ArrayList<>();
        this.sentidoVertical = new ArrayList<>();
        this.semaforo = semaforo;
    }

    public void preencherSentidos(String sentidoH, String sentidoV) {
        Veiculo[] veiculos = Veiculo.values();
        for (int i = 0; i < 5; i++) {
            int indexAleatorioH = (int) (Math.random() * veiculos.length);
            int indexAleatorioV = (int) (Math.random() * veiculos.length);
            this.sentidoHorizontal.add(veiculos[indexAleatorioH].toString());
            this.sentidoVertical.add(veiculos[indexAleatorioV].toString());
        }
    }

    public void definirPreferencia() {
        int countPublicosHorizontal = 0;
        int countPublicosVertical = 0;
        
        String[] publicos = { "POLICIA", "BOMBEIRO", "AMBULANCIA" };
        
        for (String veiculo : sentidoHorizontal) {
            for (String publico : publicos) {
                if (veiculo.equals(publico)) {
                    countPublicosHorizontal++;
                    break;
                }
            }
        }
        
        for (String veiculo : sentidoVertical) {
            for (String publico : publicos) {
                if (veiculo.equals(publico)) {
                    countPublicosVertical++;
                    break;
                }
            }
        }
        
        if (countPublicosHorizontal > countPublicosVertical) {
            System.out.println("Dando preferencia ao sentido horizontal");
            this.semaforo.setHorizontal(true);
            this.semaforo.setVertical(false);
        } else if (countPublicosVertical > countPublicosHorizontal) {
            System.out.println("Dando preferencia ao sentido vertical");
            this.semaforo.setHorizontal(false);
            this.semaforo.setVertical(true);
        } else {
            System.out.println("Semaforo funcionando normalmente, sem preferencia");
        }
    }

    public void gerarTransito() {
        preencherSentidos(null, null);
        definirPreferencia();
        
        System.out.println("\nVeiculos no sentido horizontal:");
        for (int i = 0; i < sentidoHorizontal.size(); i++) {
            System.out.println("Posicao " + i + ": " + sentidoHorizontal.get(i));
        }
        
        System.out.println("\nVeiculos no sentido vertical:");
        for (int i = 0; i < sentidoVertical.size(); i++) {
            System.out.println("Posicao " + i + ": " + sentidoVertical.get(i));
        }
    }


}
