/**
 * QUESTÃO 14 - Contagem de Frequência de Palavras em um Texto
 *
 * Reutiliza a classe EstruturaTabelaHash (encadeamento separado):
 *   - frequencias: chave = palavra, valor = quantidade de ocorrências;
 *   - stopwords:   chave = palavra comum ("de", "a", "que"...), valor = true.
 *                  Usada como CONJUNTO: só interessa saber se a chave existe.
 *
 * Justificativa da estrutura: para cada palavra lida é preciso achar o seu
 * contador e incrementá-lo, e a ordem das palavras não importa durante a
 * contagem. A tabela hash faz esse "buscar + atualizar" em O(1) médio, o que
 * torna a contagem linear no tamanho do texto. Com uma lista seria O(n * d)
 * (d = palavras distintas).
 *
 * Complexidade (n = palavras do texto, d = palavras distintas, K = tamanho do ranking):
 *   - processar texto ........ O(n) médio (cada palavra: 1 busca + 1 inserção)
 *   - topK ................... O(d * K)  (seleção parcial, sem ordenar tudo)
 *   - total de distintas ..... O(1)
 *   - Espaço ................. O(d)
 */
package naolineareshash.questao14;

import naolineareshash.EstruturaTabelaHash;

public class AnalisadorFrequencia {

    /** Palavra e quantas vezes apareceu. */
    public static class PalavraFrequencia {
        private final String palavra;
        private final int contagem;

        PalavraFrequencia(String palavra, int contagem) {
            this.palavra = palavra;
            this.contagem = contagem;
        }

        public String getPalavra() {
            return palavra;
        }

        public int getContagem() {
            return contagem;
        }
    }

    private static final String[] STOPWORDS_PADRAO = {
            "a", "o", "as", "os", "um", "uma", "uns", "umas", "de", "do", "da", "dos", "das",
            "em", "no", "na", "nos", "nas", "por", "para", "com", "sem", "sob", "sobre",
            "e", "ou", "mas", "que", "se", "como", "mais", "já", "não", "nem", "ao", "aos",
            "à", "às", "pelo", "pela", "pelos", "pelas", "é", "são", "foi", "ser", "há",
            "seu", "sua", "seus", "suas", "ele", "ela", "eles", "elas", "isso", "isto",
            "esse", "essa", "esses", "essas", "este", "esta", "estes", "estas", "num", "numa"
    };

    private final EstruturaTabelaHash<Integer> frequencias;
    private final EstruturaTabelaHash<Boolean> stopwords;
    private final boolean ignorarStopwords;

    private int totalPalavras;          // palavras contadas (após o filtro)
    private int totalPalavrasIgnoradas; // stopwords descartadas

    public AnalisadorFrequencia(boolean ignorarStopwords) {
        this.frequencias = new EstruturaTabelaHash<Integer>();
        this.stopwords = new EstruturaTabelaHash<Boolean>();
        this.ignorarStopwords = ignorarStopwords;
        for (String s : STOPWORDS_PADRAO) {
            stopwords.inserir(s, Boolean.TRUE);
        }
    }

    // ------------------------------------------------------------------
    // Processamento do texto
    // ------------------------------------------------------------------

    /**
     * Lê o texto caractere a caractere, separando palavras em qualquer coisa
     * que não seja letra (espaço, pontuação, dígito, hífen). Tudo é convertido
     * para minúsculas, então "Casa" e "casa" são a mesma palavra.
     */
    public void processar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("O texto não pode ser vazio.");
        }

        StringBuilder atual = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isLetter(c)) {
                atual.append(Character.toLowerCase(c));
            } else if (atual.length() > 0) {
                registrar(atual.toString());
                atual.setLength(0);
            }
        }
        if (atual.length() > 0) {
            registrar(atual.toString());
        }
    }

    /** Conta uma ocorrência da palavra (ou descarta, se for stopword). */
    private void registrar(String palavra) {
        if (ignorarStopwords && stopwords.contem(palavra)) {
            totalPalavrasIgnoradas++;
            return;
        }
        Integer contagem = frequencias.buscar(palavra);
        frequencias.inserir(palavra, contagem == null ? 1 : contagem + 1);
        totalPalavras++;
    }

    // ------------------------------------------------------------------
    // Consultas
    // ------------------------------------------------------------------

    public int getTotalPalavras() {
        return totalPalavras;
    }

    public int getTotalPalavrasIgnoradas() {
        return totalPalavrasIgnoradas;
    }

    public int getTotalPalavrasDistintas() {
        return frequencias.tamanho();
    }

    /** Quantas vezes a palavra apareceu (0 se nunca apareceu). */
    public int frequenciaDe(String palavra) {
        if (palavra == null || palavra.trim().isEmpty()) {
            throw new IllegalArgumentException("A palavra não pode ser vazia.");
        }
        Integer contagem = frequencias.buscar(palavra.trim().toLowerCase());
        return contagem == null ? 0 : contagem;
    }

    /**
     * As k palavras mais frequentes, da maior para a menor contagem.
     * Empates são desfeitos em ordem alfabética, para o resultado ser estável.
     * Usa seleção parcial: mantém um ranking de tamanho k e insere cada palavra
     * na posição certa, sem ordenar as d palavras.
     */
    public PalavraFrequencia[] topK(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k deve ser positivo: " + k);
        }
        if (frequencias.estaVazia()) {
            throw new IllegalStateException("Nenhum texto foi processado ainda.");
        }

        String[] palavras = frequencias.chaves();
        int tamanhoRanking = Math.min(k, palavras.length);
        PalavraFrequencia[] ranking = new PalavraFrequencia[tamanhoRanking];
        int preenchido = 0;

        for (String palavra : palavras) {
            PalavraFrequencia candidata = new PalavraFrequencia(palavra, frequencias.buscar(palavra));

            if (preenchido == tamanhoRanking && !vemAntes(candidata, ranking[preenchido - 1])) {
                continue; // não entra no ranking
            }

            // Inserção ordenada: desloca para baixo quem fica depois da candidata.
            int posicao = (preenchido < tamanhoRanking) ? preenchido : preenchido - 1;
            while (posicao > 0 && vemAntes(candidata, ranking[posicao - 1])) {
                ranking[posicao] = ranking[posicao - 1];
                posicao--;
            }
            ranking[posicao] = candidata;
            if (preenchido < tamanhoRanking) {
                preenchido++;
            }
        }
        return ranking;
    }

    /** Estatísticas da tabela hash interna (para o relatório). */
    public String resumoDaTabela() {
        return String.format("capacidade %d | fator de carga %.2f | colisões %d | maior cadeia %d",
                frequencias.getCapacidade(), frequencias.getFatorDeCarga(),
                frequencias.getTotalDeColisoes(), frequencias.getMaiorCadeia());
    }

    // ------------------------------------------------------------------
    // Auxiliares
    // ------------------------------------------------------------------

    /** a vem antes de b se tem mais ocorrências, ou, empatando, vem antes no alfabeto. */
    private boolean vemAntes(PalavraFrequencia a, PalavraFrequencia b) {
        if (a.contagem != b.contagem) {
            return a.contagem > b.contagem;
        }
        return a.palavra.compareTo(b.palavra) < 0;
    }
}