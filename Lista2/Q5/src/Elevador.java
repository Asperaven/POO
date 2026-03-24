public class Elevador {
    int andarAtual;
    int totalAndares;
    int capacidade = 5;
    int pessoasPresentes;
    int andarAlvo;

    int entra() {
        if (pessoasPresentes < capacidade) {
            pessoasPresentes++;
            return pessoasPresentes;
        } else {
            System.out.println("Capacidade máxima atingida!");
            return pessoasPresentes;
        }
    }

    int sai() {
        if (pessoasPresentes > 0) {
            pessoasPresentes--;
            return pessoasPresentes;
        } else {
            System.out.println("O elevador já está vazio!");
            return pessoasPresentes;
        }
    }

    int sobe() {
        if (andarAtual < totalAndares) {
            andarAtual++;
            return andarAtual;
        } else {
            System.out.println("Você já está no último andar!");
            return andarAtual;
        }
    }

    int sobeAndar(int andarAlvo) {
        if (andarAlvo > totalAndares) {
            System.out.println("Andar inválido!");
            return andarAtual;
        } else if (andarAlvo < 0) {
            System.out.println("Andar inválido!");
            return andarAtual;
        } 
        while (andarAtual < andarAlvo) {
            sobe();
        }
        return andarAtual;
    }

    int desce() {
        if (andarAtual > 0) {
            andarAtual--;
            return andarAtual;
        } else {
            System.out.println("Você já está no térreo!");
            return andarAtual;
        }
    }

    int desceAndar(int andarAlvo) {
        if (andarAlvo > totalAndares) {
            System.out.println("Andar inválido!");
            return andarAtual;
        } else if (andarAlvo < 0) {
            System.out.println("Andar inválido!");
            return andarAtual;
        } 
        while (andarAtual > andarAlvo) {
            desce();
        }
        return andarAtual;
    }

    String boasVindas() {
        return "Bem-vindo ao elevador! O elevador está no andar " + andarAtual + " e tem " + pessoasPresentes + " pessoas presentes.";
    }

}
