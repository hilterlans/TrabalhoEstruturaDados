package naoLinearesArvore.Questao8;

public class ProdutoTrie implements Comparable<ProdutoTrie> {
    private String nome;

    public ProdutoTrie(String nome) {
        this.nome = nome;
    }
    
    public String getNome() { return nome; }

    @Override
    public int compareTo(ProdutoTrie outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public String toString() {
        return nome;
    }
}
