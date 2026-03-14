public class Calculo {
    int x;
    int y;

    void Calcular() {
        int soma = x + y;
        int subtracao = x - y;
        int multiplicacao = x * y;
        int divisao = x / y;

        if (x % y == 0) {
            System.out.format("%d é divisível por %d.%n", x, y);
        } else {
            System.out.format("%d não é divisível por %d.%n", x, y);
        }

        System.out.println("Soma: " + soma);
        System.out.println("Subtração: " + subtracao);
        System.out.println("Multiplicação: " + multiplicacao);
        System.out.println("Divisão: " + divisao);

    }

    int[] gerarRaizQuadrada() {
        return new int[]{(int) Math.sqrt(x), (int) Math.sqrt(y)};
    }

    int gerarPotencia() {
        return (int) Math.pow(x, y);
    }

}
