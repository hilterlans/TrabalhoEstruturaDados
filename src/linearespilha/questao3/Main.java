package linearespilha.questao3;

public class Main {
    public static void main(String[] args) {
        EditorTexto editor = new EditorTexto();

        System.out.println("===== EXECUTANDO 12 AÇÕES =====");
        editor.executarAcao("DIGITACAO", "Estruturas de dados ");
        editor.executarAcao("DIGITACAO", "são fundamentais");
        editor.executarAcao("DIGITACAO", " para programar.");
        editor.executarAcao("FORMATACAO", "fundamentais");
        editor.executarAcao("DIGITACAO", " Pilhas seguem LIFO.");
        editor.executarAcao("EXCLUSAO", " para programar");
        editor.executarAcao("DIGITACAO", " Filas seguem FIFO.");
        editor.executarAcao("FORMATACAO", "LIFO");
        editor.executarAcao("FORMATACAO", "FIFO");
        editor.executarAcao("DIGITACAO", " Fim.");
        editor.executarAcao("EXCLUSAO", " Fim.");
        editor.executarAcao("DIGITACAO", " Revisado.");

        System.out.println("\n===== TESTE DE AÇÕES INVÁLIDAS =====");
        editor.executarAcao("EXCLUSAO", "inexistente");
        editor.executarAcao("DESENHO", "algo");
        editor.executarAcao("DIGITACAO", "");

        System.out.println("\n===== ESTADO ATUAL =====");
        editor.mostrarTexto();
        System.out.println("Ações no histórico: " + editor.tamanhoHistorico());
        editor.mostrarHistorico();

        System.out.println("\n===== DESFAZENDO 5 AÇÕES, PASSO A PASSO =====");
        for (int i = 0; i < 5; i++) {
            editor.desfazer();
        }

        System.out.println("\n===== REFAZENDO 2 AÇÕES =====");
        editor.refazer();
        editor.refazer();

        System.out.println("\n===== AÇÃO NOVA (LIMPA O REFAZER) =====");
        editor.executarAcao("DIGITACAO", " Texto final.");
        editor.refazer(); // não há nada para refazer

        System.out.println("\n===== DESFAZENDO TUDO =====");
        while (editor.tamanhoHistorico() > 0) {
            editor.desfazer();
        }

        System.out.println("\n===== TESTE DE PILHA VAZIA =====");
        editor.desfazer();

        System.out.println("\n===== RESULTADO FINAL =====");
        editor.mostrarTexto();
        System.out.println("Ações no histórico: " + editor.tamanhoHistorico());
    }
}
