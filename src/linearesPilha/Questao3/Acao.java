package linearesPilha.Questao3;

public class Acao {
    private String tipo;           // "DIGITACAO", "EXCLUSAO" ou "FORMATACAO"
    private String conteudo;       // trecho digitado, apagado ou formatado
    private String textoAnterior;  // estado do texto antes da ação

    public Acao(String tipo, String conteudo, String textoAnterior) {
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo da ação não pode ser vazio.");
        }
        if (conteudo == null) {
            throw new IllegalArgumentException("Conteúdo da ação não pode ser nulo.");
        }
        if (textoAnterior == null) {
            throw new IllegalArgumentException("Texto anterior não pode ser nulo.");
        }
        this.tipo = tipo;
        this.conteudo = conteudo;
        this.textoAnterior = textoAnterior;
    }

    public String getTipo() {
        return tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getTextoAnterior() {
        return textoAnterior;
    }

    @Override
    public String toString() {
        return tipo + " (\"" + conteudo + "\")";
    }
}