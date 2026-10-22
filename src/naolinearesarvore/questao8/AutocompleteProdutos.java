/**
 * QUESTÃO 8 – Autocomplete de nomes de produtos (BST)
 *
 * Justificativa de escolha:
 *   Os nomes ficam ordenados na BST, então todos os que começam com um
 *   prefixo formam um bloco contíguo da ordem alfabética. Isso permite PODAR
 *   a busca: se o nome do nó é menor que o prefixo, só a direita interessa;
 *   se é maior (e não começa com o prefixo), só a esquerda. O percurso é
 *   em ordem (sugestões já saem alfabéticas) e para ao atingir o limite de
 *   sugestões ("percurso limitado").
 *
 * Complexidade: inserir O(h); sugerir O(h + s), com s = sugestões devolvidas
 * (e não O(n) como seria varrer a árvore inteira); espaço O(n).
 */
package naolinearesarvore.questao8;

import naolinearesarvore.EstruturaBST;

public class AutocompleteProdutos {
    private final EstruturaBST<ProdutoTrie> arvore = new EstruturaBST<>();
    private int nosVisitados;

    public boolean cadastrar(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        return arvore.inserir(new ProdutoTrie(nome.trim()));
    }

    public int getTotalProdutos() { return arvore.getTamanho(); }

    public int getAlturaArvore() { return arvore.altura(); }

    /** Quantos nós a última chamada de sugerir() precisou visitar. */
    public int getNosVisitados() { return nosVisitados; }

    /** Devolve até 'limite' produtos que começam com o prefixo, em ordem alfabética. */
    public String[] sugerir(String prefixo, int limite) {
        if (prefixo == null) {
            throw new IllegalArgumentException("O prefixo não pode ser nulo.");
        }
        if (limite <= 0) {
            throw new IllegalArgumentException("O limite deve ser positivo.");
        }
        String[] encontrados = new String[limite];
        int[] quantidade = {0};
        nosVisitados = 0;
        coletar(arvore.getRaiz(), prefixo, encontrados, quantidade, limite);

        String[] resultado = new String[quantidade[0]];
        for (int i = 0; i < quantidade[0]; i++) {
            resultado[i] = encontrados[i];
        }
        return resultado;
    }

    private void coletar(EstruturaBST<ProdutoTrie>.No no, String prefixo, String[] saida, int[] qtd, int limite) {
        if (no == null || qtd[0] >= limite) return;
        nosVisitados++;
        String nome = no.dado.getNome();
        boolean casa = nome.regionMatches(true, 0, prefixo, 0, prefixo.length());
        int cmp = casa ? 0 : nome.compareToIgnoreCase(prefixo);

        if (casa || cmp > 0) {            // nomes menores que este ainda podem casar
            coletar(no.esq, prefixo, saida, qtd, limite);
        }
        if (casa && qtd[0] < limite) {
            saida[qtd[0]++] = nome;
        }
        if (casa || cmp < 0) {            // nomes maiores que este ainda podem casar
            coletar(no.dir, prefixo, saida, qtd, limite);
        }
    }
}
