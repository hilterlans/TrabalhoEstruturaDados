package linearesLista.Questao5;

public class MainPlaylist {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        System.out.println("===== QUESTAO 5: PLAYLIST DE MUSICAS =====");
        System.out.println("Adicionando musicas...");
        playlist.adicionarMusica(new Musica("Tempo Perdido", "Legiao Urbana", 300));
        playlist.adicionarMusica(new Musica("Pais e Filhos", "Legiao Urbana", 330));
        playlist.adicionarMusica(new Musica("Anna Julia", "Los Hermanos", 210));
        playlist.adicionarMusica(new Musica("Velha Infancia", "Tribalistas", 230));
        playlist.adicionarMusica(new Musica("Amei Te Ver", "Tiago Iorc", 200));
        playlist.adicionarMusica(new Musica("Dias Melhores", "Jota Quest", 220));
        playlist.adicionarMusica(new Musica("Meu Abrigo", "Melim", 190));
        playlist.adicionarMusica(new Musica("Sina", "Djavan", 240));
        playlist.adicionarMusica(new Musica("O Sol", "Vitor Kley", 210));
        playlist.adicionarMusica(new Musica("Flor", "Jorge e Mateus", 205));
        playlist.adicionarMusica(new Musica("Velha Roupa Colorida", "Belchior", 250));
        playlist.adicionarMusica(new Musica("Pro Dia Nascer Feliz", "Barao Vermelho", 230));

        System.out.println("\nPlaylist inicial (" + playlist.getTamanho() + " musicas):");
        playlist.listarMusicas();

        System.out.println("\nBuscando 'Sina':");
        Musica encontrada = playlist.buscarPorTitulo("Sina");
        System.out.println(encontrada == null ? "Musica nao encontrada." : encontrada);

        System.out.println("\nInserindo uma musica na posicao 3:");
        playlist.inserirNaPosicao(new Musica("Trevo", "Anavitoria", 210), 2);
        playlist.listarMusicas();

        System.out.println("\nMovendo 'Trevo' para a primeira posicao:");
        playlist.moverMusica("Trevo", 0);
        playlist.listarMusicas();

        System.out.println("\nRemovendo 'Anna Julia':");
        Musica removida = playlist.removerPorTitulo("Anna Julia");
        System.out.println(removida == null ? "Musica nao encontrada." : "Removida: " + removida);

        System.out.println("\nPlaylist final (" + playlist.getTamanho() + " musicas):");
        playlist.listarMusicas();
    }
}
