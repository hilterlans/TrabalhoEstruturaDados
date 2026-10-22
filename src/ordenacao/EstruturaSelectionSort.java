/**
 * ALGORITMO: Selection Sort (ordenação por seleção), genérico.
 *
 * Funcionamento: a cada passada procura o menor (ou maior) elemento da parte
 * ainda não ordenada e o troca com o primeiro dessa parte. Faz no máximo
 * n-1 trocas, o que é muito menos que o Bubble Sort.
 *
 * Propriedades:
 *   - NÃO é estável (a troca à distância pode inverter elementos iguais).
 *   - In-place.
 *
 * Complexidade (n = tamanho do vetor):
 *   - comparações: sempre n(n-1)/2, mesmo com o vetor já ordenado;
 *   - tempo: O(n²) no melhor, médio e pior caso;
 *   - trocas: no máximo n-1, ou seja, O(n);
 *   - espaço: O(1).
 */
package ordenacao;

public class EstruturaSelectionSort {

    private EstruturaSelectionSort() {
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

        ResultadoOrdenacao resultado = new ResultadoOrdenacao("Selection Sort");

        for (int i = 0; i < vetor.length - 1; i++) {
            resultado.contarPassada();
            int escolhido = i; // posição do menor (ou maior) visto até agora
            for (int j = i + 1; j < vetor.length; j++) {
                resultado.contarComparacao();
                int comparacao = vetor[j].compareTo(vetor[escolhido]);
                if (crescente ? comparacao < 0 : comparacao > 0) {
                    escolhido = j;
                }
            }
            if (escolhido != i) { // evita troca inútil
                T auxiliar = vetor[i];
                vetor[i] = vetor[escolhido];
                vetor[escolhido] = auxiliar;
                resultado.contarTroca();
            }
        }
        return resultado;
    }
}