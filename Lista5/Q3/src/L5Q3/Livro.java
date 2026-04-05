package L5Q3;

public class Livro {
    private String nome;
    private String autor;


    public String getNome() {
        return nome;
    }



    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getAutor() {
        return autor;
    }



    public void setAutor(String autor) {
        this.autor = autor;
    }



    public Livro(String nome, String autor) {
        this.nome = nome;
        this.autor = autor;
    }

}
