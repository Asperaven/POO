package T3Classes;
import java.io.*;

public class Disciplina {
    private String nome;
    private Provas[] provas;
    private Gabarito gabarito;

    public Disciplina(String nome, Provas[] provas, Gabarito gabarito) {
        this.nome = nome;
        this.provas = provas;
        this.gabarito = gabarito;
    }

    public String getNome() {
        return nome;
    }

    public Provas[] getProvas() {
        return provas;
    }

    public Gabarito getGabarito() {
        return gabarito;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProvas(Provas[] provas) {
        this.provas = provas;
    }

    public void setGabarito(Gabarito gabarito) {
        this.gabarito = gabarito;
    }


    public void corrigirTodasAsProvas() {
        for (Provas prova : provas) {
            String[] respostasProva = prova.getRespostas();
            String[] respostasGabarito = gabarito.getRespostas();
            String respostasStr = String.join("", respostasProva);
            
            if (respostasStr.equals("VVVVVVVVVV") || respostasStr.equals("FFFFFFFFFF")) {
                prova.setAcertos(0);
            } else {
                int acertos = 0;
                for (int i = 0; i < respostasProva.length; i++) {
                    if (respostasProva[i].equals(respostasGabarito[i])) {
                        acertos++;
                    }
                }
                prova.setAcertos(acertos);
            }
        }
    }


    public double calcularMediaTurma() {
        corrigirTodasAsProvas();
        int totalAcertos = 0;
        for (Provas prova : provas) {
            totalAcertos += prova.getAcertos();
        }
        double media = (double) totalAcertos / (provas.length * 10);
        return media;
    }

    public void ordenarAlfabeticamente() {
        corrigirTodasAsProvas();
        Provas[] provasOrdenadas = provas.clone();
        for (int i = 0; i < provasOrdenadas.length - 1; i++) {
            for (int j = 0; j < provasOrdenadas.length - i - 1; j++) {
                if (provasOrdenadas[j].getNome().compareTo(provasOrdenadas[j + 1].getNome()) > 0) {
                    Provas temp = provasOrdenadas[j];
                    provasOrdenadas[j] = provasOrdenadas[j + 1];
                    provasOrdenadas[j + 1] = temp;
                }
            }
        }
        try (FileWriter fw = new FileWriter(nome + ".txt");
            BufferedWriter bw = new BufferedWriter(fw)) {
            for (Provas prova : provasOrdenadas) {
                String respostas = String.join("", prova.getRespostas());
                bw.write(respostas + "\t" + prova.getNome() + "\t" + prova.getAcertos());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ordernarPorAcertos() {
        corrigirTodasAsProvas();
        Provas[] provasOrdenadas = provas.clone();
        for (int i = 0; i < provasOrdenadas.length - 1; i++) {
            for (int j = 0; j < provasOrdenadas.length - i - 1; j++) {
                if (provasOrdenadas[j].getAcertos() < provasOrdenadas[j + 1].getAcertos()) {
                    Provas temp = provasOrdenadas[j];
                    provasOrdenadas[j] = provasOrdenadas[j + 1];
                    provasOrdenadas[j + 1] = temp;
                }
            }
        }
        try (FileWriter fw = new FileWriter(nome + ".txt");
            BufferedWriter bw = new BufferedWriter(fw)) {
            for (Provas prova : provasOrdenadas) {
                String respostas = String.join("", prova.getRespostas());
                bw.write(respostas + "\t" + prova.getNome() + "\t" + prova.getAcertos());
                bw.newLine();
            }
            bw.write("Media: " + calcularMediaTurma());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerProvasDeArquivo(String nome) {
        try (FileReader fr = new FileReader(nome + ".txt");
            BufferedReader br = new BufferedReader(fr)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


