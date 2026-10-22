/**
 * QUESTÃO 15 - Ordenação de Notas de uma Turma Pequena
 *
 * Guarda os alunos em um vetor (turma de até 30 alunos) e usa o Bubble Sort
 * otimizado da classe EstruturaBubbleSort para ordená-los por nota.
 *
 * Justificativa do algoritmo: com no máximo 30 alunos, o custo O(n²) é
 * irrelevante (no máximo 435 comparações), e o Bubble Sort otimizado é simples,
 * estável (alunos com a mesma nota mantêm a ordem de chamada) e termina na
 * primeira passada se a lista já estiver ordenada.
 *
 * Depois de ordenada, as estatísticas ficam baratas:
 *   - menor e maior nota: O(1) (primeira e última posição);
 *   - mediana: O(1) (posição do meio);
 *   - média e aprovados: O(n) (uma varredura).
 */
package ordenacao.Questao15;

import ordenacao.ResultadoOrdenacao;
import ordenacao.EstruturaBubbleSort;

public class Turma {

    public static final int CAPACIDADE_MAXIMA = 30;
    public static final double NOTA_DE_APROVACAO = 6.0;

    private final Aluno[] alunos;
    private int quantidade;
    private boolean ordenada;

    public Turma() {
        this.alunos = new Aluno[CAPACIDADE_MAXIMA];
        this.quantidade = 0;
        this.ordenada = true; // turma vazia está trivialmente ordenada
    }

    public void adicionar(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("O aluno não pode ser nulo.");
        }
        if (quantidade == CAPACIDADE_MAXIMA) {
            throw new IllegalStateException("A turma está cheia (máximo " + CAPACIDADE_MAXIMA + " alunos).");
        }
        alunos[quantidade++] = aluno;
        ordenada = false;
    }

    public int getQuantidade() {
        return quantidade;
    }

    /** Cópia dos alunos na ordem atual (para o chamador não alterar o vetor interno). */
    public Aluno[] getAlunos() {
        Aluno[] copia = new Aluno[quantidade];
        for (int i = 0; i < quantidade; i++) {
            copia[i] = alunos[i];
        }
        return copia;
    }

    /** Ordena a turma por nota com Bubble Sort otimizado. */
    public ResultadoOrdenacao ordenarPorNota(boolean crescente) {
        verificarNaoVazia();
        Aluno[] ativos = getAlunos();
        ResultadoOrdenacao resultado = EstruturaBubbleSort.ordenarOtimizado(ativos, crescente);
        for (int i = 0; i < quantidade; i++) {
            alunos[i] = ativos[i];
        }
        ordenada = crescente; // as estatísticas abaixo assumem ordem crescente
        return resultado;
    }

    // ------------------------------------------------------------------
    // Estatísticas (exigem a turma ordenada de forma crescente)
    // ------------------------------------------------------------------

    public double menorNota() {
        exigirOrdemCrescente();
        return alunos[0].getNota();
    }

    public double maiorNota() {
        exigirOrdemCrescente();
        return alunos[quantidade - 1].getNota();
    }

    public double mediana() {
        exigirOrdemCrescente();
        int meio = quantidade / 2;
        if (quantidade % 2 == 1) {
            return alunos[meio].getNota();
        }
        return (alunos[meio - 1].getNota() + alunos[meio].getNota()) / 2.0;
    }

    public double media() {
        verificarNaoVazia();
        double soma = 0;
        for (int i = 0; i < quantidade; i++) {
            soma += alunos[i].getNota();
        }
        return soma / quantidade;
    }

    public int totalAprovados() {
        verificarNaoVazia();
        int aprovados = 0;
        for (int i = 0; i < quantidade; i++) {
            if (alunos[i].getNota() >= NOTA_DE_APROVACAO) {
                aprovados++;
            }
        }
        return aprovados;
    }

    // ------------------------------------------------------------------
    // Auxiliares
    // ------------------------------------------------------------------

    private void verificarNaoVazia() {
        if (quantidade == 0) {
            throw new IllegalStateException("A turma está vazia.");
        }
    }

    private void exigirOrdemCrescente() {
        verificarNaoVazia();
        if (!ordenada) {
            throw new IllegalStateException("Ordene a turma por nota (crescente) antes de pedir esta estatística.");
        }
    }
}