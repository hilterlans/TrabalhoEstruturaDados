package linearesLista.Questao6;

public class Produto {
    private final String codigo;
    private String nome;
    private int quantidade;
    private double preco;

    public Produto(String codigo, String nome, int quantidade, double preco) {
        if (codigo == null || codigo.isBlank() || nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Codigo e nome sao obrigatorios.");
        }
        if (quantidade < 0 || preco < 0) {
            throw new IllegalArgumentException("Quantidade e preco nao podem ser negativos.");
        }
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }
    public double getPreco() { return preco; }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome invalido.");
        this.nome = nome;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade nao pode ser negativa.");
        this.quantidade = quantidade;
    }

    public void setPreco(double preco) {
        if (preco < 0) throw new IllegalArgumentException("Preco nao pode ser negativo.");
        this.preco = preco;
    }

    @Override
    public String toString() {
        return String.format("Codigo: %s | Produto: %s | Quantidade: %d | Preco: R$ %.2f",
                codigo, nome, quantidade, preco);
    }
}
