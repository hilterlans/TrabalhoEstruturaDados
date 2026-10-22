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

    public void inserir(T dado) {
        raiz = inserirRec(raiz, dado);
    }

    private No inserirRec(No raiz, T dado) {
        if (raiz == null) {
            return new No(dado);
        }
        if (dado.compareTo(raiz.dado) < 0) {
            raiz.esq = inserirRec(raiz.esq, dado);
        } else if (dado.compareTo(raiz.dado) > 0) {
            raiz.dir = inserirRec(raiz.dir, dado);
        }
        return raiz;
    }

    public T buscar(T dado) {
        No res = buscarRec(raiz, dado);
        return res != null ? res.dado : null;
    }

    private No buscarRec(No raiz, T dado) {
        if (raiz == null || raiz.dado.equals(dado)) return raiz;
        if (dado.compareTo(raiz.dado) < 0) return buscarRec(raiz.esq, dado);
        return buscarRec(raiz.dir, dado);
    }

    public void inOrder(java.util.function.Consumer<T> action) {
        inOrderRec(raiz, action);
    }

    private void inOrderRec(No raiz, java.util.function.Consumer<T> action) {
        if (raiz != null) {
            inOrderRec(raiz.esq, action);
            action.accept(raiz.dado);
            inOrderRec(raiz.dir, action);
        }
    }
    
    public No getRaiz() { return raiz; }
}
