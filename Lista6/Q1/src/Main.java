import java.util.Scanner;

import L6Q1.*;

public class Main {
    public static void main(String[] args) {
        Empresa empresa = new Empresa("Tech Corp");
        Scanner scanner = new Scanner(System.in);
        
        FuncionarioComum comum = new FuncionarioComum("Erick", "Dev", 2500);
        FuncionarioTemporario temporario = new FuncionarioTemporario("Paulo", "Secretario", 1500);
        FuncionarioExecutivo executivo = new FuncionarioExecutivo("Henrique", "Diretor", 10000);
        
        empresa.getFuncionarios().add(comum);
        empresa.getFuncionarios().add(temporario);
        empresa.getFuncionarios().add(executivo);

        boolean menu = true;
        while (menu) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Exibir todos os funcionarios");
            System.out.println("2. Ver funcionario especifico");
            System.out.println("3. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            if (opcao == 1) {
                empresa.exibirFuncionarios();
            } else if (opcao == 2) {
                System.out.println("Digite o nome do funcionario:");
                String nome = scanner.nextLine();
                Funcionario f = empresa.buscarFuncionario(nome);
                
                if (f != null) {
                    f.exibirInformacoes();
                    
                    if (f instanceof FuncionarioExecutivo) {
                        FuncionarioExecutivo exec = (FuncionarioExecutivo) f;
                        submenuExecutivo(exec, empresa, scanner);
                    }
                }
            } else {
                menu = false;
            }
        }
    }
    
    static void submenuExecutivo(FuncionarioExecutivo exec, Empresa empresa, Scanner scanner) {
        boolean submenu = true;
        while (submenu) {
            System.out.println("\n=== MENU DO EXECUTIVO ===");
            System.out.println("1. Contratar funcionario");
            System.out.println("2. Demitir funcionario");
            System.out.println("3. Promover funcionario");
            System.out.println("4. Voltar");
            int op = scanner.nextInt();
            scanner.nextLine();
            
            if (op == 1) {
                System.out.println("Nome do funcionario a contratar: ");
                String nome = scanner.nextLine();
                System.out.println("Cargo do funcionario a contratar: ");
                String cargo = scanner.nextLine();
                System.out.println("Salario do funcionario a contratar: ");
                double salario = scanner.nextDouble();
                FuncionarioComum novo = new FuncionarioComum(nome, cargo, salario);
                exec.contratarFuncionario(novo, empresa);
            } else if (op == 2) {
                System.out.println("Nome do funcionario a demitir: ");
                String nome = scanner.nextLine();
                Funcionario f = empresa.buscarFuncionario(nome);
                if (f != null) {
                    exec.demitirFuncionario(f, empresa);
                }
            } else if (op == 3) {
                System.out.println("Nome do funcionario a promover: ");
                String nome = scanner.nextLine();
                Funcionario f = empresa.buscarFuncionario(nome);
                System.out.println("Novo cargo: ");
                String novoCargo = scanner.nextLine();
                System.out.println("Novo salario: ");
                double novoSalario = scanner.nextDouble();
                if (f != null) {
                    exec.promoverFuncionario(f, novoCargo, novoSalario, empresa);
                }
            } else {
                submenu = false;
            }
        }
    }
}