import L4Q5.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        CalendarioDeAniversarios calendario = new CalendarioDeAniversarios();
        System.out.println("Sistema de Gerenciamento de Aniversários");
        System.out.println("Menu de opcoes:");
        System.out.println("1. Registrar amigo");
        System.out.println("2. Remover amigo");
        System.out.println("3. Atualizar amigo");
        System.out.println("4. Registrar presente");
        System.out.println("5. Organizar por idade");
        System.out.println("6. Organizar alfabeticamente");
        System.out.println("7. Verificar aniversário");
        System.out.println("8. Mostrar amigos");
        System.out.println("0. Sair");
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();
        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do amigo:");
                    String nome = scanner.next();
                    System.out.println("Digite a data de nascimento do amigo (dd/MM/yyyy):");
                    String dataNascimento = scanner.next();
                    Amigo amigo = new Amigo(nome, dataNascimento);
                    calendario.registrarAmigo(amigo);
                    break;
                case 2:
                    System.out.println("Digite o nome do amigo a ser removido:");
                    String nomeRemover = scanner.next();
                    Amigo amigoRemover = null;
                    for (Amigo a : calendario.getAmigos()) {
                        if (a.getNome().equals(nomeRemover)) {
                            amigoRemover = a;
                            break;
                        }
                    }
                    if (amigoRemover != null) {
                        calendario.removerAmigo(amigoRemover);
                        System.out.println("Amigo removido com sucesso.");
                    } else {
                        System.out.println("Amigo nao encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Digite o nome do amigo a ser atualizado:");
                    String nomeAtualizar = scanner.next();
                    Amigo amigoAtualizar = null;
                    for (Amigo a : calendario.getAmigos()) {
                        if (a.getNome().equals(nomeAtualizar)) {
                            amigoAtualizar = a;
                            break;
                        }
                    }
                    if (amigoAtualizar != null) {
                        System.out.println("Digite o novo nome do amigo:");
                        String novoNome = scanner.next();
                        System.out.println("Digite a nova data de nascimento do amigo (dd/MM/yyyy):");
                        String novaDataNascimento = scanner.next();
                        calendario.atualizarAmigo(amigoAtualizar, novoNome, novaDataNascimento);
                        System.out.println("Amigo atualizado com sucesso.");
                    } else {
                        System.out.println("Amigo nao encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Digite o nome do amigo para registrar o presente:");
                    String nomePresente = scanner.next();
                    Amigo amigoPresente = null;
                    for (Amigo a : calendario.getAmigos()) {
                        if (a.getNome().equals(nomePresente)) {
                            amigoPresente = a;
                            break;
                        }
                    }
                    if (amigoPresente != null) {
                        System.out.println("Digite o presente dado:");
                        String presenteDado = scanner.next();
                        calendario.registrarPresentesDados(amigoPresente, presenteDado);
                        System.out.println("Presente registrado com sucesso.");
                    } else {
                        System.out.println("Amigo nao encontrado.");
                    }
                    break;
                case 5:
                    calendario.organizarPorIdade();
                    System.out.println("Amigos organizados por idade.");
                    break;
                case 6:
                    calendario.organizarAlfabeticamente();
                    System.out.println("Amigos organizados alfabeticamente.");
                    break;
                case 7:
                    System.out.println("Digite a data para verificar aniversário (dd/MM/yyyy):");
                    String data = scanner.next();
                    calendario.verificarAniversario(data);
                    break;
                case 8:
                    calendario.mostrarAmigos();
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
            System.out.println("Menu de opcoes:");
            System.out.println("1. Registrar amigo");
            System.out.println("2. Remover amigo");
            System.out.println("3. Atualizar amigo");
            System.out.println("4. Registrar presente");
            System.out.println("5. Organizar por idade");
            System.out.println("6. Organizar alfabeticamente");
            System.out.println("7. Verificar aniversário");
            System.out.println("8. Mostrar amigos");
            System.out.println("0. Sair");
            opcao = scanner.nextInt();
        }
        scanner.close();
    }
}
