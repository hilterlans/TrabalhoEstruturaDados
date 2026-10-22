/**
 * ESTRUTURA DE DADOS: Árvore Binária de Busca (BST)
 *
 * Representação:
 *   - Cada nó guarda um dado e dois filhos: valores menores à esquerda e
 *     maiores à direita (a ordem vem do compareTo do tipo T).
 *   - Chaves iguais não são aceitas (inserir devolve false).
 *
 * Complexidade (n = nós, h = altura):
 *   - inserir / buscar / remover ... O(h): O(log n) em média, O(n) no pior
 *                                    caso (inserção em ordem crescente
 *                                    transforma a árvore numa "lista")
 *   - inOrder ...................... O(n)
 *   - altura ....................... O(n)
 *   - Espaço ....................... O(n)
 */
package naolinearesarvore;

public class EstruturaBST<T extends Comparable<T>> {
    public class No {
        public T dado;
        public No esq, dir;
        public No(T dado) {
            this.dado = dado;
            this.esq = null;
            this.dir = null;
        }
    }

    private No raiz;
    private int tamanho;

    /** Insere o dado. Devolve false se já existe um dado igual (não duplica). */
    public boolean inserir(T dado) {
        if (dado == null) {
            throw new IllegalArgumentException("Não é possível inserir valor nulo na árvore.");
        }
        int antes = tamanho;
        raiz = inserirRec(raiz, dado);
        return tamanho > antes;
    }

    private No inserirRec(No no, T dado) {
        if (no == null) {
            tamanho++;
            return new No(dado);
        }
        int cmp = dado.compareTo(no.dado);
        if (cmp < 0) {
            no.esq = inserirRec(no.esq, dado);
        } else if (cmp > 0) {
            no.dir = inserirRec(no.dir, dado);
        }
        return no;
    }

    /** Devolve o dado armazenado que é "igual" (compareTo == 0) ao informado, ou null. */
    public T buscar(T dado) {
        No res = buscarRec(raiz, dado);
        return res != null ? res.dado : null;
    }

    public boolean contem(T dado) {
        return buscarRec(raiz, dado) != null;
    }

    private No buscarRec(No no, T dado) {
        if (no == null || dado == null) return null;
        int cmp = dado.compareTo(no.dado);
        if (cmp == 0) return no;
        return cmp < 0 ? buscarRec(no.esq, dado) : buscarRec(no.dir, dado);
    }

    /**
     * Remove o dado. Trata os três casos clássicos:
     *   1) nó folha: simplesmente some;
     *   2) um filho: o filho ocupa o lugar do nó;
     *   3) dois filhos: o nó recebe o sucessor em ordem (menor valor da
     *      subárvore direita), que então é removido da subárvore.
     * Devolve false se o dado não existe.
     */
    public boolean remover(T dado) {
        if (dado == null || !contem(dado)) return false;
        raiz = removerRec(raiz, dado);
        tamanho--;
        return true;
    }

    private No removerRec(No no, T dado) {
        if (no == null) return null;
        int cmp = dado.compareTo(no.dado);
        if (cmp < 0) {
            no.esq = removerRec(no.esq, dado);
        } else if (cmp > 0) {
            no.dir = removerRec(no.dir, dado);
        } else {
            if (no.esq == null) return no.dir;
            if (no.dir == null) return no.esq;
            No sucessor = no.dir;
            while (sucessor.esq != null) sucessor = sucessor.esq;
            no.dado = sucessor.dado;
            no.dir = removerRec(no.dir, sucessor.dado);
        }
        return no;
    }

    /** Percurso em ordem (esquerda, raiz, direita): dados em ordem crescente. */
    public void inOrder(java.util.function.Consumer<T> action) {
        inOrderRec(raiz, action);
    }

    private void inOrderRec(No no, java.util.function.Consumer<T> action) {
        if (no != null) {
            inOrderRec(no.esq, action);
            action.accept(no.dado);
            inOrderRec(no.dir, action);
        }
    }

    /** Altura da árvore: 0 se vazia, 1 se só a raiz. */
    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(No no) {
        if (no == null) return 0;
        return 1 + Math.max(alturaRec(no.esq), alturaRec(no.dir));
    }

    public int getTamanho() { return tamanho; }

    public boolean estaVazia() { return raiz == null; }

    public No getRaiz() { return raiz; }
}
