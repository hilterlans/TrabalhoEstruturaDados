package linearesfila.questao1;

import linearesfila.EstruturaFila;
import java.util.ArrayList;
import java.util.List;

public class Clinica {

        private EstruturaFila.Fila<Pacientes> fila;
        private List<Pacientes> atendidos;
        private List<Integer> temposEspera;
        private int relogioAtual;

        public Clinica(int horarioAbertura) {
            // Fila é classe interna (não estática) de EstruturaFila
            this.fila = new EstruturaFila().new Fila<Pacientes>();
            this.atendidos = new ArrayList<>();
            this.temposEspera = new ArrayList<>();
            this.relogioAtual = horarioAbertura; // ex: 8h00 = 480
        }

        public void chegada(Pacientes paciente) {
            fila.enqueue(paciente);
        }

        public Pacientes chamarProximo() {
            if (fila.isEmpty()) {
                System.out.println("Nenhum paciente na fila.");
                return null;
            }

            Pacientes p = fila.dequeue();

            if (relogioAtual < p.getChegadaEmMinutos()) {
                relogioAtual = p.getChegadaEmMinutos();
            }

            temposEspera.add(relogioAtual - p.getChegadaEmMinutos());
            atendidos.add(p);

            relogioAtual += duracaoAtendimento(p.getTipoAtendimento());
            return p;
        }

        private int duracaoAtendimento(String tipo) {
            switch (tipo) {
                case "Consulta": return 20;
                case "Exame":    return 15;
                case "Cirurgia": return 60;
                default:         return 20;
            }
        }

        public int tamanhoFila() {
            return fila.size();
        }

        public Pacientes proximoDaFila() {
            if (fila.isEmpty()) {
                System.out.println("Nenhum paciente na fila.");
                return null;
            }
            return fila.front();
        }

        public void relatorio() {
            System.out.println("=== RELATÓRIO FINAL ===");
            for (int i = 0; i < atendidos.size(); i++) {
                Pacientes p = atendidos.get(i);
                System.out.println((i + 1) + ". " + p.getNome()
                        + " | chegada: " + p.getHorarioChegada()
                        + " | tipo: " + p.getTipoAtendimento()
                        + " | espera: " + temposEspera.get(i) + " min");
            }

            int soma = 0;
            for (int t : temposEspera) {
                soma += t;
            }
            double media = temposEspera.isEmpty() ? 0 : (double) soma / temposEspera.size();
            System.out.printf("Tempo médio de espera: %.1f min%n", media);
        }
    }

