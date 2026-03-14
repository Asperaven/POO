import java.util.Scanner;
import Classes.Datas;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sistema de Datas");
        System.out.print("Digite o dia da primeira data: ");
        int dia1 = scanner.nextInt();
        System.out.print("Digite o mês da primeira data: ");
        int mes1 = scanner.nextInt();
        System.out.print("Digite o ano da primeira data: ");
        int ano1 = scanner.nextInt();
        Datas data1 = new Datas(dia1, mes1, ano1);
        System.out.print("Digite o dia da segunda data: ");
        int dia2 = scanner.nextInt();
        System.out.print("Digite o mês da segunda data: ");
        int mes2 = scanner.nextInt();
        System.out.print("Digite o ano da segunda data: ");
        int ano2 = scanner.nextInt();
        Datas data2 = new Datas(dia2, mes2, ano2);
        data1.toStringData();
        data2.toStringData();
        System.out.println("Qual data é mais antiga?");
        if (data1.eMaisAntiga(data2)) {
            System.out.println("Data 1 é mais antiga que Data 2");
        } else {
            System.out.println("Data 2 é mais antiga que Data 1");
        }
        System.out.println("Avancando as duas datas em 1 dia:");
        data1.avancarData();
        data2.avancarData();
        data1.toStringData();
        data2.toStringData();
        scanner.close();
    }
}
