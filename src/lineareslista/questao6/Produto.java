package lineareslista.questao6;

import java.util.Objects;

/** Produto do estoque de uma loja de materiais de construção. */
public class Produto {
    private static final int ESTOQUE_MINIMO_PADRAO = 10;

    private final String codigo;
    private final String nome;
    private int quantidade;
    private double preco;
    private final int estoqueMinimo;

    public Produto(String codigo, String nome, int quantidade, double preco) {
        this(codigo, nome, quantidade, preco, ESTOQUE_MINIMO_PADRAO);
    }

    public Produto(String codigo, String nome, int quantidade, double preco, int estoqueMinimo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("O código do produto é obrigatório.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        if (quantidade < 0 || preco < 0 || estoqueMinimo < 0) {
            throw new IllegalArgumentException("Quantidade, preço e estoque mínimo não podem ser negativos.");
        }
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.estoqueMinimo = estoqueMinimo;
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }
    public double getPreco() { return preco; }
    public int getEstoqueMinimo() { return estoqueMinimo; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setPreco(double preco) { this.preco = preco; }

    public boolean estaEsgotado() { return quantidade == 0; }

    public boolean abaixoDoMinimo() { return quantidade < estoqueMinimo; }

    /** Dois produtos são o mesmo produto se têm o mesmo código. */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produto produto = (Produto) obj;
        return codigo.equalsIgnoreCase(produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("[%s] %-22s Qtd: %5d (mín. %d) | R$ %.2f", codigo, nome, quantidade, estoqueMinimo, preco);
    }
}
