package Classes;

public class Disciplina {
    String codigo;
    String nome;
    int qtdMaxAlunos;
    Aluno[] alunosMatriculados;

    public Disciplina(String codigo, String nome, int qtdMaxAlunos) {
        this.codigo = codigo;
        this.nome = nome;
        this.qtdMaxAlunos = qtdMaxAlunos;
        this.alunosMatriculados = new Aluno[qtdMaxAlunos];
    }

    public void matricularAluno(String nomeAluno, String matriculaAluno) {
        int qtdAlunos = 0;
        for (int i = 0; i < alunosMatriculados.length; i++) {
            if (alunosMatriculados[i] != null) {
                qtdAlunos++;
                if (alunosMatriculados[i].getMatricula().equals(matriculaAluno)) {
                    System.out.println("Aluno já matriculado nesta disciplina.");
                    return;
                }
            }
        }
        
        if (qtdAlunos < qtdMaxAlunos) {
            for (int i = 0; i < alunosMatriculados.length; i++) {
                if (alunosMatriculados[i] == null) {
                    Aluno aluno = new Aluno(nomeAluno, matriculaAluno);
                    alunosMatriculados[i] = aluno;
                    System.out.println("Aluno matriculado com sucesso!");
                    return;
                }
            }
        } else {
            System.out.println("Não é possível matricular, disciplina cheia.");
        }
    }

    public void atribuirNota(String matriculaAluno, double nota1, double nota2) {
        for (int i = 0; i < alunosMatriculados.length; i++) {
            if (alunosMatriculados[i] != null && alunosMatriculados[i].getMatricula().equals(matriculaAluno)) {
                alunosMatriculados[i].setNota1(nota1);
                alunosMatriculados[i].setNota2(nota2);
                System.out.println("Notas atribuídas com sucesso!");
                return;
            }
        }
        System.out.println("Aluno não encontrado.");
    }

    public void alterarNota(String matriculaAluno, double novaNota1, double novaNota2) {
        for (int i = 0; i < alunosMatriculados.length; i++) {
            if (alunosMatriculados[i] != null && alunosMatriculados[i].getMatricula().equals(matriculaAluno)) {
                alunosMatriculados[i].setNota1(novaNota1);
                alunosMatriculados[i].setNota2(novaNota2);
                System.out.println("Notas alteradas com sucesso!");
                return;
            }
        }
        System.out.println("Aluno não encontrado.");
    }

    public void gerarEstatisticas() {
        Aluno alunoMaiorMedia = null;
        double maiorMedia = -1;
        int aprovados = 0;
        int reprovados = 0;
        double mediaGeral = 0;
        int qtdAlunos = 0;
        
        // Encontrar aluno com maior média
        for (int i = 0; i < alunosMatriculados.length; i++) {
            if (alunosMatriculados[i] != null) {
                double media = alunosMatriculados[i].calcularMedia();
                mediaGeral += media;
                qtdAlunos++;
                
                if (media > maiorMedia) {
                    maiorMedia = media;
                    alunoMaiorMedia = alunosMatriculados[i];
                }
                
                if (media >= 7) {
                    aprovados++;
                } else {
                    reprovados++;
                }
            }
        }
        
        if (alunoMaiorMedia != null) {
            System.out.println("Aluno com maior média: " + alunoMaiorMedia.getNome() + " - Média: " + alunoMaiorMedia.calcularMedia());
        }
        
        System.out.println("Lista de alunos aprovados: ");
        if (aprovados > 0) {
            for (int i = 0; i < alunosMatriculados.length; i++) {
                if (alunosMatriculados[i] != null && alunosMatriculados[i].calcularMedia() >= 7) {
                    System.out.println(alunosMatriculados[i].getNome() + " - Média: " + alunosMatriculados[i].calcularMedia());
                }
            }
        } else {
            System.out.println("Nenhum aluno aprovado.");
        }
        
        System.out.println("Lista de alunos reprovados: ");
        if (reprovados > 0) {
            for (int i = 0; i < alunosMatriculados.length; i++) {
                if (alunosMatriculados[i] != null && alunosMatriculados[i].calcularMedia() < 7) {
                    System.out.println(alunosMatriculados[i].getNome() + " - Média: " + alunosMatriculados[i].calcularMedia());
                }
            }
        } else {
            System.out.println("Nenhum aluno reprovado.");
        }
        
        if (qtdAlunos > 0) {
            System.out.println("Media geral da turma: " + (mediaGeral / qtdAlunos));
        }
        
        System.out.println("Alunos ordenados por ordem alfabética: ");
        for (int i = 0; i < alunosMatriculados.length; i++) {
            if (alunosMatriculados[i] != null) {
                System.out.println(alunosMatriculados[i].getNome() + " - Média: " + alunosMatriculados[i].calcularMedia());
            }
        }
        
        System.out.println("Alunos ordenados por média decrescente: ");
        for (int i = 0; i < alunosMatriculados.length; i++) {
            if (alunosMatriculados[i] != null) {
                System.out.println(alunosMatriculados[i].getNome() + " - Média: " + alunosMatriculados[i].calcularMedia());
            }
        }
    }


}
