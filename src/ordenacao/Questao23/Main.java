package ordenacao.Questao23;


public class Main {
    static class Produto {
        String nome; double preco; int popularidade;
        Produto(String nome, double preco, int popularidade) {
            this.nome = nome; this.preco = preco; this.popularidade = popularidade;
        }
        public String toString() {
            return String.format("%-15s | R$ %7.2f | popularidade %d", nome, preco, popularidade);
        }
    }
    public static void main(String[] args) {
        Produto[] produtos = new Produto[100];
        for (int i = 0; i < produtos.length; i++) {
            double preco = 10 + ((i * 7919 + 137) % 99000) / 100.0;
            int popularidade = (i * 379 + 83) % 1001;
            produtos[i] = new Produto("Produto" + (i + 1), preco, popularidade);
        }
        ordenar(produtos, "preco");
        System.out.println("=== CATALOGO ORDENADO POR PRECO (100 PRODUTOS) ===");
        imprimir(produtos);
        ordenar(produtos, "nome");
        System.out.println("\n=== PRIMEIROS 10 POR NOME ===");
        for (int i = 0; i < 10; i++) System.out.println(produtos[i]);
        ordenar(produtos, "popularidade");
        System.out.println("\n=== TOP 10 POR POPULARIDADE ===");
        for (int i = produtos.length - 1; i >= produtos.length - 10; i--) System.out.println(produtos[i]);
    }
    private static int comparar(Produto a, Produto b, String criterio) {
        if ("nome".equalsIgnoreCase(criterio)) return a.nome.compareToIgnoreCase(b.nome);
        if ("popularidade".equalsIgnoreCase(criterio)) return Integer.compare(a.popularidade, b.popularidade);
        return Double.compare(a.preco, b.preco);
    }
    private static void ordenar(Produto[] a, String criterio) { quickSort(a, 0, a.length - 1, criterio); }
    private static void quickSort(Produto[] a, int inicio, int fim, String criterio) {
        if (inicio >= fim) return;
        int p = particionar(a, inicio, fim, criterio);
        quickSort(a, inicio, p - 1, criterio); quickSort(a, p + 1, fim, criterio);
    }
    private static int particionar(Produto[] a, int inicio, int fim, String criterio) {
        int meio = (inicio + fim) / 2;
        Produto piv = mediana(a[inicio], a[meio], a[fim], criterio);
        int indicePivo = piv == a[inicio] ? inicio : (piv == a[meio] ? meio : fim);
        trocar(a, indicePivo, fim);
        Produto pivo = a[fim]; int i = inicio - 1;
        for (int j = inicio; j < fim; j++)
            if (comparar(a[j], pivo, criterio) <= 0) trocar(a, ++i, j);
        trocar(a, i + 1, fim); return i + 1;
    }
    private static Produto mediana(Produto a, Produto b, Produto c, String criterio) {
        if (comparar(a,b,criterio) > 0) { Produto t=a; a=b; b=t; }
        if (comparar(b,c,criterio) > 0) { Produto t=b; b=c; c=t; }
        if (comparar(a,b,criterio) > 0) { Produto t=a; a=b; b=t; }
        return b;
    }
    private static void trocar(Produto[] a, int i, int j) { Produto t=a[i]; a[i]=a[j]; a[j]=t; }
    private static void imprimir(Produto[] a) { for (Produto p : a) System.out.println(p); }
}
