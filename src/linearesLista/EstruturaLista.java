package linearesLista;

public class EstruturaLista<T> {
    public class No {
        public T dado;
        public No proximo;
        public No(T dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    private No cabeca;
    private int tamanho;

    public EstruturaLista() {
        this.cabeca = null;
        this.tamanho = 0;
    }

    public void inserirInicio(T dado) {
        No novoNo = new No(dado);
        novoNo.proximo = cabeca;
        cabeca = novoNo;
        tamanho++;
    }

    public void inserirFim(T dado) {
        No novoNo = new No(dado);
        if (cabeca == null) {
            cabeca = novoNo;
        } else {
            No atual = cabeca;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
        }
        tamanho++;
    }

    public void inserirPosicao(T dado, int posicao) {
        if (posicao <= 0) {
            inserirInicio(dado);
            return;
        }
        if (posicao >= tamanho) {
            inserirFim(dado);
            return;
        }
        No novoNo = new No(dado);
        No atual = cabeca;
        for (int i = 0; i < posicao - 1; i++) {
            atual = atual.proximo;
        }
        novoNo.proximo = atual.proximo;
        atual.proximo = novoNo;
        tamanho++;
    }

    public boolean remover(T dado) {
        if (cabeca == null) return false;

        if (cabeca.dado.equals(dado)) {
            cabeca = cabeca.proximo;
            tamanho--;
            return true;
        }

        No atual = cabeca;
        while (atual.proximo != null && !atual.proximo.dado.equals(dado)) {
            atual = atual.proximo;
        }

        if (atual.proximo != null) {
            atual.proximo = atual.proximo.proximo;
            tamanho--;
            return true;
        }
        return false;
    }

    public No getCabeca() {
        return cabeca;
    }

    public int getTamanho() {
        return tamanho;
    }
}
