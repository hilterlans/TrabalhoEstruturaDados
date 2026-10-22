package naolinearesarvore.questao10;

public class Jogador implements Comparable<Jogador> {
    private final String nickname;
    private final int pontuacao;

    public Jogador(String nickname, int pontuacao) {
        if (nickname == null || nickname.isBlank()) {
            throw new IllegalArgumentException("O nickname é obrigatório.");
        }
        if (pontuacao < 0) {
            throw new IllegalArgumentException("A pontuação não pode ser negativa.");
        }
        this.nickname = nickname;
        this.pontuacao = pontuacao;
    }

    public String getNickname() { return nickname; }
    public int getPontuacao() { return pontuacao; }

    /**
     * Ordem crescente por pontuação. No empate, o nickname é o desempate, em
     * ordem INVERSA: assim, ao ler a árvore do maior para o menor (ranking),
     * jogadores empatados saem em ordem alfabética. Sem desempate, dois
     * jogadores com a mesma pontuação seriam tratados como "iguais" e o
     * segundo seria descartado pela árvore.
     */
    @Override
    public int compareTo(Jogador outro) {
        int cmp = Integer.compare(this.pontuacao, outro.pontuacao);
        if (cmp != 0) return cmp;
        return outro.nickname.compareTo(this.nickname);
    }

    @Override
    public String toString() {
        return nickname + " - " + pontuacao + " pts";
    }
}
