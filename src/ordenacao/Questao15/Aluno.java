/**
 * Registro de um aluno (nome + nota).
 *
 * compareTo usa SOMENTE a nota. Assim, alunos com a mesma nota são "iguais"
 * para a ordenação, e dá para observar quais algoritmos são estáveis (mantêm
 * a ordem original entre notas iguais).
 */
package ordenacao.Questao15;

public class Aluno implements Comparable<Aluno> {

    private final String nome;
    private final double nota;

    public Aluno(String nome, double nota) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do aluno não pode ser vazio.");
        }
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Nota deve estar entre 0 e 10: " + nota);
        }
        this.nome = nome.trim();
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public int compareTo(Aluno outro) {
        return Double.compare(this.nota, outro.nota);
    }

    @Override
    public String toString() {
        return String.format("%-10s %4.1f", nome, nota);
    }
}