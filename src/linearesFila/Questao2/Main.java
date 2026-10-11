package linearesFila.Questao2;

public class Main {
    public static void main(String[] args) {
        Informatica lab = new Informatica();

        System.out.println("===== ENVIO DOS TRABALHOS =====");
        lab.enviarTrabalho(new Alunos("Ana Souza", "relatorio_fisica.pdf", 12));
        lab.enviarTrabalho(new Alunos("Bruno Lima", "lista_calculo.pdf", 4));
        lab.enviarTrabalho(new Alunos("Carla Mendes", "tcc_capitulo1.docx", 25));
        lab.enviarTrabalho(new Alunos("Diego Alves", "slides_algoritmos.pdf", 18));
        lab.enviarTrabalho(new Alunos("Elisa Rocha", "curriculo.pdf", 2));
        lab.enviarTrabalho(new Alunos("Felipe Costa", "resumo_banco_dados.docx", 7));
        lab.enviarTrabalho(new Alunos("Gabriela Nunes", "artigo_ia.pdf", 14));
        lab.enviarTrabalho(new Alunos("Hugo Pereira", "exercicios_java.pdf", 9));
        lab.enviarTrabalho(new Alunos("Isabela Martins", "projeto_final.pdf", 30));
        lab.enviarTrabalho(new Alunos("João Ribeiro", "prova_antiga.pdf", 6));
        lab.enviarTrabalho(new Alunos("Karina Dias", "apostila_redes.pdf", 22));
        lab.enviarTrabalho(new Alunos("Lucas Barros", "trabalho_historia.docx", 10));
        lab.enviarTrabalho(new Alunos("Marina Teixeira", "mapa_mental.pdf", 1));
        lab.enviarTrabalho(new Alunos("Nicolas Freitas", "relatorio_estagio.pdf", 16));
        lab.enviarTrabalho(new Alunos("Olívia Campos", "formulario.pdf", 3));

        System.out.println("\n===== FILA ATUAL (" + lab.tamanhoFila() + " trabalhos) =====");
        lab.listarFila();

        System.out.println("\n===== CANCELANDO O ÚLTIMO TRABALHO ENVIADO =====");
        lab.cancelarUltimo();

        System.out.println("\n===== FILA APÓS CANCELAMENTO (" + lab.tamanhoFila() + " trabalhos) =====");
        lab.listarFila();

        System.out.println("\n===== IMPRIMINDO OS 3 PRIMEIROS =====");
        for (int i = 0; i < 3; i++) {
            lab.imprimirProximo();
        }

        System.out.println("\n===== FILA RESTANTE (" + lab.tamanhoFila() + " trabalhos) =====");
        lab.listarFila();

        System.out.println("\n===== IMPRIMINDO O RESTANTE =====");
        while (!lab.isEmpty()) {
            lab.imprimirProximo();
        }

        System.out.println("\n===== TESTE DE FILA VAZIA =====");
        lab.imprimirProximo();
        lab.cancelarUltimo();

        System.out.println("\n===== RELATÓRIO FINAL =====");
        System.out.println("Trabalhos impressos: " + lab.getTrabalhosImpressos());
        System.out.println("Total de páginas impressas: " + lab.getTotalImpresso());
    }
}

