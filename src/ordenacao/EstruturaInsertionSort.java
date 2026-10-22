/**
 * ALGORITMO: Insertion Sort (ordenação por inserção), genérico.
 *
 * Funcionamento: mantém a parte esquerda do vetor ordenada. Pega o próximo
 * elemento e o desliza para a esquerda, deslocando os maiores uma posição
 * para a direita, até achar o seu lugar.
 *
 * Propriedades:
 *   - Estável (só desloca elementos ESTRITAMENTE fora de ordem).
 *   - In-place.
 *   - Muito rápido em vetores pequenos ou quase ordenados.
 *
 * Complexidade (n = tamanho do vetor):
 *   - melhor caso (já ordenado): O(n), com n-1 comparações e 0 deslocamentos;
 *   - caso médio e pior caso (ordem inversa): O(n²);
 *   - espaço: O(1).
 *
 * Na contagem de "trocas", cada deslocamento de um elemento conta como 1.
 */
package ordenacao;

public class EstruturaInsertionSort {

    private EstruturaInsertionSort() {
    }

    public static <T extends Comparable<T>> ResultadoOrdenacao ordenar(T[] vetor, boolean crescente) {
        if (vetor == null) {
            throw new IllegalArgumentException("O vetor não pode ser nulo.");
        }
        if (vetor.length == 0) {
            throw new IllegalArgumentException("O vetor está vazio: não há o que ordenar.");
        }
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == null) {
                throw new IllegalArgumentException("Elemento nulo na posição " + i + ".");
            }
        }

        ResultadoOrdenacao resultado = new ResultadoOrdenacao("Insertion Sort");

        for (int i = 1; i < vetor.length; i++) {
            resultado.contarPassada();
            T chave = vetor[i];
            int j = i - 1;
            while (j >= 0) {
                resultado.contarComparacao();
                int comparacao = vetor[j].compareTo(chave);
                boolean foraDeOrdem = crescente ? comparacao > 0 : comparacao < 0;
                if (!foraDeOrdem) {
                    break;
                }
                vetor[j + 1] = vetor[j]; // desloca para a direita
                resultado.contarTroca();
                j--;
            }
            vetor[j + 1] = chave;
        }
        return resultado;
    }
}