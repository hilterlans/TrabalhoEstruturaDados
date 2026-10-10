package linearesFila.Questao1;

public class Main {

    public static void main(String[] args) {
        Clinica clinica = new Clinica(8 * 60); // clínica abre às 08:00

        // 1. Chegada dos pacientes (mínimo de 15)
        Pacientes[] pacientes = {
                new Pacientes("Ana",      "08:05", "Consulta"),
                new Pacientes("Bruno",    "08:12", "Exame"),
                new Pacientes("Carla",    "08:15", "Cirurgia"),
                new Pacientes("Diego",    "08:20", "Consulta"),
                new Pacientes("Elisa",    "08:25", "Consulta"),
                new Pacientes("Felipe",   "08:30", "Exame"),
                new Pacientes("Gabriela", "08:38", "Consulta"),
                new Pacientes("Henrique", "08:45", "Exame"),
                new Pacientes("Isabela",  "08:50", "Consulta"),
                new Pacientes("João",     "09:00", "Cirurgia"),
                new Pacientes("Karen",    "09:10", "Consulta"),
                new Pacientes("Lucas",    "09:15", "Exame"),
                new Pacientes("Marina",   "09:25", "Consulta"),
                new Pacientes("Nathan",   "09:30", "Consulta"),
                new Pacientes("Olívia",   "09:40", "Exame"),
                new Pacientes("Pedro",    "09:50", "Consulta")
        };

        System.out.println("=== CHEGADA DOS PACIENTES ===");
        for (Pacientes p : pacientes) {
            clinica.chegada(p);
            System.out.println(p.getNome() + " chegou às " + p.getHorarioChegada()
                    + " (" + p.getTipoAtendimento() + ")");
        }

        System.out.println("\nTamanho atual da fila: " + clinica.tamanhoFila());
        System.out.println("Próximo da fila: " + clinica.proximoDaFila().getNome());

        // 2. Chamada dos pacientes, um por um
        System.out.println("\n=== CHAMADAS ===");
        while (clinica.tamanhoFila() > 0) {
            Pacientes chamado = clinica.chamarProximo();
            System.out.println("Chamado: " + chamado.getNome()
                    + " | Restam na fila: " + clinica.tamanhoFila());
        }

        // 3. Teste de fila vazia (tratamento de erro)
        System.out.println("\n=== TESTE FILA VAZIA ===");
        clinica.chamarProximo();
        clinica.proximoDaFila();

        // 4. Relatório final
        System.out.println();
        clinica.relatorio();
    }
}