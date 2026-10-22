package naolinearesarvore.questao7;

public class Aluno implements Comparable<Aluno> {
    private final String matricula;
    private final String nome;
    private final String curso;

    public Aluno(String matricula, String nome, String curso) {
        this.matricula = matricula;
        this.nome = nome;
        this.curso = curso;
    }

    public String getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public String getCurso() { return curso; }

    /** A BST ordena (e compara) os alunos somente pela matrícula. */
    @Override
    public int compareTo(Aluno outro) {
        return this.matricula.compareTo(outro.matricula);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return matricula.equals(((Aluno) obj).matricula);
    }

    @Override
    public int hashCode() { return matricula.hashCode(); }

    @Override
    public String toString() {
        return "Matrícula: " + matricula + " | Nome: " + nome + " | Curso: " + curso;
    }
}
