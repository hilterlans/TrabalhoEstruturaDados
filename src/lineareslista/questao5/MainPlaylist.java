package lineareslista.questao5;
import lineareslista.EstruturaLista;

public class MainPlaylist {
    public static void main(String[] args) {
        EstruturaLista<Musica> playlist = new EstruturaLista<>();

        System.out.println("--- Gerenciamento de Playlist ---");
        playlist.inserirFim(new Musica("Bloody Samaritan", "Ayra Starr", "3:07"));
        playlist.inserirFim(new Musica("Psychosocial", "Slipknot", "4:43"));
        playlist.inserirFim(new Musica("What is love", "Haddaway", "4:30"));
        
        System.out.println("Músicas na playlist:");
        listarPlaylist(playlist);

        System.out.println("\nInserindo no inicio:");
        playlist.inserirInicio(new Musica("Diz Aí Qual é o Plano", "MC IG", "3:15"));
        listarPlaylist(playlist);
    }

    public static void listarPlaylist(EstruturaLista<Musica> lista) {
        EstruturaLista<Musica>.No atual = lista.getCabeca();
        int i = 1;
        while (atual != null) {
            System.out.println(i + ". " + atual.dado.toString());
            atual = atual.proximo;
            i++;
        }
    }
}
