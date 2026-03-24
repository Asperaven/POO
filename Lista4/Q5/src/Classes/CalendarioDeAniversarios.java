package Classes;
import java.util.ArrayList;

public class CalendarioDeAniversarios {
    private ArrayList<Amigo> amigos;

    public ArrayList<Amigo> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Amigo> amigos) {
        this.amigos = amigos;
    }

    public CalendarioDeAniversarios() {
        this.amigos = new ArrayList<>();
    }

    public void registrarAmigo(Amigo amigo) {
        amigos.add(amigo);
    }

    public void removerAmigo(Amigo amigo) {
        amigos.remove(amigo);
    }

    public void atualizarAmigo(Amigo amigo, String nome, String dataNascimento) {
        amigo.setNome(nome);
        amigo.setDataNascimento(dataNascimento);
    }

    public void registrarPresentesDados(Amigo amigo, String presente) {
        amigo.getPresentesDados().add(presente);
    }

    public void organizarPorIdade() {
        amigos.sort((b, a) -> b.getDataNascimento().compareTo(a.getDataNascimento()));
    }

    public void organizarAlfabeticamente() {
        amigos.sort((a, b) -> a.getNome().compareTo(b.getNome()));
    }

    public boolean verificarAniversario(String data) {
        for (Amigo amigo : amigos) {
            if (amigo.getDataNascimento().equals(data)) {
                System.out.println("Hoje é aniversário de " + amigo.getNome() + "!");
                return true;
            }
        }
        System.out.println("Não há aniversários hoje.");
        return false;
    }

    public void mostrarAmigos() {
        for (Amigo amigo : amigos) {
            amigo.mostrarDados();
            System.out.println("-------------------");
        }  
    }
}
