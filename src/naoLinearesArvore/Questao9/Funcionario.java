package naoLinearesArvore.Questao9;

public class Funcionario implements Comparable<Funcionario> {
    private String cpf;
    private String nome;

    public Funcionario(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    @Override
    public int compareTo(Funcionario outro) {
        return this.cpf.compareTo(outro.cpf);
    }

    @Override
    public String toString() {
        return "CPF: " + cpf + " | Nome: " + nome;
    }
}
