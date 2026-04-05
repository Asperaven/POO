import java.util.Scanner;

import L3Q2.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Bem vindo a concessionaria!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome da concessionaria:");
        String nomeConcessionaria = scanner.nextLine();
        Concessionaria concessionaria = new Concessionaria();
        concessionaria.nome = nomeConcessionaria;
        boolean continuar = true;
        while (continuar) {
            System.out.println("Menu de opcoes: 1 - Cadastrar veiculo, 2 - Vender veiculo, 3 - Gerar extrato de vendas, 4 - Sair");
            int opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1:
                    System.out.println("Quantos veiculos deseja cadastrar?");
                    int quantidade = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < quantidade; i++) {
                        System.out.println("Digite o nome do veiculo:");
                        String nomeVeiculo = scanner.nextLine();
                        System.out.println("Digite o preco a vista do veiculo:");
                        double precoAVista = Double.parseDouble(scanner.nextLine());
                        System.out.println("Digite a quantidade de portas do veiculo:");
                        int quantidadeDePortas = Integer.parseInt(scanner.nextLine());
                        
                        Veiculo[] novoArray = new Veiculo[concessionaria.carrosAVenda.length + 1];
                        for (int j = 0; j < concessionaria.carrosAVenda.length; j++) {
                            novoArray[j] = concessionaria.carrosAVenda[j];
                        }
                        novoArray[concessionaria.carrosAVenda.length] = new Veiculo(precoAVista, nomeVeiculo, quantidadeDePortas);
                        concessionaria.carrosAVenda = novoArray;
                    }
                    break;
                case 2:
                    concessionaria.venderVeiculo();
                    break;
                case 3:
                    concessionaria.gerarExtratoDeVendas();
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
        scanner.close();

    }  
}
