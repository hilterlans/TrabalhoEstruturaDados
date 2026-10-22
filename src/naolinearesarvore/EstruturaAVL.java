/**
 * ESTRUTURA DE DADOS: Árvore AVL (árvore binária de busca auto-balanceada)
 *
 * Representação:
 *   - Igual à BST, mas cada nó guarda sua altura. O fator de balanceamento
 *     (altura da esquerda - altura da direita) de TODO nó fica sempre em
 *     {-1, 0, +1}; quando uma inserção ou remoção quebra isso, rotações
 *     simples ou duplas restauram o equilíbrio.
 *   - Chaves iguais não são aceitas (inserir devolve false).
 *
 * Complexidade (n = nós):
 *   - inserir / buscar / remover ... O(log n) no pior caso (altura <= 1,44 log2 n)
 *   - topK(k) ...................... O(log n + k)
 *   - inOrder ...................... O(n)
 *   - estaBalanceada ............... O(n)
 *   - Espaço ....................... O(n)
 */
package naolinearesarvore;

public class EstruturaAVL<T extends Comparable<T>> {
    public class No {
        public T dado;
        public int altura;
        public No esq, dir;

        public No(T dado) {
            this.dado = dado;
            this.altura = 1;
        }
    }

    private No raiz;
    private int tamanho;

    // ------------------------------------------------------------------
    // Altura, fator de balanceamento e rotações
    // ------------------------------------------------------------------

    private int altura(No no) {
        return no == null ? 0 : no.altura;
    }

    private void atualizarAltura(No no) {
        no.altura = 1 + Math.max(altura(no.esq), altura(no.dir));
    }

    private int fator(No no) {
        return no == null ? 0 : altura(no.esq) - altura(no.dir);
    }

    private No rotacaoDireita(No y) {
        No x = y.esq;
        No t2 = x.dir;
        x.dir = y;
        y.esq = t2;
        atualizarAltura(y);
        atualizarAltura(x);
        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.dir;
        No t2 = y.esq;
        y.esq = x;
        x.dir = t2;
        atualizarAltura(x);
        atualizarAltura(y);
        return y;
    }

    /**
     * Recalcula a altura do nó e aplica a rotação necessária. Serve tanto
     * para inserção quanto para remoção, olhando o fator do filho pesado:
     * esquerda-esquerda e direita-direita => rotação simples;
     * esquerda-direita e direita-esquerda => rotação dupla.
     */
    private No balancear(No no) {
        atualizarAltura(no);
        int fb = fator(no);
        if (fb > 1) {
            if (fator(no.esq) < 0) no.esq = rotacaoEsquerda(no.esq);
            return rotacaoDireita(no);
        }
        if (fb < -1) {
            if (fator(no.dir) > 0) no.dir = rotacaoDireita(no.dir);
            return rotacaoEsquerda(no);
        }
        return no;
    }

    // ------------------------------------------------------------------
    // Inserção, busca e remoção
    // ------------------------------------------------------------------

    /** Insere o dado e rebalanceia. Devolve false se já existe um dado igual. */
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
        } else {
            return no; // chave repetida: ignora
        }
        return balancear(no);
    }

    /** Devolve o dado armazenado "igual" (compareTo == 0) ao informado, ou null. */
    public T buscar(T dado) {
        No atual = raiz;
        while (atual != null && dado != null) {
            int cmp = dado.compareTo(atual.dado);
            if (cmp == 0) return atual.dado;
            atual = cmp < 0 ? atual.esq : atual.dir;
        }
        return null;
    }

    public boolean contem(T dado) {
        return buscar(dado) != null;
    }

    /** Remove o dado e rebalanceia o caminho até a raiz. Devolve false se não existe. */
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
        return balancear(no);
    }

    // ------------------------------------------------------------------
    // Verificação de altura / balanceamento
    // ------------------------------------------------------------------

    /** Altura da árvore: 0 se vazia, 1 se só a raiz. */
    public int altura() {
        return altura(raiz);
    }

    /** Fator de balanceamento da raiz (0 se a árvore está vazia). */
    public int fatorBalanceamentoRaiz() {
        return fator(raiz);
    }

    public int getTamanho() { return tamanho; }

    public boolean estaVazia() { return raiz == null; }

    /**
     * Confere nó a nó se |fator| <= 1 e se a altura guardada no nó bate com a
     * altura real das subárvores. Serve como "prova" de que a árvore está
     * mesmo balanceada.
     */
    public boolean estaBalanceada() {
        return verificar(raiz) >= 0;
    }

    private int verificar(No no) {
        if (no == null) return 0;
        int he = verificar(no.esq);
        if (he < 0) return -1;
        int hd = verificar(no.dir);
        if (hd < 0) return -1;
        if (Math.abs(he - hd) > 1) return -1;
        int real = 1 + Math.max(he, hd);
        return no.altura == real ? real : -1;
    }

    // ------------------------------------------------------------------
    // Percursos
    // ------------------------------------------------------------------

    /** Em ordem crescente (esquerda, raiz, direita). */
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

    /** Em ordem decrescente (direita, raiz, esquerda). */
    public void inOrderDecrescente(java.util.function.Consumer<T> action) {
        decrescenteRec(raiz, action);
    }

    private void decrescenteRec(No no, java.util.function.Consumer<T> action) {
        if (no != null) {
            decrescenteRec(no.dir, action);
            action.accept(no.dado);
            decrescenteRec(no.esq, action);
        }
    }

    /**
     * Visita os k maiores dados, do maior para o menor, e PARA assim que
     * chegar em k (não percorre a árvore inteira).
     */
    public void topK(int k, java.util.function.Consumer<T> action) {
        if (k < 0) {
            throw new IllegalArgumentException("k não pode ser negativo: " + k);
        }
        int[] restante = {k};
        topKRec(raiz, restante, action);
    }

    private void topKRec(No no, int[] restante, java.util.function.Consumer<T> action) {
        if (no == null || restante[0] <= 0) return;
        topKRec(no.dir, restante, action);
        if (restante[0] > 0) {
            action.accept(no.dado);
            restante[0]--;
        }
        topKRec(no.esq, restante, action);
    }

    /** Desenha a árvore deitada (raiz à esquerda, direita em cima), com o fator de cada nó. */
    public void imprimirArvore(java.util.function.Function<T, String> rotulo) {
        if (raiz == null) {
            System.out.println("(árvore vazia)");
            return;
        }
        imprimirRec(raiz, 0, rotulo);
    }

    private void imprimirRec(No no, int nivel, java.util.function.Function<T, String> rotulo) {
        if (no == null) return;
        imprimirRec(no.dir, nivel + 1, rotulo);
        System.out.println("      ".repeat(nivel) + rotulo.apply(no.dado) + " [FB=" + fator(no) + "]");
        imprimirRec(no.esq, nivel + 1, rotulo);
    }
}
