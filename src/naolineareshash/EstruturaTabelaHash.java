/**
 * ESTRUTURA DE DADOS: Tabela Hash (encadeamento separado)
 *
 * Representação:
 *   - Um vetor de "baldes" (buckets). Cada balde guarda uma lista encadeada
 *     de nós (chave, valor), usada para tratar colisões.
 *   - Chaves são Strings; os valores são genéricos (tipo V).
 *   - Função de espalhamento: polinomial (h = h * 31 + caractere), reduzida
 *     ao tamanho do vetor com módulo.
 *   - Redimensionamento automático (dobra a capacidade e reinsere os nós)
 *     quando o fator de carga (elementos / capacidade) passa do limite.
 *
 * Complexidade (n = elementos, m = capacidade, k = tamanho da chave):
 *   - inserir / buscar / remover ... O(k) em média (cadeias curtas);
 *                                    O(n) no pior caso (todas as chaves no mesmo balde)
 *   - redimensionar ................ O(n + m), mas amortizado O(1) por inserção
 *   - chaves() ..................... O(n + m)
 *   - Espaço ....................... O(n + m)
 */
package naolineareshash;

public class EstruturaTabelaHash<V> {

    /** Nó da lista encadeada de um balde. */
    private static class No<V> {
        final String chave;
        V valor;
        No<V> proximo;

        No(String chave, V valor, No<V> proximo) {
            this.chave = chave;
            this.valor = valor;
            this.proximo = proximo;
        }
    }

    private static final int CAPACIDADE_PADRAO = 16;
    private static final double FATOR_CARGA_PADRAO = 0.75;

    private No<V>[] tabela;
    private int tamanho;
    private final double fatorCargaMaximo;

    public EstruturaTabelaHash() {
        this(CAPACIDADE_PADRAO, FATOR_CARGA_PADRAO);
    }

    public EstruturaTabelaHash(int capacidadeInicial, double fatorCargaMaximo) {
        if (capacidadeInicial <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser positiva: " + capacidadeInicial);
        }
        if (fatorCargaMaximo <= 0) {
            throw new IllegalArgumentException("Fator de carga máximo deve ser positivo: " + fatorCargaMaximo);
        }
        this.tabela = criarTabela(capacidadeInicial);
        this.tamanho = 0;
        this.fatorCargaMaximo = fatorCargaMaximo;
    }

    // ------------------------------------------------------------------
    // Operações principais
    // ------------------------------------------------------------------

    /**
     * Insere o par (chave, valor). Se a chave já existe, o valor é substituído.
     * Retorna true se a chave era nova e false se apenas atualizou o valor.
     */
    public boolean inserir(String chave, V valor) {
        validarChave(chave);

        int posicao = indice(chave, tabela.length);
        for (No<V> atual = tabela[posicao]; atual != null; atual = atual.proximo) {
            if (atual.chave.equals(chave)) {
                atual.valor = valor;
                return false;
            }
        }

        // Insere no início da cadeia: O(1).
        tabela[posicao] = new No<V>(chave, valor, tabela[posicao]);
        tamanho++;

        if (getFatorDeCarga() > fatorCargaMaximo) {
            redimensionar();
        }
        return true;
    }

    /** Devolve o valor associado à chave, ou null se a chave não existe. */
    public V buscar(String chave) {
        validarChave(chave);
        int posicao = indice(chave, tabela.length);
        for (No<V> atual = tabela[posicao]; atual != null; atual = atual.proximo) {
            if (atual.chave.equals(chave)) {
                return atual.valor;
            }
        }
        return null;
    }

    public boolean contem(String chave) {
        validarChave(chave);
        int posicao = indice(chave, tabela.length);
        for (No<V> atual = tabela[posicao]; atual != null; atual = atual.proximo) {
            if (atual.chave.equals(chave)) {
                return true;
            }
        }
        return false;
    }

