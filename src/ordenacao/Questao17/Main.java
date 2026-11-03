package ordenacao.Questao17;

import ordenacao.EstruturaSelectionSort;

public class Main {
    static class Candidato implements Comparable<Candidato> {
        String nome; double pontuacao;
        Candidato(String nome, double pontuacao) { this.nome = nome; this.pontuacao = pontuacao; }
        public int compareTo(Candidato outro) { return Double.compare(pontuacao, outro.pontuacao); }
        public String toString() { return String.format("%-18s %.1f pontos", nome, pontuacao); }
    }
    public static void main(String[] args) {
        Candidato[] candidatos = {
            new Candidato("Ana", 91), new Candidato("Bruno", 78), new Candidato("Carla", 96),
            new Candidato("Diego", 84), new Candidato("Elisa", 88), new Candidato("Felipe", 73),
            new Candidato("Gabriela", 95), new Candidato("Hugo", 81), new Candidato("Isabela", 90),
            new Candidato("Joao", 67), new Candidato("Karina", 93), new Candidato("Lucas", 86),
            new Candidato("Marina", 99), new Candidato("Nicolas", 76), new Candidato("Olivia", 89)
        };
        EstruturaSelectionSort.ordenar(candidatos, false);
        System.out.println("=== CLASSIFICACAO DO PROCESSO SELETIVO ===");
        for (int i = 0; i < candidatos.length; i++)
            System.out.println((i + 1) + "º " + candidatos[i]);
        System.out.println("\n=== TOP 5 APROVADOS ===");
        for (int i = 0; i < 5; i++) System.out.println(candidatos[i]);
    }
}
