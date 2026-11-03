package ordenacao.Questao19;

import ordenacao.EstruturaInsertionSort;

public class Main {
    public static void main(String[] args) {
        int[] cartasRecebidas = {8, 3, 12, 1, 10, 5, 13, 2, 7, 4, 11, 6};
        Integer[] mao = new Integer[cartasRecebidas.length];
        int quantidade = 0;
        System.out.println("=== RECEBIMENTO DE CARTAS ===");
        for (int carta : cartasRecebidas) {
            mao[quantidade++] = carta;
            Integer[] atual = new Integer[quantidade];
            for (int i = 0; i < quantidade; i++) {
                atual[i] = mao[i];
            }
            EstruturaInsertionSort.ordenar(atual, true);
            for (int i = 0; i < quantidade; i++) {
                mao[i] = atual[i];
            }
            System.out.print("Carta recebida: " + carta + " | Mao ordenada: ");
            imprimir(mao, quantidade);
        }
    }
    private static void imprimir(Integer[] cartas, int quantidade) {
        for (int i = 0; i < quantidade; i++) System.out.print(cartas[i] + " ");
        System.out.println();
    }
}
