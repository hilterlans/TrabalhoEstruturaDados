package lineareslista.questao5;

public class Playlist {
    private No inicio;
    private int tamanho;

    public void adicionarMusica(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("A musica nao pode ser nula.");
        }
        No novo = new No(musica);
        if (inicio == null) {
            inicio = novo;
        } else {
            No atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
        tamanho++;
    }

    public boolean inserirNaPosicao(Musica musica, int posicao) {
        if (musica == null || posicao < 0 || posicao > tamanho) {
            return false;
        }
        No novo = new No(musica);
        if (posicao == 0) {
            novo.proximo = inicio;
            inicio = novo;
        } else {
            No anterior = obterNo(posicao - 1);
            novo.proximo = anterior.proximo;
            anterior.proximo = novo;
        }
        tamanho++;
        return true;
    }

    public Musica removerPorTitulo(String titulo) {
        if (titulo == null || inicio == null) {
            return null;
        }
        if (inicio.musica.getTitulo().equalsIgnoreCase(titulo)) {
            Musica removida = inicio.musica;
            inicio = inicio.proximo;
            tamanho--;
            return removida;
        }

        No anterior = inicio;
        No atual = inicio.proximo;
        while (atual != null) {
            if (atual.musica.getTitulo().equalsIgnoreCase(titulo)) {
                anterior.proximo = atual.proximo;
                tamanho--;
                return atual.musica;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return null;
    }

    public Musica buscarPorTitulo(String titulo) {
        if (titulo == null) {
            return null;
        }
        No atual = inicio;
        while (atual != null) {
            if (atual.musica.getTitulo().equalsIgnoreCase(titulo)) {
                return atual.musica;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public boolean moverMusica(String titulo, int novaPosicao) {
        if (titulo == null || novaPosicao < 0 || novaPosicao >= tamanho) {
            return false;
        }
        Musica musica = removerPorTitulo(titulo);
        if (musica == null) {
            return false;
        }
        return inserirNaPosicao(musica, Math.min(novaPosicao, tamanho));
    }

    public void listarMusicas() {
        if (inicio == null) {
            System.out.println("Playlist vazia.");
            return;
        }
        No atual = inicio;
        int indice = 1;
        while (atual != null) {
            System.out.println(indice + ". " + atual.musica);
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

    private No obterNo(int posicao) {
        No atual = inicio;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proximo;
        }
        return atual;
    }
}
