package ordenacao.Questao24;


public class Main {
    static class Atleta implements Comparable<Atleta> {
        String nome; double tempo;
        Atleta(String nome, double tempo) { this.nome = nome; this.tempo = tempo; }
        public int compareTo(Atleta outro) { return Double.compare(tempo, outro.tempo); }
        public String toString() { return String.format("%-15s %.2f segundos", nome, tempo); }
    }
    public static void main(String[] args) {
        Atleta[] atletas = new Atleta[30];
        for (int i = 0; i < atletas.length; i++) {
            double tempo = 900 + ((i * 197 + 61) % 50000) / 100.0;
            atletas[i] = new Atleta("Atleta " + (i + 1), tempo);
        }
        quickSort(atletas, 0, atletas.length - 1);
        System.out.println("=== CLASSIFICACAO FINAL DA CORRIDA ===");
        for (int i = 0; i < atletas.length; i++)
            System.out.println((i + 1) + "º lugar: " + atletas[i]);
        System.out.println("\n=== PODIO ===");
        for (int i = 0; i < 3; i++) System.out.println((i + 1) + "º lugar: " + atletas[i]);
    }
    private static void quickSort(Atleta[] a, int inicio, int fim) {
        if (inicio >= fim) return;
        int p = particionar(a, inicio, fim);
        quickSort(a, inicio, p - 1); quickSort(a, p + 1, fim);
    }
    private static int particionar(Atleta[] a, int inicio, int fim) {
        Atleta pivo = a[fim]; int i = inicio - 1;
        for (int j = inicio; j < fim; j++)
            if (a[j].compareTo(pivo) <= 0) { i++; trocar(a, i, j); }
        trocar(a, i + 1, fim); return i + 1;
    }
    private static void trocar(Atleta[] a, int i, int j) {
        Atleta aux = a[i]; a[i] = a[j]; a[j] = aux;
    }
}
