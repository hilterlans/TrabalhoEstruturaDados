/**
 * QUESTÃO 10 – Ranking de jogadores de um jogo online (AVL)
 *
 * Justificativa de escolha:
 *   O ranking muda o tempo todo (cada partida atualiza uma pontuação) e a
 *   consulta mais comum é "quem são os K melhores". A AVL mantém os jogadores
 *   ordenados por pontuação e continua balanceada após muitas atualizações,
 *   garantindo O(log n) para inserir/remover. O Top-K lê só os k maiores,
 *   sem ordenar tudo de novo.
 *   Como a árvore é ordenada por (pontuação, nickname), localizar um jogador
 *   pelo nickname exigiria varrer tudo; por isso um índice em Tabela Hash
 *   (nickname -> jogador atual) permite achar a pontuação antiga em O(1)
 *   médio e removê-la da AVL antes de inserir a nova.
 *
 * Complexidade (n = jogadores): cadastrar e atualizar O(log n); topK(k)
 * O(log n + k); espaço O(n).
 */
package naolinearesarvore.questao10;

import naolinearesarvore.EstruturaAVL;
import naolineareshash.EstruturaTabelaHash;

public class RankingJogos {
    private final EstruturaAVL<Jogador> arvore = new EstruturaAVL<>();
    private final EstruturaTabelaHash<Jogador> indice = new EstruturaTabelaHash<>();

    /** Cadastra um jogador novo. Devolve false se o nickname já existe. */
    public boolean cadastrar(String nickname, int pontuacao) {
        Jogador novo = new Jogador(nickname, pontuacao);
        if (indice.contem(nickname)) return false;
        arvore.inserir(novo);
        indice.inserir(nickname, novo);
        return true;
    }

    /** Define a nova pontuação do jogador. Devolve false se ele não existe. */
    public boolean atualizarPontuacao(String nickname, int novaPontuacao) {
        Jogador antigo = indice.buscar(nickname);
        if (antigo == null) return false;
        Jogador novo = new Jogador(nickname, novaPontuacao);
        arvore.remover(antigo);
        arvore.inserir(novo);
        indice.inserir(nickname, novo);
        return true;
    }

    /** Soma pontos à pontuação atual. Devolve false se o jogador não existe. */
    public boolean adicionarPontos(String nickname, int pontos) {
        Jogador atual = indice.buscar(nickname);
        if (atual == null) return false;
        return atualizarPontuacao(nickname, atual.getPontuacao() + pontos);
    }

    public Jogador buscar(String nickname) {
        return indice.buscar(nickname);
    }

    /** Mostra os k melhores jogadores, do primeiro colocado para baixo. */
    public void mostrarTopK(int k) {
        System.out.println("   Top " + k + ":");
        int[] posicao = {1};
        arvore.topK(k, j -> System.out.println("   " + posicao[0]++ + "º " + j));
    }

    public int getTotalJogadores() { return arvore.getTamanho(); }
    public int getAltura() { return arvore.altura(); }
    public boolean estaBalanceada() { return arvore.estaBalanceada(); }
}