    /** Remove a chave e devolve o valor removido, ou null se ela não existia. */
    public V remover(String chave) {
        validarChave(chave);
        int posicao = indice(chave, tabela.length);
        No<V> anterior = null;
        for (No<V> atual = tabela[posicao]; atual != null; atual = atual.proximo) {
            if (atual.chave.equals(chave)) {
                if (anterior == null) {
                    tabela[posicao] = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                tamanho--;
                return atual.valor;
            }
            anterior = atual;
        }
        return null;
    }

    // ------------------------------------------------------------------
    // Consultas e estatísticas
    // ------------------------------------------------------------------

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int getCapacidade() {
        return tabela.length;
    }

    public double getFatorDeCarga() {
        return (double) tamanho / tabela.length;
    }

    /** Quantidade de elementos que dividem balde com outro (elementos além do 1º de cada balde). */
    public int getTotalDeColisoes() {
        int colisoes = 0;
        for (int i = 0; i < tabela.length; i++) {
            int cadeia = tamanhoDaCadeia(i);
            if (cadeia > 1) {
                colisoes += cadeia - 1;
            }
        }
        return colisoes;
    }

    /** Tamanho da maior cadeia (pior caso de busca). */
    public int getMaiorCadeia() {
        int maior = 0;
        for (int i = 0; i < tabela.length; i++) {
            int cadeia = tamanhoDaCadeia(i);
            if (cadeia > maior) {
                maior = cadeia;
            }
        }
        return maior;
    }

    /** Todas as chaves, na ordem dos baldes (a ordem NÃO é a de inserção). */
    public String[] chaves() {
        String[] resultado = new String[tamanho];
        int i = 0;
        for (int b = 0; b < tabela.length; b++) {
            for (No<V> atual = tabela[b]; atual != null; atual = atual.proximo) {
                resultado[i++] = atual.chave;
            }
        }
        return resultado;
    }

    /** Imprime os baldes que têm elementos: [balde] -> chave -> chave ... */
    public void imprimirEstrutura() {
        for (int b = 0; b < tabela.length; b++) {
            if (tabela[b] == null) {
                continue;
            }
            StringBuilder linha = new StringBuilder();
            linha.append(String.format("  [%2d] ", b));
            for (No<V> atual = tabela[b]; atual != null; atual = atual.proximo) {
                linha.append(atual.chave);
                if (atual.proximo != null) {
                    linha.append(" -> ");
                }
            }
            System.out.println(linha);
        }
    }

    // ------------------------------------------------------------------
    // Auxiliares privados
    // ------------------------------------------------------------------

    /** Função de espalhamento polinomial: h = h * 31 + c (com overflow de int). */
    private int indice(String chave, int capacidade) {
        int hash = 0;
        for (int i = 0; i < chave.length(); i++) {
            hash = hash * 31 + chave.charAt(i);
        }
        return (hash & 0x7fffffff) % capacidade; // & remove o sinal
    }

    /** Dobra a capacidade e reposiciona os nós existentes. */
    private void redimensionar() {
        No<V>[] antiga = tabela;
        No<V>[] nova = criarTabela(antiga.length * 2);
        for (int b = 0; b < antiga.length; b++) {
            No<V> atual = antiga[b];
            while (atual != null) {
                No<V> proximo = atual.proximo;
                int posicao = indice(atual.chave, nova.length);
                atual.proximo = nova[posicao];
                nova[posicao] = atual;
                atual = proximo;
            }
        }
        tabela = nova;
    }

    private int tamanhoDaCadeia(int balde) {
        int contagem = 0;
        for (No<V> atual = tabela[balde]; atual != null; atual = atual.proximo) {
            contagem++;
        }
        return contagem;
    }

    private void validarChave(String chave) {
        if (chave == null || chave.isEmpty()) {
            throw new IllegalArgumentException("Chave não pode ser nula ou vazia.");
        }
    }

    @SuppressWarnings("unchecked")
    private No<V>[] criarTabela(int capacidade) {
        return (No<V>[]) new No[capacidade];
    }
}