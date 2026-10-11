package linearesFila.Questao2;

import linearesFila.EstruturaFila;

/**
 * Laboratório de informática: gerencia a fila de impressão (FIFO).
 * Complexidade: enviar e imprimir são O(1); listar e cancelar último são O(n).
 */
public class Informatica {
    // Fila não é static, então precisa de uma instância de EstruturaFila
    private EstruturaFila estrutura = new EstruturaFila();
    private EstruturaFila.Fila<Alunos> fila;
    private int totalImpresso = 0;       // total de páginas impressas
    private int trabalhosImpressos = 0;  // quantidade de trabalhos impressos

    public Informatica() {
        this.fila = estrutura.new Fila<>();
    }

    // Enviar trabalho (entra no final da fila)
    public void enviarTrabalho(Alunos trabalho) {
        if (trabalho == null) {
            System.out.println("Trabalho inválido, não foi enviado.");
            return;
        }
        fila.enqueue(trabalho);
        System.out.println("Enviado: " + trabalho);
    }

    // Imprimir próximo (sai da frente da fila)
    public Alunos imprimirProximo() {
        if (fila.isEmpty()) {
            System.out.println("Fila vazia, nada para imprimir.");
            return null;
        }
        Alunos t = fila.dequeue();
        totalImpresso += t.getNumeroPag();
        trabalhosImpressos++;
        System.out.println("Imprimindo: " + t);
        return t;
    }

    // Listar a fila sem perder a ordem original
    public void listarFila() {
        if (fila.isEmpty()) {
            System.out.println("Fila vazia.");
            return;
        }
        // toString da Fila percorre os nós sem remover ninguém
        System.out.println(fila);
    }

    // Cancelar o último trabalho enviado (usa removerUltimo da Fila, O(n))
    public void cancelarUltimo() {
        if (fila.isEmpty()) {
            System.out.println("Nenhum trabalho para cancelar.");
            return;
        }
        Alunos cancelado = fila.removerUltimo();
        System.out.println("Cancelado: " + cancelado);
    }

    public boolean isEmpty() {
        return fila.isEmpty();
    }

    public int tamanhoFila() {
        return fila.size();
    }

    public int getTotalImpresso() {
        return totalImpresso;
    }

    public int getTrabalhosImpressos() {
        return trabalhosImpressos;
    }
}