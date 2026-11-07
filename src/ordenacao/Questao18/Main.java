package ordenacao.Questao18;

import ordenacao.EstruturaSelectionSort;

public class Main {
    static class Tarefa implements Comparable<Tarefa> {
        String descricao; int prioridade;
        Tarefa(String descricao, int prioridade) {
            if (prioridade < 1 || prioridade > 5) throw new IllegalArgumentException("Prioridade deve ser de 1 a 5.");
            this.descricao = descricao; this.prioridade = prioridade;
        }
        public int compareTo(Tarefa outra) { return Integer.compare(prioridade, outra.prioridade); }
        public String toString() { return "Prioridade " + prioridade + " - " + descricao; }
    }
    public static void main(String[] args) {
        Tarefa[] tarefas = {
            new Tarefa("Responder e-mails", 3), new Tarefa("Entregar trabalho", 5),
            new Tarefa("Estudar Java", 5), new Tarefa("Organizar mesa", 1),
            new Tarefa("Ir ao mercado", 2), new Tarefa("Revisar conteúdo", 4),
            new Tarefa("Pagar conta", 4), new Tarefa("Lavar roupas", 2),
            new Tarefa("Fazer exercícios", 3), new Tarefa("Atualizar currículo", 4),
            new Tarefa("Preparar almoço", 3), new Tarefa("Arrumar quarto", 1),
            new Tarefa("Ler documentação", 5), new Tarefa("Separar materiais", 2)
        };
        EstruturaSelectionSort.ordenar(tarefas, false);
        System.out.println("=== TAREFAS POR PRIORIDADE (MAIOR PRIMEIRO) ===");
        for (Tarefa t : tarefas) System.out.println(t);
    }
}
