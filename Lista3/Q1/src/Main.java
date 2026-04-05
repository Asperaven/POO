import java.util.Scanner;

import L3Q1.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome da empresa:");
        String nomeEmpresa = scanner.nextLine();
        System.out.println("Digite a quantidade máxima de funcionários:");
        int quantidadeFuncionarios = scanner.nextInt();
        scanner.nextLine();
        Funcionario[] funcionarios = new Funcionario[quantidadeFuncionarios];
        Empresa empresa = new Empresa(nomeEmpresa, funcionarios);
        boolean continuar = true;
        while (continuar) {
            System.out.println("Menu:");
            System.out.println("1. Registrar novo funcionário");
            System.out.println("2. Promover funcionário");
            System.out.println("3. Demitir funcionário");
            System.out.println("4. Dar aumento");
            System.out.println("5. Mostrar lista de funcionários");
            System.out.println("6. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do funcionário:");
                    String nomeFuncionario = scanner.nextLine();
                    System.out.println("Digite o cargo do funcionário:");
                    String cargoFuncionario = scanner.nextLine();
                    System.out.println("Digite o salário do funcionário:");
                    double salarioFuncionario = scanner.nextDouble();
                    scanner.nextLine(); 
                    empresa.registrarNovoFuncionario(nomeFuncionario, cargoFuncionario, salarioFuncionario);
                    break;
                case 2:
                    System.out.println("Digite o nome do funcionário a ser promovido:");
                    String nomePromovido = scanner.nextLine();
                    System.out.println("Digite o novo cargo:");
                    String novoCargo = scanner.nextLine();
                    System.out.println("Digite o valor do aumento:");
                    double valorPromocao = scanner.nextDouble();
                    scanner.nextLine();
                    empresa.promoverFuncionario(nomePromovido, novoCargo, valorPromocao);
                    break;
                case 3:
                    System.out.println("Digite o nome do funcionário a ser demitido:");
                    String nomeDemitido = scanner.nextLine();
                    empresa.demitirFuncionario(nomeDemitido);
                    break;
                case 4:
                    System.out.println("Digite o nome do funcionário para dar aumento:");
                    String nomeAumento = scanner.nextLine();
                    System.out.println("Digite o valor do aumento:");
                    double valorAumento = scanner.nextDouble();
                    scanner.nextLine(); 
                    empresa.darAumento(nomeAumento, valorAumento);
                    break;
                case 5:
                    empresa.mostrarListaDeFuncionarios();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    continuar = false;  
                    break;
            
                default:
                    break;
            }
        }
        scanner.close();
    }
}
