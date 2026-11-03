package ordenacao.Questao16;

import ordenacao.EstruturaBubbleSort;

public class Main {
    static class Produto implements Comparable<Produto> {
        String nome; double preco;
        Produto(String nome, double preco) { this.nome = nome; this.preco = preco; }
        public int compareTo(Produto outro) { return Double.compare(preco, outro.preco); }
        public String toString() { return String.format("%-22s R$ %.2f", nome, preco); }
    }
    public static void main(String[] args) {
        Produto[] produtos = {
            new Produto("Caderno", 18.90), new Produto("Caneta", 2.50),
            new Produto("Mochila", 129.90), new Produto("Estojo", 15.00),
            new Produto("Fone", 49.90), new Produto("Garrafa", 24.90),
            new Produto("Mouse", 59.90), new Produto("Teclado", 89.90),
            new Produto("Suporte", 35.00), new Produto("Carregador", 39.90),
            new Produto("Agenda", 22.00), new Produto("Luminária", 74.90)
        };
        System.out.println("=== VITRINE ORIGINAL ===");
        imprimir(produtos);
        EstruturaBubbleSort.ordenarOtimizado(produtos, true);
        System.out.println("\n=== PREÇO CRESCENTE ===");
        imprimir(produtos);
        EstruturaBubbleSort.ordenarOtimizado(produtos, false);
        System.out.println("\n=== PREÇO DECRESCENTE ===");
        imprimir(produtos);
    }
    private static void imprimir(Produto[] produtos) {
        for (Produto p : produtos) System.out.println(p);
    }
}
