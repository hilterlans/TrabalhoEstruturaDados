/**
 * QUESTÃO 14 - Simulação: conta as palavras de um texto, mostra as mais
 * frequentes e o total de palavras distintas. Compara a contagem com e sem
 * o filtro de stopwords e testa os casos de erro.
 *
 * Uso: sem argumentos analisa o TextoExemplo; com um argumento, lê esse
 * arquivo .txt (codificação UTF-8).
 */
package naolineareshash.questao14;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static final int TAMANHO_RANKING = 10;

    public static void main(String[] args) throws IOException {
        String texto;
        String origem;
        if (args.length > 0) {
            texto = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8);
            origem = "arquivo " + args[0];
        } else {
            texto = TextoExemplo.ARTIGO;
            origem = "texto de exemplo (artigo sobre tabelas hash)";
        }

        System.out.println("=== ANÁLISE DE FREQUÊNCIA DE PALAVRAS ===");
        System.out.println("Fonte: " + origem);
        System.out.println("Caracteres no texto: " + texto.length());

        // 1) Contagem sem filtro: toda palavra conta.
        System.out.println("\n--- Sem filtro (todas as palavras) ---");
        AnalisadorFrequencia completo = new AnalisadorFrequencia(false);
        completo.processar(texto);
        imprimirResultado(completo);

        // 2) Contagem ignorando stopwords: o ranking mostra o assunto do texto.
        System.out.println("\n--- Ignorando stopwords (palavras de ligação) ---");
        AnalisadorFrequencia filtrado = new AnalisadorFrequencia(true);
        filtrado.processar(texto);
        imprimirResultado(filtrado);
        System.out.println("Stopwords descartadas: " + filtrado.getTotalPalavrasIgnoradas());

        // 3) Consulta de palavras específicas.
        System.out.println("\n--- Consulta por palavra (sem filtro) ---");
        String[] consultas = {"tabela", "Tabela", "colisão", "chave", "grafo"};
        for (String palavra : consultas) {
            System.out.println("\"" + palavra + "\": " + completo.frequenciaDe(palavra) + " ocorrência(s)");
        }

        // 4) Tratamento de erros.
        System.out.println("\n--- Tratamento de erros ---");
        tentar("Texto vazio", () -> new AnalisadorFrequencia(false).processar("   "));
        tentar("Texto nulo", () -> new AnalisadorFrequencia(false).processar(null));
        tentar("Texto só com símbolos", () -> {
            AnalisadorFrequencia a = new AnalisadorFrequencia(false);
            a.processar("123 !!! ---");
            a.topK(3); // nada foi contado
        });
        tentar("k inválido", () -> completo.topK(0));
        tentar("Palavra vazia na consulta", () -> completo.frequenciaDe(""));
    }

    private static void imprimirResultado(AnalisadorFrequencia analisador) {
        System.out.println("Total de palavras contadas: " + analisador.getTotalPalavras());
        System.out.println("Total de palavras distintas: " + analisador.getTotalPalavrasDistintas());
        System.out.println("Tabela hash: " + analisador.resumoDaTabela());
        System.out.println("Top " + TAMANHO_RANKING + ":");

        AnalisadorFrequencia.PalavraFrequencia[] ranking = analisador.topK(TAMANHO_RANKING);
        for (int i = 0; i < ranking.length; i++) {
            System.out.println(String.format("  %2d. %-16s %3d  %s", i + 1,
                    ranking[i].getPalavra(), ranking[i].getContagem(),
                    barra(ranking[i].getContagem())));
        }
    }

    /** Barrinha de '#' proporcional à contagem, só para visualizar. */
    private static String barra(int contagem) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < contagem && i < 40; i++) {
            b.append('#');
        }
        return b.toString();
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