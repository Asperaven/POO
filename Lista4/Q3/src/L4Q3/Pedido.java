package L4Q3;
import java.util.ArrayList;

public class Pedido {
    private ArrayList<String> alimentos;
    private String cliente;
    private String endereco;

    public ArrayList<String> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(ArrayList<String> alimentos) {
        this.alimentos = alimentos;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Pedido(String cliente, String endereco) {
        this.cliente = cliente;
        this.endereco = endereco;
    }

    public void registrarAlimento(String alimento) {
        if (alimentos == null) {
            alimentos = new ArrayList<>();
        }
        alimentos.add(alimento);
    }

    public void atualizarPedido(String alimentoAtualizado) {
        if (alimentos == null || alimentos.isEmpty()) {
            System.out.println("Nenhum alimento registrado para atualizar.");
            return;
        }
        boolean encontrado = false;
        for (int i = 0; i < alimentos.size(); i++) {
            if (alimentos.get(i).equals(alimentoAtualizado)) {
                alimentos.set(i, alimentoAtualizado);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Erro: Alimento '" + alimentoAtualizado + "' nao foi encontrado para atualizar!");
        }
    }

    public void limparPedido() {
        if (alimentos != null) {
            alimentos.clear();
        }
    }

    public void mostrarPedido() {
        System.out.println("Cliente: " + cliente);
        System.out.println("Endereco: " + endereco);
        if (alimentos == null || alimentos.isEmpty()) {
            System.out.println("Nenhum alimento registrado.");
        } else {
            System.out.println("Alimentos:");
            for (String alimento : alimentos) {
                System.out.println("- " + alimento);
            }
        }
    }


}
