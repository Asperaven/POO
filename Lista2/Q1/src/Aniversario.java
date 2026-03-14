import java.util.Scanner;

public class Aniversario {
    private String[] nomes;
    private String[] data;
    private int size; 

    public Aniversario() {
        this.nomes = new String[10];
        this.data = new String[10];
        this.size = 0;
    }

    private void redimensionar() {
        if (size >= nomes.length) {
            String[] novosNomes = new String[nomes.length + 1];
            String[] novasDatas = new String[data.length + 1];
            for (int i = 0; i < nomes.length; i++) {
                novosNomes[i] = nomes[i];
                novasDatas[i] = data[i];
            }
            nomes = novosNomes;
            data = novasDatas;
        }
    }

    void adicionarAmigo() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o nome do amigo: ");
            String nome = sc.nextLine();
            System.out.println("Digite a data de aniversario do amigo: ");
            String dataAmigo = sc.nextLine();
            
            redimensionar(); 
            nomes[size] = nome;
            data[size] = dataAmigo;
            size++;
            System.out.println("Amigo adicionado na posição " + (size - 1) + "!");
        }
    }

    void atualizarAmigo() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite a posição do amigo que deseja atualizar (0 a " + (size - 1) + "): ");
            int posicao = sc.nextInt();
            sc.nextLine();
            
            if (posicao < 0 || posicao >= size) {
                System.out.println("Posição inválida!");
                return;
            }
            
            System.out.println("Novo nome: ");
            String novoNome = sc.nextLine();
            System.out.println("Nova data de aniversario: ");
            String novaData = sc.nextLine();
            
            nomes[posicao] = novoNome;
            data[posicao] = novaData;
            System.out.println("Amigo atualizado!");
        }
    }

    void removerAmigo() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite a posição do amigo que deseja remover (0 a " + (size - 1) + "): ");
            int posicao = sc.nextInt();
            
            if (posicao < 0 || posicao >= size) {
                System.out.println("Posição inválida!");
                return;
            }
            
            for (int i = posicao; i < size - 1; i++) {
                nomes[i] = nomes[i + 1];
                data[i] = data[i + 1];
            }
            size--;
            System.out.println("Amigo removido!");
        }
    }

    void listarAmigos() {
        if (size == 0) {
            System.out.println("Lista de amigos: (vazia)");
            return;
        }
        
        System.out.println("Lista de amigos:");
        for (int i = 0; i < size; i++) {
            System.out.println("[" + i + "] " + nomes[i] + " - " + data[i]);
        }
    }
}
