package L3Q3;

public class Animal {
    String nome;
    double peso;
    int quantidade;
    double valor;

    public Animal() {
        this.nome = "Galinha";
        this.peso = 1.5;
        this.quantidade = 500;
    }

    public Animal(String nome) {
        if (nome.equals("Pato") || nome.equals("Ganso")) {
            this.nome = nome;
        } else {
            this.nome = "Invalido";
        }
        this.peso = 2.0;
        this.quantidade = 100;
    }

    public Animal(String nome, double peso) {
        if (nome.equals("Vaca") || nome.equals("Porco")) {
            this.nome = nome;
        } else {
            this.nome = "Invalido";
        }
        this.peso = peso;
        this.quantidade = 50;
    }

    public void gerarRelatorio() {
        System.out.println("Relatório do Animal:");
        if (this.nome.equals("Invalido")) {
            System.out.println("Nome do animal inválido, tente novamente.");
            return;
        }
        if (this.nome.equals("Galinha")) {
            this.valor = 2.0;
            System.out.println("Nome do animal: " + this.nome);
            System.out.println("Valor unitario: R$ " + this.valor);
            System.out.println("Quantidade unitaria: " + this.quantidade);
            System.out.println("Valor total: R$ " + (this.valor * this.quantidade));
        } else if (this.nome.equals("Vaca")) {
            this.valor = 1500.0;
            if (this.peso > 5) {
                this.valor += this.peso * 50.0;
                this.valor -= 5 * 50.0; // Desconto para os primeiros 5 kg
            }
            System.out.println("Nome do animal: " + this.nome);
            System.out.println("Valor unitario: R$ " + this.valor);
            System.out.println("Quantidade unitaria: " + this.quantidade);
            System.out.println("Valor total: R$ " + (this.valor * this.quantidade));
        } else if (this.nome.equals("Porco")) {
            this.valor = 200.0;
            if (this.peso > 5) {
                this.valor += this.peso * 50.0;
                this.valor -= 5 * 50.0; // Desconto para os primeiros 5 kg
            }
            System.out.println("Nome do animal: " + this.nome);
            System.out.println("Valor unitario: R$ " + this.valor);
            System.out.println("Quantidade unitaria: " + this.quantidade);
            System.out.println("Valor total: R$ " + (this.valor * this.quantidade));
        } else if (this.nome.equals("Pato")) {
            this.valor = 50.0;
            System.out.println("Nome do animal: " + this.nome);
            System.out.println("Valor unitario: R$ " + this.valor);
            System.out.println("Quantidade unitaria: " + this.quantidade);
            System.out.println("Valor total: R$ " + (this.valor * this.quantidade));
        } else if (this.nome.equals("Ganso")) {
            this.valor = 100.0;
            System.out.println("Nome do animal: " + this.nome);
            System.out.println("Valor unitario: R$ " + this.valor);
            System.out.println("Quantidade unitaria: " + this.quantidade);
            System.out.println("Valor total: R$ " + (this.valor * this.quantidade));
        } else {
            this.valor = 0.0;
        }
    }


}
