import java.util.Scanner;
import L5Q2.*;

public class Main {
    static Hospital hospital;

    public static void main(String[] args) throws Exception {
        System.out.println("Sistema de gerenciamento hospitalar");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do diretor:");
        String nomeDiretor = scanner.nextLine();
        System.out.println("Medico diretor tem especialidade? 1 - Pediatra, 2 - Cirurgiao, 3 - Ambos, 0 - Nenhum");
        int especialidadeDiretor = scanner.nextInt();
        switch(especialidadeDiretor) {
            case 1:
                hospital = new Hospital(new Medico(nomeDiretor, "DIRETOR", true, false));
                break;
            case 2:
                hospital = new Hospital(new Medico(nomeDiretor, "DIRETOR", false, true));
                break;
            case 3:
                hospital = new Hospital(new Medico(nomeDiretor, "DIRETOR", true, true));
                break;
            default:
                hospital = new Hospital(new Medico(nomeDiretor, "DIRETOR", false, false));
        }
        System.out.println("Hospital criado com sucesso!");
        System.out.println("Menu de opcoes:");
        System.out.println("1. Contratar Funcionario");
        System.out.println("2. Demitir Funcionario");
        System.out.println("3. Realizar Cirurgia");
        System.out.println("0. Sair");
        while (true) {
            System.out.println("Escolha uma opcao:");
            int opcao = scanner.nextInt();
            if (opcao == 0) {
                System.out.println("Encerrando o programa...");
                break;
            }
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do funcionario:");
                    String nomeFuncionario = scanner.next();
                    System.out.println("Digite o cargo do funcionario (1 - FUNCIONARIO GERAL, 2 - MEDICO, 3 - ENFERMEIRO):");
                    int cargoFuncionario = scanner.nextInt();
                    switch (cargoFuncionario) {
                        case 1:
                            Funcionario funcionario = new Funcionario(nomeFuncionario, 1200, "INTEGRAL");
                            hospital.contratarFuncionario(funcionario);
                            break;
                        case 2:
                            System.out.println("O medico e estagiario ou formado? 1 - Estagiario, 2 - Formado");
                            int tipoMedico = scanner.nextInt();
                            if (tipoMedico == 1) {
                                Medico medicoEstagiario = new Medico(nomeFuncionario, "ESTAGIARIO", false, false);
                                hospital.contratarFuncionario(medicoEstagiario);
                            } else {
                                System.out.println("O medico tem especialidade? 1 - Pediatra, 2 - Cirurgiao, 3 - Ambos, 0 - Nenhum");
                                int especialidadeMedico = scanner.nextInt();
                                switch (especialidadeMedico) {
                                    case 1:
                                        hospital.contratarFuncionario(new Medico(nomeFuncionario, "MEDICO", true, false));
                                        break;
                                    case 2:
                                        hospital.contratarFuncionario(new Medico(nomeFuncionario, "MEDICO", false, true));
                                        break;
                                    case 3:
                                        hospital.contratarFuncionario(new Medico(nomeFuncionario, "MEDICO", true, true));
                                        break;
                                    default:
                                        hospital.contratarFuncionario(new Medico(nomeFuncionario, "MEDICO", false, false));
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Digite as horas extras trabalhadas pelo enfermeiro:");
                            int horasExtras = scanner.nextInt();
                            Enfermeiro enfermeiro = new Enfermeiro(nomeFuncionario, horasExtras);
                            hospital.contratarFuncionario(enfermeiro);
                            break;
                        default:
                            System.out.println("Cargo invalido! Tente novamente.");
                    }
                    break;
                case 2:
                    System.out.println("Digite o nome do funcionario a ser demitido:");
                    String nomeFuncionarioDemitir = scanner.next();
                    Funcionario funcionario = encontrarFuncionarioPorNome(nomeFuncionarioDemitir);
                    if (funcionario != null) {
                        hospital.demitirFuncionario(funcionario);
                    } else {
                        System.out.println("Funcionario nao encontrado! Tente novamente.");
                    }

                    break;
                case 3:
                    System.out.println("Digite o nome do medico que realizara a cirurgia:");
                    String nomeMedicoCirurgia = scanner.next();
                    Funcionario medicoCirurgia = encontrarFuncionarioPorNome(nomeMedicoCirurgia);
                    if (medicoCirurgia != null && medicoCirurgia instanceof Medico) {
                        System.out.println("Digite o nome da cirurgia a ser realizada:");
                        String nomeCirurgia = scanner.next();
                        hospital.realizarCirurgia(nomeCirurgia, (Medico) medicoCirurgia);
                    } else {
                        System.out.println("Medico nao encontrado ou funcionario nao e cirurgiao! Tente novamente.");
                    }
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
        scanner.close();
    }
    static Funcionario encontrarFuncionarioPorNome(String nome) {
        for (Funcionario funcionario : hospital.getFuncionarios()) {
            if (funcionario.getNome().equals(nome)) {
                return funcionario;
            }
        }
        return null;
    }
}
