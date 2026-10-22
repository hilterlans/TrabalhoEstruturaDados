
package lineareslista.questao5;

public class MainPlaylist {

    public static void main(String[] args) {

        Playlist playlist = new Playlist();

        System.out.println("--- Gerenciamento de Playlist ---");

        playlist.adicionarMusica(
                new Musica("Bloody Samaritan", "Ayra Starr", "3:07")
        );

        playlist.adicionarMusica(
                new Musica("Psychosocial", "Slipknot", "4:43")
        );

        playlist.adicionarMusica(
                new Musica("What is love", "Haddaway", "4:30")
        );

        System.out.println("\nMúsicas na playlist:");
        playlist.listarMusicas();

        System.out.println("\nInserindo no início (posição 0):");
        playlist.inserirNaPosicao(
                new Musica("Diz Aí Qual é o Plano", "MC IG", "3:15"),
                0
        );
        playlist.listarMusicas();

        System.out.println("\nBuscando música:");
        Musica encontrada = playlist.buscarPorTitulo("Psychosocial");

        if (encontrada != null) {
            System.out.println("Música encontrada: " + encontrada);
        } else {
            System.out.println("Música não encontrada.");
        }

        System.out.println("\nMovendo música para a posição 0:");
        playlist.moverMusica("What is love", 0);
        playlist.listarMusicas();

        System.out.println("\nRemovendo música:");
        Musica removida = playlist.removerPorTitulo("Bloody Samaritan");

        if (removida != null) {
            System.out.println("Música removida: " + removida);
        } else {
            System.out.println("Música não encontrada.");
        }

        System.out.println("\nPlaylist final:");
        playlist.listarMusicas();

        System.out.println("\nQuantidade de músicas: " + playlist.getTamanho());
        System.out.println("Playlist vazia? " + playlist.estaVazia());
    }
}
