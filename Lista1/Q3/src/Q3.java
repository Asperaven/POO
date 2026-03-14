import java.util.Scanner;

public class Q3 {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[10];
        int numNegativos = 0;
        int numRepetidos = 0;
        int soma = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre 10 numeros inteiros: ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
            soma += arr[i];
            if (arr[i] < 0) {
                numNegativos++;
            }
        }
        scanner.close();

        for (int i = 0; i < arr.length; i++) {
            boolean jaContou = false;
            for (int k = 0; k < i; k++) {
                if (arr[k] == arr[i]) {
                    jaContou = true;
                    break;
                }
            }
            if (!jaContou) {
                int count = 0;
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j] == arr[i]) {
                        count++;
                    }
                }
                if (count > 1) {
                    numRepetidos++;
                }
            }
        }
        System.out.println("Quantidade de numeros repetidos: " + numRepetidos);
        System.out.println("Os numeros repetidos sao: ");
        for (int i = 0; i < arr.length; i++) {
            boolean jaContou = false;
            for (int k = 0; k < i; k++) {
                if (arr[k] == arr[i]) {
                    jaContou = true;
                    break;
                }
            }
            if (!jaContou) {
                int count = 0;
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j] == arr[i]) {
                        count++;
                    }
                }
                if (count > 1) {
                    System.out.print(arr[i] + " ");
                }
            }
        }
        System.out.println();
        System.out.println("Quantidade de numeros negativos: " + numNegativos);
        System.out.println("Soma dos numeros: " + soma);
        System.out.println("Vetor em ordem reversa: ");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("Vetor em ordem crescente: ");
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
