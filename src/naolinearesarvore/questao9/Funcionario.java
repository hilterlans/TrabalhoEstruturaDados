package naolinearesarvore.questao9;

public class Funcionario implements Comparable<Funcionario> {
    private final String cpf;
    private final String nome;
    private final String cargo;

    public Funcionario(String cpf, String nome, String cargo) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("O CPF é obrigatório.");
        }
        this.cpf = cpf;
        this.nome = nome;
        this.cargo = cargo;
    }

    /** Construtor usado só para buscar/remover por CPF. */
    public Funcionario(String cpf) {
        this(cpf, "", "");
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getCargo() { return cargo; }

    /** A árvore ordena (e compara) os funcionários somente pelo CPF. */
    @Override
    public int compareTo(Funcionario outro) {
        return this.cpf.compareTo(outro.cpf);
    }

    @Override
    public String toString() {
        return "CPF: " + cpf + " | Nome: " + nome + " | Cargo: " + cargo;
    }
}
