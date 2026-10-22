package naoLinearesArvore.Questao10;

public class Jogador implements Comparable<Jogador> {
    private String nickname;
    private int pontuacao;

    public Jogador(String nickname, int pontuacao) {
        this.nickname = nickname;
        this.pontuacao = pontuacao;
    }

    @Override
    public int compareTo(Jogador outro) {
        // Ordenação crescente pela pontuação (a AVL trata o balanceamento por isso)
        return Integer.compare(this.pontuacao, outro.pontuacao);
    }

    @Override
    public String toString() {
        return nickname + " - " + pontuacao + " pts";
    }
}
