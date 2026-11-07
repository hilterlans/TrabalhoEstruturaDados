package ordenacao.Questao20;

import ordenacao.EstruturaInsertionSort;

public class Main {
    static class Vendedor implements Comparable<Vendedor> {
        String nome; double vendas;
        Vendedor(String nome, double vendas) { this.nome = nome; this.vendas = vendas; }
        public int compareTo(Vendedor outro) { return Double.compare(vendas, outro.vendas); }
        public String toString() { return String.format("%-12s R$ %.2f", nome, vendas); }
    }
    public static void main(String[] args) {
        Vendedor[] vendedores = {
            new Vendedor("Ana", 1200), new Vendedor("Bruno", 950),
            new Vendedor("Carla", 1800), new Vendedor("Diego", 1400),
            new Vendedor("Elisa", 1100), new Vendedor("Felipe", 1600),
            new Vendedor("Gabriela", 1300), new Vendedor("Hugo", 1000)
        };
        System.out.println("=== RANKING INICIAL ===");
        EstruturaInsertionSort.ordenar(vendedores, false);
        imprimir(vendedores);
        double[][] atualizacoes = {
            {150, 200, 100, 250, 180, 120, 300, 90},
            {100, 150, 200, 100, 120, 180, 100, 160},
            {200, 100, 150, 180, 100, 220, 120, 140},
            {120, 180, 100, 200, 160, 100, 180, 110},
            {180, 120, 220, 150, 200, 140, 100, 190},
            {100, 200, 120, 100, 180, 160, 200, 130},
            {150, 100, 180, 220, 120, 200, 150, 170}
        };
        for (int dia = 0; dia < atualizacoes.length; dia++) {
            for (int i = 0; i < vendedores.length; i++) vendedores[i].vendas += atualizacoes[dia][i];
            EstruturaInsertionSort.ordenar(vendedores, false);
            System.out.println("\n--- Ranking apos o dia " + (dia + 1) + " ---");
            imprimir(vendedores);
        }
        // Comparação simples: lista quase ordenada versus lista embaralhada.
        Vendedor[] quase = vendedores.clone();
        Vendedor[] aleatoria = vendedores.clone();
        // Embaralhamento feito manualmente, sem biblioteca de numeros aleatorios.
        for (int i = aleatoria.length - 1; i > 0; i--) {
            int j = (i * 3 + 2) % (i + 1);
            Vendedor aux = aleatoria[i];
            aleatoria[i] = aleatoria[j];
            aleatoria[j] = aux;
        }
        long inicio = System.nanoTime(); EstruturaInsertionSort.ordenar(quase, false);
        long tempoQuase = System.nanoTime() - inicio;
        inicio = System.nanoTime(); EstruturaInsertionSort.ordenar(aleatoria, false);
        long tempoAleatorio = System.nanoTime() - inicio;
        System.out.println("\nTempo lista ja ordenada: " + tempoQuase + " ns");
        System.out.println("Tempo lista embaralhada: " + tempoAleatorio + " ns");
    }
    private static void imprimir(Vendedor[] vendedores) {
        for (int i = 0; i < vendedores.length; i++) System.out.println((i + 1) + "º " + vendedores[i]);
    }
}
