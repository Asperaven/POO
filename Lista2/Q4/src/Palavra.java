public class Palavra {
    String palavra;
    String novaPalavra;

    String inverterPalavra(String palavra) {
        String palavraInvertida = "";
        for (int i = palavra.length() - 1; i >= 0; i--) {
            palavraInvertida += palavra.charAt(i);
        }
        return palavraInvertida;
    }

    String quantVogais(String palavra) {
        int contador = 0;
        for (int i = 0; i < palavra.length(); i++) {
            char letra = palavra.charAt(i);
            if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u' ||
                letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U') {
                contador++;
            }
        }
        return "A palavra '" + palavra + "' tem " + contador + " vogais.";
    }

    String concatenarPalavra(String palavra, String novaPalavra) {
        String palavraConcatenada = palavra + novaPalavra;
        return palavraConcatenada;
    }

    String verificarPalavra(String palavra, String novaPalavra) {
        if (novaPalavra.equals(palavra)) {
            return "A palavra '" + novaPalavra + "' é igual à palavra original.";
        } else {
            return "A palavra '" + novaPalavra + "' é diferente da palavra original" + palavra + ".";
        }
    }
}
