package linearesLista.Questao5;
import java.util.Objects;

public class Musica {
    private String titulo;
    private String artista;
    private String duracao;

    public Musica(String titulo, String artista, String duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
    }

    public String getTitulo() { return titulo; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Musica musica = (Musica) obj;
        return Objects.equals(titulo, musica.titulo);
    }

    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + duracao + ")";
    }
}
