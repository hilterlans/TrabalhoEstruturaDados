package linearesFila;
public class EstruturaFila {
    public class Fila<T> {

        // Nó interno da filaa
        private static class No<T> {
            T dado;
            No<T> proximo;

            No(T dado) {
                this.dado = dado;
            }
        }

        private No<T> inicio; // quem sai primeiro
        private No<T> fim;    // quem entrou por último
        private int tamanho;

        // Insere no final da fila
        public void enqueue(T elemento) {
            No<T> novo = new No<>(elemento);
            if (isEmpty()) {
                inicio = novo;
            } else {
                fim.proximo = novo;
            }
            fim = novo;
            tamanho++;
        }

        // Remove e retorna o elemento do início
        public T dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Fila vazia: não há elemento para remover.");
            }
            T dado = inicio.dado;
            inicio = inicio.proximo;
            if (inicio == null) {
                fim = null; // fila ficou vazia
            }
            tamanho--;
            return dado;
        }

        // Consulta o próximo da fila sem remover
        public T front() {
            if (isEmpty()) {
                throw new IllegalStateException("Fila vazia: não há primeiro elemento.");
            }
            return inicio.dado;
        }

        public boolean isEmpty() {
            return tamanho == 0;
        }

        public int size() {
            return tamanho;
        }

        // Extra para o problema 2 (cancelar o último trabalho enviado): O(n)
        public T removerUltimo() {
            if (isEmpty()) {
                throw new IllegalStateException("Fila vazia: não há último elemento.");
            }
            if (inicio == fim) {
                return dequeue();
            }
            No<T> atual = inicio;
            while (atual.proximo != fim) {
                atual = atual.proximo;
            }
            T dado = fim.dado;
            atual.proximo = null;
            fim = atual;
            tamanho--;
            return dado;
        }

        // Extra para listar a fila sem alterá-la
        @Override
        public String toString() {
            if (isEmpty()) {
                return "[]";
            }
            StringBuilder sb = new StringBuilder("[");
            No<T> atual = inicio;
            while (atual != null) {
                sb.append(atual.dado);
                if (atual.proximo != null) {
                    sb.append(" -> ");
                }
                atual = atual.proximo;
            }
            return sb.append("]").toString();
        }
    }
}
