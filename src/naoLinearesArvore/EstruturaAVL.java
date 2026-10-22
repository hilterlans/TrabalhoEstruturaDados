package naoLinearesArvore;

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

    private int altura(No no) {
        return no == null ? 0 : no.altura;
    }

    private int getFatorBalanceamento(No no) {
        return no == null ? 0 : altura(no.esq) - altura(no.dir);
    }

    private No rotacaoDireita(No y) {
        No x = y.esq;
        No T2 = x.dir;
        x.dir = y;
        y.esq = T2;
        y.altura = Math.max(altura(y.esq), altura(y.dir)) + 1;
        x.altura = Math.max(altura(x.esq), altura(x.dir)) + 1;
        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.dir;
        No T2 = y.esq;
        y.esq = x;
        x.dir = T2;
        x.altura = Math.max(altura(x.esq), altura(x.dir)) + 1;
        y.altura = Math.max(altura(y.esq), altura(y.dir)) + 1;
        return y;
    }

    public void inserir(T dado) {
        raiz = inserirRec(raiz, dado);
    }

    private No inserirRec(No no, T dado) {
        if (no == null) return new No(dado);

        if (dado.compareTo(no.dado) < 0)
            no.esq = inserirRec(no.esq, dado);
        else if (dado.compareTo(no.dado) > 0)
            no.dir = inserirRec(no.dir, dado);
        else return no;

        no.altura = 1 + Math.max(altura(no.esq), altura(no.dir));
        int balanceamento = getFatorBalanceamento(no);

        if (balanceamento > 1 && dado.compareTo(no.esq.dado) < 0)
            return rotacaoDireita(no);

        if (balanceamento < -1 && dado.compareTo(no.dir.dado) > 0)
            return rotacaoEsquerda(no);

        if (balanceamento > 1 && dado.compareTo(no.esq.dado) > 0) {
            no.esq = rotacaoEsquerda(no.esq);
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && dado.compareTo(no.dir.dado) < 0) {
            no.dir = rotacaoDireita(no.dir);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void inOrder(java.util.function.Consumer<T> action) {
        inOrderRec(raiz, action);
    }

    private void inOrderRec(No raiz, java.util.function.Consumer<T> action) {
        if (raiz != null) {
            inOrderRec(raiz.dir, action); // Reverse para o Ranking ou inOrder normal
            action.accept(raiz.dado);
            inOrderRec(raiz.esq, action);
        }
    }
}
