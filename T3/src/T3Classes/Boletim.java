package T3Classes;

public class Boletim {
    private Disciplina[] disciplinas;
    private Aluno aluno;

    public Boletim(Disciplina[] disciplinas, Aluno aluno) {
        this.disciplinas = disciplinas;
        this.aluno = aluno;
    }

    public Disciplina[] getDisciplinas() {
        return disciplinas;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setDisciplinas(Disciplina[] disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void gerarBoletim() {
        System.out.println("Boletim de " + aluno.getNome() + ":");
        for (Disciplina disciplina : disciplinas) {
            System.out.println("Disciplina: " + disciplina.getNome());
            for (Aluno prova : disciplina.getProvas()) {
                if (prova.getNome().equals(aluno.getNome())) {
                    System.out.println("Nota: " + prova.getAcertos() + "/10");
                }
            }
        }
        System.out.println("Media geral: " + calcularMediaGeral());
        System.out.println("Situacao: " + (passou() ? "Aprovado" : "Reprovado"));   
    }

    public double calcularMediaGeral() {
        int totalAcertos = 0;
        int totalProvas = 0;
        for (Disciplina disciplina : disciplinas) {
            for (Aluno prova : disciplina.getProvas()) {
                if (prova.getNome().equals(aluno.getNome())) {
                    totalAcertos += prova.getAcertos();
                    totalProvas++;
                }
            }
        }
        double media = (totalProvas > 0) ? (double) totalAcertos / totalProvas : 0;
        System.out.println("Media geral: " + media);
        return media;
    }

    public boolean passou() {
        int totalAcertos = 0;
        int totalProvas = 0;
        for (Disciplina disciplina : disciplinas) {
            for (Aluno prova : disciplina.getProvas()) {
                if (prova.getNome().equals(aluno.getNome())) {
                    totalAcertos += prova.getAcertos();
                    totalProvas++;
                }
            }
        }
        double media = (totalProvas > 0) ? (double) totalAcertos / totalProvas : 0;
        return media >= 7.0; // Considera aprovado se a média for 7 ou mais
    }


}
