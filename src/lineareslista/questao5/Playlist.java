
package lineareslista.questao5;

import lineareslista.EstruturaLista;

public class Playlist {

    private EstruturaLista<Musica> musicas;
    private int tamanho;

    public Playlist() {
        this.musicas = new EstruturaLista<>();
        this.tamanho = 0;
    }

    public void adicionarMusica(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException(
                    "A musica nao pode ser nula."
            );
        }

        musicas.inserirFim(musica);
        tamanho++;
    }

    public boolean inserirNaPosicao(Musica musica, int posicao) {
        if (musica == null || posicao < 0 || posicao > tamanho) {
            return false;
        }

        musicas.inserirPosicao(musica, posicao);
        tamanho++;
        return true;
    }

    public Musica removerPorTitulo(String titulo) {
        if (titulo == null) {
            return null;
        }

        EstruturaLista<Musica>.No atual = musicas.getCabeca();
        EstruturaLista<Musica>.No alvo = null;

        // Encontra a primeira musica com o titulo informado.
        while (atual != null) {
            if (atual.dado.getTitulo() != null
                    && atual.dado.getTitulo().equalsIgnoreCase(titulo)) {
                alvo = atual;
                break;
            }

            atual = atual.proximo;
        }

        if (alvo == null) {
            return null;
        }

        Musica removida = alvo.dado;
        EstruturaLista<Musica> novaLista = new EstruturaLista<>();

        atual = musicas.getCabeca();

        // Reconstrói a lista sem o nó encontrado.
        while (atual != null) {
            if (atual != alvo) {
                novaLista.inserirFim(atual.dado);
            }

            atual = atual.proximo;
        }

        musicas = novaLista;
        tamanho--;

        return removida;
    }

    public Musica buscarPorTitulo(String titulo) {
        if (titulo == null) {
            return null;
        }

        EstruturaLista<Musica>.No atual = musicas.getCabeca();

        while (atual != null) {
            Musica musica = atual.dado;

            if (musica.getTitulo() != null
                    && musica.getTitulo().equalsIgnoreCase(titulo)) {
                return musica;
            }

            atual = atual.proximo;
        }

        return null;
    }

    public boolean moverMusica(String titulo, int novaPosicao) {
        if (titulo == null || novaPosicao < 0
                || novaPosicao >= tamanho) {
            return false;
        }

        Musica musica = removerPorTitulo(titulo);

        if (musica == null) {
            return false;
        }

        return inserirNaPosicao(
                musica,
                Math.min(novaPosicao, tamanho)
        );
    }

    public void listarMusicas() {
        if (musicas.getCabeca() == null) {
            System.out.println("Playlist vazia.");
            return;
        }

        EstruturaLista<Musica>.No atual = musicas.getCabeca();
        int indice = 1;

        while (atual != null) {
            System.out.println(indice + ". " + atual.dado);
            atual = atual.proximo;
            indice++;
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }
}
