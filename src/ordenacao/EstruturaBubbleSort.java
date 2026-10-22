/**
 * ALGORITMO: Bubble Sort (ordenação por flutuação), genérico.
 *
 * Funcionamento: percorre o vetor comparando pares de vizinhos e trocando os
 * que estão fora de ordem. A cada passada, o maior elemento restante "sobe"
 * como uma bolha até a sua posição final no fim do vetor.
 *
 * Duas versões:
 *   - ordenarSimples ......... sempre executa todas as passadas;
 *   - ordenarOtimizado ....... para assim que uma passada completa termina sem
 *                              nenhuma troca (o vetor já está ordenado).
 *
 * Propriedades:
 *   - Estável: só troca quando o primeiro é ESTRITAMENTE fora de ordem, então
 *     elementos iguais mantêm a ordem original.
 *   - In-place: usa apenas um elemento auxiliar para a troca.
 *
 * Complexidade (n = tamanho do vetor):
 *                          melhor caso   caso médio   pior caso   espaço
 *   versão simples ........ O(n²)         O(n²)        O(n²)      O(1)
 *   versão otimizada ...... O(n)          O(n²)        O(n²)      O(1)
 *   (o melhor caso da otimizada é um vetor que já veio ordenado: 1 passada)
 */
package ordenacao;

public class EstruturaBubbleSort {

    private EstruturaBubbleSort() {
    }

    /** Bubble Sort sem parada antecipada: n-1 passadas, sempre. */
    public static <T extends Comparable<T>> ResultadoOrdenacao ordenarSimples(T[] vetor, boolean crescente) {
        validar(vetor);
        ResultadoOrdenacao resultado = new ResultadoOrdenacao("Bubble Sort (simples)");

        for (int passada = 0; passada < vetor.length - 1; passada++) {
            resultado.contarPassada();
            // O fim do vetor (últimas 'passada' posições) já está definitivo.
            for (int j = 0; j < vetor.length - 1 - passada; j++) {
                resultado.contarComparacao();
                if (foraDeOrdem(vetor[j], vetor[j + 1], crescente)) {
                    trocar(vetor, j, j + 1);
                    resultado.contarTroca();
                }
            }
        }
        return resultado;
    }

    /** Bubble Sort com parada antecipada: se uma passada não trocou nada, termina. */
    public static <T extends Comparable<T>> ResultadoOrdenacao ordenarOtimizado(T[] vetor, boolean crescente) {
        validar(vetor);
        ResultadoOrdenacao resultado = new ResultadoOrdenacao("Bubble Sort (otimizado)");

        boolean houveTroca = true;
        for (int passada = 0; passada < vetor.length - 1 && houveTroca; passada++) {
            resultado.contarPassada();
            houveTroca = false;
            for (int j = 0; j < vetor.length - 1 - passada; j++) {
                resultado.contarComparacao();
                if (foraDeOrdem(vetor[j], vetor[j + 1], crescente)) {
                    trocar(vetor, j, j + 1);
                    resultado.contarTroca();
                    houveTroca = true;
                }
            }
        }
        return resultado;
    }

    // ------------------------------------------------------------------
    // Auxiliares
    // ------------------------------------------------------------------

    /** Estritamente fora de ordem (iguais NÃO trocam, para manter a estabilidade). */
    private static <T extends Comparable<T>> boolean foraDeOrdem(T a, T b, boolean crescente) {
        int comparacao = a.compareTo(b);
        return crescente ? comparacao > 0 : comparacao < 0;
    }

    private static <T> void trocar(T[] vetor, int i, int j) {
        T auxiliar = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = auxiliar;
    }

    private static <T> void validar(T[] vetor) {
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
    }
}