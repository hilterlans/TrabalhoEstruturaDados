package linearesLista.Questao5;

public class Musica {
    private final String titulo;
    private final String artista;
    private final int duracaoSegundos;

    public Musica(String titulo, String artista, int duracaoSegundos) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O titulo da musica nao pode ser vazio.");
        }
        if (artista == null || artista.isBlank()) {
            throw new IllegalArgumentException("O nome do artista nao pode ser vazio.");
        }
        if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException("A duracao deve ser maior que zero.");
        }
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public String getDuracaoFormatada() {
        return String.format("%d:%02d", duracaoSegundos / 60, duracaoSegundos % 60);
    }

    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + getDuracaoFormatada() + ")";
    }
}
