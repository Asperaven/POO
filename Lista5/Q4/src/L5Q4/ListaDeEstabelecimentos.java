package L5Q4;
import java.util.ArrayList;

public class ListaDeEstabelecimentos {
    private ArrayList<PontoDeColeta> pontos;
    private String demandaAtual;

    public ListaDeEstabelecimentos() {
        this.pontos = new ArrayList<>();
    }

    public void registrarEstabelecimento(PontoDeColeta ponto) {
        if (buscarEstabelecimento(ponto.getNome()) == null) {
            pontos.add(ponto);
            System.out.println("Ponto de Coleta registrado: " + ponto.getNome());
        } else if (verificarEstabelecimento(ponto)) {
            System.out.println("Ponto de Coleta do mesmo tipo ja registrado: " + ponto.getNome());
        }
    }

    public void removerEstabelecimento(String nome) {
        PontoDeColeta ponto = buscarEstabelecimento(nome);
        if (ponto != null) {
            pontos.remove(ponto);
            System.out.println("Ponto de Coleta removido: " + nome);
        }
    }

    public void definirDemanda(String demanda) {
        if (demanda == "VIDRO" || demanda == "PAPELAO" || demanda == "METAL") {
            this.demandaAtual = demanda;
            System.out.println("Demanda definida: " + demanda);
        } else {
            System.out.println("Demanda invalida. Use VIDRO, PAPELAO ou METAL.");
            return;
        };
    }

    public void mostrarLista() {
        if (pontos.isEmpty()) {
            System.out.println("Nenhum ponto de coleta registrado.");
        } else {
            System.out.println("Pontos de Coleta Registrados:");
            for (PontoDeColeta ponto : pontos) {
                System.out.println("- " + ponto.getNome() + " | Preco: " + ponto.getP());
            }
        }
        System.out.println("Melhor ponto para demanda (" + demandaAtual + "):");
        
        PontoDeColeta melhorPonto = null;
        int maiorPreco = -1;
        
        for (PontoDeColeta ponto : pontos) {
            if ((demandaAtual.equals("VIDRO") && ponto instanceof ColetaVidro) ||
                (demandaAtual.equals("METAL") && ponto instanceof ColetaMetal) ||
                (demandaAtual.equals("PAPELAO") && ponto instanceof ColetaPapelao)) {
                
                if (ponto.getP() > maiorPreco) {
                    maiorPreco = ponto.getP();
                    melhorPonto = ponto;
                }
            }
        }
        
        if (melhorPonto != null) {
            System.out.println("- " + melhorPonto.getNome() + " | Preco: " + melhorPonto.getP());
        } else {
            System.out.println("Nenhum ponto disponivel para esta demanda.");
        }
        
        System.out.println("Pior ponto para demanda (" + demandaAtual + "):");
        
        PontoDeColeta piorPonto = null;
        int menorPreco = Integer.MAX_VALUE;
        
        for (PontoDeColeta ponto : pontos) {
            if ((demandaAtual.equals("VIDRO") && ponto instanceof ColetaVidro) ||
                (demandaAtual.equals("METAL") && ponto instanceof ColetaMetal) ||
                (demandaAtual.equals("PAPELAO") && ponto instanceof ColetaPapelao)) {
                
                if (ponto.getP() < menorPreco) {
                    menorPreco = ponto.getP();
                    piorPonto = ponto;
                }
            }
        }
        
        if (piorPonto != null) {
            System.out.println("- " + piorPonto.getNome() + " | Preco: " + piorPonto.getP());
        } else {
            System.out.println("Nenhum ponto disponivel para esta demanda.");
        }
    }



    PontoDeColeta buscarEstabelecimento(String nome) {
        for (PontoDeColeta ponto : pontos) {
            if (ponto.getNome().equalsIgnoreCase(nome)) {
                return ponto;
            }
        }
        System.out.println("Ponto de Coleta nao encontrado.");
        return null;
    }

    private boolean verificarEstabelecimento(PontoDeColeta novoPonto) {
        for (PontoDeColeta ponto : pontos) {
            if (novoPonto.getClass() == ponto.getClass() && 
                novoPonto.getNome().equalsIgnoreCase(ponto.getNome())) {
                return true; 
            }
        }
        return false;
    }

}
