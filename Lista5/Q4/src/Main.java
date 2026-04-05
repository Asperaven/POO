import java.util.Scanner;

import L5Q4.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Sistema de gerenciamento de coleta de materiais reciclaveis");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu de opcoes:");
        System.out.println("1. Cadastrar Estabelecimento");
        System.out.println("2. Cadastrar Material");
        System.out.println("3. Registrar Coleta");
        System.out.println("0. Sair");
        ListaDeEstabelecimentos lista = new ListaDeEstabelecimentos();
        while (true) {
            System.out.println("Escolha uma opcao:");
            int opcao = scanner.nextInt();
            if (opcao == 0) {
                System.out.println("Encerrando o programa...");
                break;
            }
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do estabelecimento:");
                    String nomeEstabelecimento = scanner.next();
                    System.out.println("Digite o peso em kilos:");
                    int peso = scanner.nextInt();
                    System.out.println("Digite o tipo de material coletado (VIDRO, PAPELAO, METAL):");
                    String tipoMaterial = scanner.next();
                    PontoDeColeta ponto;
                    if (tipoMaterial.equalsIgnoreCase("VIDRO")) {
                        ponto = new ColetaVidro(nomeEstabelecimento, peso);
                    } else if (tipoMaterial.equalsIgnoreCase("PAPELAO")) {
                        ponto = new ColetaPapelao(nomeEstabelecimento, peso);
                    } else if (tipoMaterial.equalsIgnoreCase("METAL")) {
                        ponto = new ColetaMetal(nomeEstabelecimento, peso);
                    } else {
                        System.out.println("Tipo de material invalido. Tente novamente.");
                        continue;
                    }
                    lista.registrarEstabelecimento(ponto);
                    break;
                case 2:
                    System.out.println("Digite a demanda atual (VIDRO, PAPELAO, METAL):");
                    String demandaAtual = scanner.next();
                    lista.definirDemanda(demandaAtual);
                    break;
                case 3:
                    lista.mostrarLista();
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
        scanner.close();
    }
}
