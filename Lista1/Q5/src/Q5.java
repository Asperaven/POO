import java.util.Scanner;

public class Q5 {
    public static void main(String[] args) throws Exception {
        System.out.println("Hora de entrada:");
        Scanner scanner = new Scanner(System.in);
        int horaEntrada = scanner.nextInt();
        if (horaEntrada > 24 || horaEntrada < 0) {
            System.out.println("Hora de entrada inválida. Tente novamente.");
        } 
        System.out.println("Minutos de entrada:");
        int minutosEntrada = scanner.nextInt();
        if (minutosEntrada > 60 || minutosEntrada < 0) {
            System.out.println("Minutos de entrada inválidos. Tente novamente.");
        }

        System.out.println("Hora de saída:");
        int horaSaida = scanner.nextInt();
        if (horaSaida > 24 || horaSaida < 0) {
            System.out.println("Hora de saída inválida. Tente novamente.");
        }
        System.out.println("Minutos de saída:");
        int minutosSaida = scanner.nextInt();
        if (minutosSaida > 60 || minutosSaida < 0) {
            System.out.println("Minutos de saída inválidos. Tente novamente.");
        }
        scanner.close();

        int totalHoras = horaSaida - horaEntrada;
        int totalMinutos = minutosSaida - minutosEntrada;

        if (totalMinutos > 0) {
            totalHoras++;
        }
        int valorPagar = 0;
        if (totalHoras == 1) {
            valorPagar = 5;
            System.out.println("Valor a pagar: R$ " + valorPagar + ",00");
        } else if (totalHoras == 2) {
            valorPagar = 8;
            System.out.println("Valor a pagar: R$ " + valorPagar + ",00");
        } else if (totalHoras > 2) {
            valorPagar = 8 + (2 * totalHoras);
            System.out.println("Valor a pagar: R$ " + valorPagar + ",00");
        } else {
            System.out.println("Tempo de estacionamento inválido. Tente novamente.");
        }
    }
}
