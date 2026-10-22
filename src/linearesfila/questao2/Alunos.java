package linearesfila.questao2;

public class Alunos {
    private String nome;
    private String nomeArquivo;
    private int numeroPag;

    public Alunos(String nome,String nomeArquivo,int numeroPag){
        this.nome = nome;
        this.nomeArquivo = nomeArquivo;
        this.numeroPag = numeroPag;

    }

    public String getNome(){
        return nome;
    }
    public String getNomeArquivo(){
        return nomeArquivo;
    }
    public int getNumeroPag(){
        return numeroPag;
    }
    @Override
    public String toString() {
        return nome + " | " + nomeArquivo + " | " + numeroPag + " pág.";
    }
}
