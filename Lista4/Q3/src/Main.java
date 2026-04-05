import L4Q3.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Restaurante restaurante = new Restaurante("Los Pollos Hermanos");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu do Restaurante:");
        System.out.println("1. Registrar Pedido");
        System.out.println("2. Organizar Pedidos");
        System.out.println("3. Mostrar Pedidos");
        System.out.println("4. Remover Pedido");
        System.out.println("5. Gerar Cupom Fiscal");
        System.out.println("0. Sair");
        int opcao = scanner.nextInt();
        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do cliente: ");
                    String cliente = scanner.next();
                    System.out.print("Digite o endereco do cliente: ");
                    String endereco = scanner.next();
                    Pedido pedido = new Pedido(cliente, endereco);
                    System.out.print("Digite o alimento a ser registrado: ");
                    String alimento = scanner.next();
                    pedido.registrarAlimento(alimento);
                    restaurante.registrarPedido(pedido);
                    System.out.println("Pedido registrado com sucesso! Deseja adicionar mais alimentos? (true/false): ");
                    boolean adicionarMais = scanner.nextBoolean();
                    while (adicionarMais) {
                        System.out.print("Digite o alimento a ser registrado: ");
                        alimento = scanner.next();
                        pedido.registrarAlimento(alimento);
                        System.out.println("Deseja adicionar mais alimentos? (true/false): ");
                        adicionarMais = scanner.nextBoolean();
                    }
                    break;
                case 2:
                    restaurante.organizarPedidos();
                    break;
                case 3:
                    restaurante.mostrarPedidos();
                    break;
                case 4:
                    System.out.print("Digite o nome do cliente para remover o pedido: ");
                    String clienteRemover = scanner.next();
                    for (Pedido p : restaurante.getPedidos()) {
                        if (p.getCliente().equals(clienteRemover)) {
                            restaurante.removerPedido(p);
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.print("Digite o nome do cliente para gerar o cupom fiscal: ");
                    String clienteCupom = scanner.next();
                    for (Pedido p : restaurante.getPedidos()) {
                        if (p.getCliente().equals(clienteCupom)) {
                            restaurante.gerarCupomFiscal(p);
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
            System.out.println("Menu do Restaurante:");
            System.out.println("1. Registrar Pedido");
            System.out.println("2. Organizar Pedidos");
            System.out.println("3. Mostrar Pedidos");
            System.out.println("4. Remover Pedido");
            System.out.println("5. Gerar Cupom Fiscal");
            System.out.println("0. Sair");
            opcao = scanner.nextInt();
        }
        scanner.close();
        System.out.println("Saindo do sistema...");
    }
}
