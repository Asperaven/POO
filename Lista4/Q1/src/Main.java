import java.util.ArrayList;
import java.util.Scanner;
import L4Q1.*;

public class Main {
    public static void mostrarMenu() {
        System.out.println("\nMenu de opcoes: ");
        System.out.println("1. Cadastrar estado");
        System.out.println("2. Atualizar preco de um estado");
        System.out.println("3. Remover estado");
        System.out.println("4. Listar estados");
        System.out.println("5. Sair");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Sistema de precos de combustiveis");
        ArrayList<Estado> estados = new ArrayList<>();
        Pais pais = new Pais("Brasil", estados);
        Scanner sc = new Scanner(System.in);
        
        mostrarMenu();
        int opcao = sc.nextInt();
        while (opcao != 5) {
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do estado: ");
                    String nome = sc.next();
                    System.out.println("Digite a sigla do estado: ");
                    String sigla = sc.next();
                    System.out.println("Digite o valor medio do alcool: ");
                    float valorMedioAlcool = sc.nextFloat();
                    System.out.println("Digite o valor medio da gasolina: ");
                    float valorMedioGasolina = sc.nextFloat();
                    Estado estado = new Estado(nome, sigla, valorMedioAlcool, valorMedioGasolina);
                    pais.getEstados().add(estado);
                    System.out.println("Estado cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println("Digite a sigla do estado que deseja atualizar: ");
                    String siglaAtualizar = sc.next();
                    System.out.println("Digite o novo valor medio do alcool: ");
                    float novoValorMedioAlcool = sc.nextFloat();
                    System.out.println("Digite o novo valor medio da gasolina: ");
                    float novoValorMedioGasolina = sc.nextFloat();
                    Estado estadoAtualizar = new Estado("", siglaAtualizar, novoValorMedioAlcool, novoValorMedioGasolina);
                    pais.atualizarEstado(estadoAtualizar);
                    System.out.println("Estado atualizado com sucesso!");
                    break;
                case 3:
                    System.out.println("Digite a sigla do estado que deseja remover: ");
                    String siglaRemover = sc.next();
                    Estado estadoRemover = new Estado("", siglaRemover, 0, 0);
                    pais.removerEstado(estadoRemover);
                    System.out.println("Estado removido com sucesso!");
                    break;
                case 4:
                    pais.gerarListaEstados();
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
            mostrarMenu();
            opcao = sc.nextInt();
        }
        sc.close();
    }
}
