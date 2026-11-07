package ordenacao.Questao21;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    static class Transacao {
        LocalDateTime data; double valor; String descricao; int registro;
        Transacao(LocalDateTime data, double valor, String descricao, int registro) {
            this.data = data; this.valor = valor; this.descricao = descricao; this.registro = registro;
        }
        public String toString() {
            return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                    " | " + String.format("R$ %.2f", valor) + " | " + descricao + " | registro " + registro;
        }
    }
    public static void main(String[] args) {
        Transacao[] transacoes = new Transacao[50];
        LocalDateTime base = LocalDateTime.of(2026, 10, 1, 8, 0);
        for (int i = 0; i < transacoes.length; i++) {
            // Alguns registros compartilham o mesmo segundo para demonstrar estabilidade.
            int minutos = (i * 137 + 53) % 600;
            int segundo = ((i * 7) % 4) * 10;
            double valor = 20 + ((i * 173) % 98000) / 100.0;
            LocalDateTime data = base.plusMinutes(minutos).withSecond(segundo);
            transacoes[i] = new Transacao(data, valor, "Transacao " + (i + 1), i + 1);
        }
        System.out.println("=== HISTORICO ORIGINAL (50 TRANSACOES) ===");
        imprimir(transacoes);
        mergeSort(transacoes, 0, transacoes.length - 1);
        System.out.println("\n=== EXTRATO ORDENADO POR DATA/HORA ===");
        imprimir(transacoes);
    }
    private static void mergeSort(Transacao[] a, int inicio, int fim) {
        if (inicio >= fim) return;
        int meio = (inicio + fim) / 2;
        mergeSort(a, inicio, meio); mergeSort(a, meio + 1, fim);
        merge(a, inicio, meio, fim);
    }
    private static void merge(Transacao[] a, int inicio, int meio, int fim) {
        Transacao[] aux = new Transacao[fim - inicio + 1];
        int i = inicio, j = meio + 1, k = 0;
        while (i <= meio && j <= fim) {
            if (!a[i].data.isAfter(a[j].data)) aux[k++] = a[i++];
            else aux[k++] = a[j++];
        }
        while (i <= meio) aux[k++] = a[i++];
        while (j <= fim) aux[k++] = a[j++];
        for (int x = 0; x < aux.length; x++) a[inicio + x] = aux[x];
    }
    private static void imprimir(Transacao[] a) { for (Transacao t : a) System.out.println(t); }
}
