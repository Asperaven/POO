import java.util.Scanner;

import L3Q3.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Animal[] animal = new Animal[5];
        int contador = 0;
        System.out.println("Menu de genreciamento de animais: 1 - Registrar animal, 2 - Gerar relatório, 3 - Sair");
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();
        while (opcao != 3) {
            if (opcao == 1) {
                System.out.println("Escolha o tipo de animal: 1 Galinha, 2 Pato ou Ganso, 3 Vaca ou Porco");
                int tipoAnimal = scanner.nextInt();
                if (tipoAnimal == 1) {
                    if (contador < animal.length) {
                        animal[contador] = new Animal();
                        contador++;
                        System.out.println("Animal registrado com sucesso.");
                    } else {
                        System.out.println("Limite de lote de animais atingido.");
                    }
                } else if (tipoAnimal == 2) {
                    System.out.println("Digite o nome do animal:");
                    String nome = scanner.next();
                    if (nome.equals("Pato") || nome.equals("Ganso")) {
                        if (contador < animal.length) {
                            animal[contador] = new Animal(nome);
                            contador++;
                            System.out.println("Animal registrado com sucesso.");
                        } else {
                            System.out.println("Limite de lote de animais atingido.");
                        }
                    } else {
                        System.out.println("Nome de animal inválido.");
                    }
                } else if (tipoAnimal == 3) {
                    System.out.println("Digite o nome do animal:");
                    String nome = scanner.next();
                    if (nome.equals("Vaca") || nome.equals("Porco")) {
                        System.out.println("Digite o peso do animal:");
                        double peso = scanner.nextDouble();
                        if (contador < animal.length) {
                            animal[contador] = new Animal(nome, peso);
                            contador++;
                            System.out.println("Animal registrado com sucesso.");
                        } else {
                            System.out.println("Limite de lote de animais atingido.");
                        }
                    } else {
                        System.out.println("Nome de animal inválido.");
                    }
                } else {
                    System.out.println("Tipo de animal inválido, tente novamente.");
                }
            } else if (opcao == 2) {
                for (int i = 0; i < animal.length; i++) {
                    if (animal[i] != null) {
                        animal[i].gerarRelatorio();
                    }
                }
            } else {
                System.out.println("Opção inválida, tente novamente.");
            }
            System.out.println("Menu de genreciamento de animais: 1 - Registrar animal, 2 - Gerar relatório, 3 - Sair");
            opcao = scanner.nextInt();
        }
        scanner.close();
    }
}
