import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o valor de x: ");
        int x = sc.nextInt();
        System.out.print("Digite o valor de y: ");
        int y = sc.nextInt();
        System.out.println("Menu de opcoes: 1 - calcular, 2 - raiz quadrada, 3 - potencia");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                Calculo calculo = new Calculo();
                calculo.x = x;
                calculo.y = y;
                calculo.Calcular();
                break;
            case 2:
                Calculo calculoRaiz = new Calculo();
                calculoRaiz.x = x;
                calculoRaiz.y = y;
                int[] raizes = calculoRaiz.gerarRaizQuadrada();
                System.out.println("Raiz quadrada de x: " + raizes[0]);
                System.out.println("Raiz quadrada de y: " + raizes[1]);
                break;
            case 3:
                Calculo calculoPotencia = new Calculo();
                calculoPotencia.x = x;
                calculoPotencia.y = y;
                int potencia = calculoPotencia.gerarPotencia();
                System.out.println("x elevado a y: " + potencia);
                break;
            default:
                System.out.println("Opcao invalida.");
        }
        sc.close();
    }
}
