/**
 * QUESTÃO 15 - Simulação: turma de 25 alunos.
 *
 * 1) Ordena com Bubble Sort otimizado e mostra a turma e as estatísticas.
 * 2) Compara comparações e trocas de 4 algoritmos (Bubble simples, Bubble
 *    otimizado, Selection e Insertion) em três cenários: notas aleatórias,
 *    lista já ordenada e lista em ordem inversa.
 * 3) Verifica a estabilidade e testa os casos de erro.
 */
package ordenacao.Questao15;

import ordenacao.EstruturaBubbleSort;
import ordenacao.EstruturaInsertionSort;
import ordenacao.EstruturaSelectionSort;
import ordenacao.ResultadoOrdenacao;

import java.util.Random;

public class Main {

    private static final String[] NOMES = {
            "Ana", "Bruno", "Carla", "Diego", "Elisa", "Felipe", "Gabriela", "Hugo", "Isabela",
            "João", "Karina", "Lucas", "Marina", "Nicolas", "Olívia", "Paulo", "Renata", "Sofia",
            "Thiago", "Úrsula", "Victor", "Wanda", "Xavier", "Yara", "Zeca"
    };

    public static void main(String[] args) {
        // Semente fixa: o resultado é sempre o mesmo a cada execução.
        Random sorteio = new Random(42);
        Turma turma = new Turma();
        for (String nome : NOMES) {
            turma.adicionar(new Aluno(nome, sorteio.nextInt(21) / 2.0)); // 0,0 a 10,0 em passos de 0,5
        }

        System.out.println("=== TURMA DE " + turma.getQuantidade() + " ALUNOS ===");
        System.out.println("\n--- Ordem de chamada (original) ---");
        imprimir(turma.getAlunos());

        Aluno[] original = turma.getAlunos();

        ResultadoOrdenacao r = turma.ordenarPorNota(true);
        System.out.println("\n--- Ordenada por nota (crescente) com " + r.getAlgoritmo() + " ---");
        imprimir(turma.getAlunos());
        System.out.println("Comparações: " + r.getComparacoes() + " | Trocas: " + r.getTrocas()
                + " | Passadas: " + r.getPassadas());

        System.out.println("\n--- Estatísticas ---");
        System.out.println(String.format("Menor nota: %.1f | Maior nota: %.1f | Mediana: %.2f | Média: %.2f",
                turma.menorNota(), turma.maiorNota(), turma.mediana(), turma.media()));
        System.out.println("Aprovados (nota >= " + Turma.NOTA_DE_APROVACAO + "): "
                + turma.totalAprovados() + " de " + turma.getQuantidade());

        turma.ordenarPorNota(false);
        System.out.println("\n--- Ordenada por nota (decrescente): top 5 ---");
        Aluno[] decrescente = turma.getAlunos();
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + (i + 1) + "º " + decrescente[i]);
        }

        // ------------------------------------------------------------------
        // Comparação entre algoritmos
        // ------------------------------------------------------------------
        System.out.println("\n=== COMPARAÇÃO DE ALGORITMOS (n = " + original.length + ") ===");

        Aluno[] aleatoria = copiar(original);
        Aluno[] jaOrdenada = copiar(original);
        EstruturaBubbleSort.ordenarOtimizado(jaOrdenada, true);
        Aluno[] inversa = copiar(original);
        EstruturaBubbleSort.ordenarOtimizado(inversa, false);

        compararAlgoritmos("Notas aleatórias", aleatoria);
        compararAlgoritmos("Lista já ordenada (melhor caso)", jaOrdenada);
        compararAlgoritmos("Lista em ordem inversa (pior caso)", inversa);

        System.out.println("\nLeitura: com a lista já ordenada, o Bubble otimizado e o Insertion fazem só n-1 = "
                + (original.length - 1) + " comparações; o Bubble simples e o Selection continuam fazendo n(n-1)/2 = "
                + (original.length * (original.length - 1) / 2) + ". O Selection faz sempre poucas trocas.");

        // ------------------------------------------------------------------
        // Estabilidade
        // ------------------------------------------------------------------
        System.out.println("\n=== ESTABILIDADE (notas iguais mantêm a ordem de chamada?) ===");
        Aluno[] porBubble = copiar(original);
        EstruturaBubbleSort.ordenarOtimizado(porBubble, true);
        Aluno[] porSelection = copiar(original);
        EstruturaSelectionSort.ordenar(porSelection, true);
        Aluno[] porInsertion = copiar(original);
        EstruturaInsertionSort.ordenar(porInsertion, true);
        System.out.println("Bubble Sort:    " + (ehEstavel(original, porBubble) ? "estável" : "NÃO estável"));
        System.out.println("Insertion Sort: " + (ehEstavel(original, porInsertion) ? "estável" : "NÃO estável"));
        System.out.println("Selection Sort: " + (ehEstavel(original, porSelection) ? "estável" : "NÃO estável")
                + " (nesta turma; o algoritmo em geral não garante estabilidade)");

        // ------------------------------------------------------------------
        // Erros
        // ------------------------------------------------------------------
        System.out.println("\n=== TRATAMENTO DE ERROS ===");
        tentar("Ordenar turma vazia", () -> new Turma().ordenarPorNota(true));
        tentar("Nota inválida", () -> new Aluno("Fulano", 11));
        tentar("Nome vazio", () -> new Aluno("  ", 5));
        tentar("Aluno nulo", () -> new Turma().adicionar(null));
        tentar("Mediana sem ordenar", () -> {
            Turma t = new Turma();
            t.adicionar(new Aluno("Ana", 7));
            t.adicionar(new Aluno("Bia", 5));
            t.mediana();
        });
        tentar("Turma com mais de 30 alunos", () -> {
            Turma t = new Turma();
            for (int i = 0; i < 31; i++) {
                t.adicionar(new Aluno("Aluno" + i, 5));
            }
        });
        tentar("Vetor nulo", () -> EstruturaBubbleSort.ordenarSimples((Aluno[]) null, true));
    }

    // ------------------------------------------------------------------
    // Auxiliares
    // ------------------------------------------------------------------

    private static void compararAlgoritmos(String cenario, Aluno[] base) {
        System.out.println("\n" + cenario);
        System.out.println(String.format("  %-24s %12s %8s %10s", "Algoritmo", "Comparações", "Trocas", "Passadas"));
        ResultadoOrdenacao[] resultados = {
                EstruturaBubbleSort.ordenarSimples(copiar(base), true),
                EstruturaBubbleSort.ordenarOtimizado(copiar(base), true),
                EstruturaSelectionSort.ordenar(copiar(base), true),
                EstruturaInsertionSort.ordenar(copiar(base), true)
        };
        for (ResultadoOrdenacao r : resultados) {
            System.out.println(String.format("  %-24s %12d %8d %10d",
                    r.getAlgoritmo(), r.getComparacoes(), r.getTrocas(), r.getPassadas()));
        }
    }

    /** Estável = para notas iguais, a ordem relativa original foi preservada. */
    private static boolean ehEstavel(Aluno[] original, Aluno[] ordenado) {
        for (int i = 0; i < ordenado.length - 1; i++) {
            if (ordenado[i].getNota() == ordenado[i + 1].getNota()
                    && posicaoOriginal(original, ordenado[i]) > posicaoOriginal(original, ordenado[i + 1])) {
                return false;
            }
        }
        return true;
    }

    private static int posicaoOriginal(Aluno[] original, Aluno aluno) {
        for (int i = 0; i < original.length; i++) {
            if (original[i] == aluno) {
                return i;
            }
        }
        return -1;
    }

    private static Aluno[] copiar(Aluno[] origem) {
        Aluno[] copia = new Aluno[origem.length];
        for (int i = 0; i < origem.length; i++) {
            copia[i] = origem[i];
        }
        return copia;
    }

    private static void imprimir(Aluno[] alunos) {
        for (int i = 0; i < alunos.length; i++) {
            System.out.println(String.format("  %2d. %s", i + 1, alunos[i]));
        }
    }

    private static void tentar(String descricao, Runnable acao) {
        try {
            acao.run();
            System.out.println(descricao + ": OK");
        } catch (RuntimeException e) {
            System.out.println(descricao + ": erro tratado -> " + e.getMessage());
        }
    }
}