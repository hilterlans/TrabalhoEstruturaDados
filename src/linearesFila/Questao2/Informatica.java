package linearesFila.Questao2;


import linearesFila.EstruturaFila;

public class Informatica {
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
        int n = fila.size();
        for (int i = 0; i < n; i++) {
            Alunos t = fila.dequeue();
            System.out.println((i + 1) + ". " + t);
            fila.enqueue(t); // volta para o final, mantendo a ordem
        }
    }

    // Cancelar o último trabalho enviado (usa fila auxiliar)
    public void cancelarUltimo() {
        if (fila.isEmpty()) {
            System.out.println("Nenhum trabalho para cancelar.");
            return;
        }
        EstruturaFila.Fila<Alunos> aux = estrutura.new Fila<>();
        int n = fila.size();
        for (int i = 0; i < n - 1; i++) {
            aux.enqueue(fila.dequeue());
        }
        Alunos cancelado = fila.dequeue(); // o último da fila
        fila = aux;
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

