import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int num1 = 0, num2 = 0, num3 = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.println("Entre com o primeiro numero:");
            num1 = scanner.nextInt();
            System.out.println("Entre com o segundo numero:");
            num2 = scanner.nextInt();
            System.out.println("Entre com o terceiro numero:");
            num3 = scanner.nextInt();
            
            if (num1 > 0 && num2 > 0 && num3 > 0) {
                validInput = true;
            } else {
                System.out.println("Erro: Todos os numeros devem ser maiores que 0. Tente novamente.");
            }
        }  
        scanner.close();
    
        if (num1 < (num2+num3) && num2 < (num1+num3) && num3 < (num1+num2)) {
            System.out.println("Os numeros formam um triangulo");
            if (num1 == num2 && num2 == num3) {
                System.out.println("O triangulo é equilatero");
            } else if (num1 == num2 || num1 == num3 || num2 == num3) {
                System.out.println("O triangulo é isosceles");
            } else {
                System.out.println("O triangulo é escaleno");
            }
        } else {
            System.out.println("Os numeros nao formam um triangulo");
        }
    }
}
