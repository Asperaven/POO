package L4Q1;
import java.util.ArrayList;

public class Pais {
    private String nome;
    private ArrayList<Estado> estados;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public Pais(String nome, ArrayList<Estado> estados) {
        this.nome = nome;
        this.estados = estados;
    }

    public void atualizarEstado(Estado estado) {
        boolean encontrado = false;
        for (Estado e : estados) {
            if (e.getSigla().equals(estado.getSigla())) {
                e.setValorMedioAlcool(estado.getValorMedioAlcool());
                e.setValorMedioGasolina(estado.getValorMedioGasolina());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Erro: Estado com sigla '" + estado.getSigla() + "' nao foi encontrado!");
        }
    }

    public void removerEstado(Estado estado) {
        boolean encontrado = false;
        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).getSigla().equals(estado.getSigla())) {
                estados.remove(i);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Erro: Estado com sigla '" + estado.getSigla() + "' nao foi encontrado!");
        }
    }

    public void gerarListaEstados() {
        if (estados.isEmpty()) {
            System.out.println("Nenhum estado cadastrado.");
            return;
        }
        
        ArrayList<Estado> estadosOrdenados = new ArrayList<>(estados);
        for (int i = 0; i < estadosOrdenados.size(); i++) {
            for (int j = 0; j < estadosOrdenados.size() - 1 - i; j++) {
                if (estadosOrdenados.get(j).getValorMedioAlcool() < estadosOrdenados.get(j + 1).getValorMedioAlcool()) {
                    Estado temp = estadosOrdenados.get(j);
                    estadosOrdenados.set(j, estadosOrdenados.get(j + 1));
                    estadosOrdenados.set(j + 1, temp);
                }
            }
        }
        
        System.out.println("\n=== ORDEM DECRESCENTE DE PREÇO DO ALCOOL ===");
        for (Estado e : estadosOrdenados) {
            System.out.println(e.getNome() + " (" + e.getSigla() + ") - R$ " + e.getValorMedioAlcool());
        }
        
        for (int i = 0; i < estadosOrdenados.size(); i++) {
            for (int j = 0; j < estadosOrdenados.size() - 1 - i; j++) {
                if (estadosOrdenados.get(j).getValorMedioGasolina() < estadosOrdenados.get(j + 1).getValorMedioGasolina()) {
                    Estado temp = estadosOrdenados.get(j);
                    estadosOrdenados.set(j, estadosOrdenados.get(j + 1));
                    estadosOrdenados.set(j + 1, temp);
                }
            }
        }
        
        System.out.println("\n=== ORDEM DECRESCENTE DE PREÇO DA GASOLINA ===");
        for (Estado e : estadosOrdenados) {
            System.out.println(e.getNome() + " (" + e.getSigla() + ") - R$ " + e.getValorMedioGasolina());
        }
    }


}
