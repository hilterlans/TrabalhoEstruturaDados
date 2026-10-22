/**
 * Resultado de uma ordenação: guarda as métricas usadas para comparar algoritmos.
 *
 *   - comparacoes: quantas vezes dois elementos foram comparados (compareTo);
 *   - trocas:      quantas vezes um elemento foi movido de lugar. No Bubble e no
 *                  Selection é uma troca entre dois elementos; no Insertion é um
 *                  deslocamento de um elemento uma posição para a direita;
 *   - passadas:    quantas passadas externas o algoritmo executou.
 */
package ordenacao;

public class ResultadoOrdenacao {

    private final String algoritmo;
    private int comparacoes;
    private int trocas;
    private int passadas;

    public ResultadoOrdenacao(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    public void contarComparacao() {
        comparacoes++;
    }

    public void contarTroca() {
        trocas++;
    }

    public void contarPassada() {
        passadas++;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public int getComparacoes() {
        return comparacoes;
    }

    public int getTrocas() {
        return trocas;
    }

    public int getPassadas() {
        return passadas;
    }
}