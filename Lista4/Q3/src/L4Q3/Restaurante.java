package L4Q3;
import java.util.ArrayList;

public class Restaurante {
    private String nome;
    private ArrayList<Pedido> pedidos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Restaurante(String nome) {
        this.nome = nome;
        this.pedidos = new ArrayList<>();
    }

    public void registrarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void organizarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado para organizar.");
            return;
        } else {
            for (int i = 0; i < pedidos.size() - 1; i++) {
                if (pedidos.get(i).getAlimentos().size() > pedidos.get(i + 1).getAlimentos().size()) {
                    Pedido temp = pedidos.get(i);
                    pedidos.set(i, pedidos.get(i + 1));
                    pedidos.set(i + 1, temp);
                }
            }
        }
    }

    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }
        for (Pedido pedido : pedidos) {
            System.out.println("Cliente: " + pedido.getCliente());
            System.out.println("Endereco: " + pedido.getEndereco());
            System.out.println("Alimentos: " + pedido.getAlimentos());
            System.out.println("-------------------------");
        }
    }

    public void removerPedido(Pedido pedido) {
        if (pedidos.remove(pedido)) {
            System.out.println("Pedido removido com sucesso.");
        } else {
            System.out.println("Erro: Pedido nao encontrado para remover!");
        }
    }

    public void gerarCupomFiscal(Pedido pedido) {
        if (pedidos.contains(pedido)) {
            System.out.println("Restaurante: " + this.nome);
            System.out.println("---Cupom Fiscal---");
            System.out.println("Cliente: " + pedido.getCliente());
            System.out.println("Endereco: " + pedido.getEndereco());
            System.out.println("Alimentos: " + pedido.getAlimentos());
            System.out.println("Valor Total: R$ " + pedido.getAlimentos().size() * 10); 
        } else {
            System.out.println("Erro: Pedido nao encontrado para gerar cupom fiscal!");
        }
    }
}
