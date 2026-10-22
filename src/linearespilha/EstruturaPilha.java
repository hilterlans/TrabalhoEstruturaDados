package linearespilha;

public class EstruturaPilha {
    public static class Pilha<T> {

        // Nó interno da pilha
        private static class No<T> {
            T dado;
            No<T> proximo;

            No(T dado) {
                this.dado = dado;
            }
        }

        private No<T> topo; // último elemento inserido (primeiro a sair)
        private int tamanho;

        // Empilha: insere no topo - O(1)
        public void push(T elemento) {
            No<T> novo = new No<>(elemento);
            novo.proximo = topo;
            topo = novo;
            tamanho++;
        }

        // Desempilha: remove e retorna o elemento do topo - O(1)
        public T pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Pilha vazia: não há elemento para remover.");
            }
            T dado = topo.dado;
            topo = topo.proximo;
            tamanho--;
            return dado;
        }

        // Consulta o topo sem remover - O(1)
        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Pilha vazia: não há elemento no topo.");
            }
            return topo.dado;
        }

        public boolean isEmpty() {
            return tamanho == 0;
        }

        public int size() {
            return tamanho;
        }

        // Esvazia a pilha - O(1)
        public void clear() {
            topo = null;
            tamanho = 0;
        }

        // Mostra a pilha do topo para a base, sem alterá-la
        @Override
        public String toString() {
            if (isEmpty()) {
                return "[]";
            }
            StringBuilder sb = new StringBuilder("[topo: ");
            No<T> atual = topo;
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
