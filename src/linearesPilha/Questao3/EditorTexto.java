package linearesPilha.Questao3;

import linearesPilha.EstruturaPilha;

public class EditorTexto {
   private String texto;
   private EstruturaPilha.Pilha<Acao> pilhaDesfazer;
   private EstruturaPilha.Pilha<Acao> pilhaRefazer;

   public EditorTexto(){
           this.texto = "";
           this.pilhaDesfazer = new EstruturaPilha.Pilha<>();
           this.pilhaRefazer = new EstruturaPilha.Pilha<>();
   }

    public void executarAcao(String tipo, String conteudo) {
        if (tipo == null || tipo.isBlank() || conteudo == null || conteudo.isEmpty()) {
            System.out.println("Ação inválida: tipo e conteúdo não podem ser vazios.");
            return;
        }
        String tipoNormalizado = tipo.trim().toUpperCase();
        String textoAnterior = texto;

        String novoTexto = aplicar(tipoNormalizado, conteudo);
        if (novoTexto == null) {
            return; // a mensagem de erro já foi impressa em aplicar()
        }

        texto = novoTexto;
        pilhaDesfazer.push(new Acao(tipoNormalizado, conteudo, textoAnterior));
        pilhaRefazer.clear(); // ação nova invalida o "refazer"

        System.out.println("Executado: " + tipoNormalizado + " (\"" + conteudo + "\")");
        System.out.println("   Texto: \"" + texto + "\"");
    }

    // Aplica a ação no texto e devolve o novo texto (ou null se não deu para aplicar)
    private String aplicar(String tipo, String conteudo) {
        switch (tipo) {
            case "DIGITACAO":
                return texto + conteudo;

            case "EXCLUSAO": {
                int pos = texto.lastIndexOf(conteudo);
                if (pos == -1) {
                    System.out.println("Exclusão não realizada: trecho \"" + conteudo + "\" não encontrado.");
                    return null;
                }
                return texto.substring(0, pos) + texto.substring(pos + conteudo.length());
            }

            case "FORMATACAO": {
                int pos = texto.lastIndexOf(conteudo);
                if (pos == -1) {
                    System.out.println("Formatação não realizada: trecho \"" + conteudo + "\" não encontrado.");
                    return null;
                }
                return texto.substring(0, pos) + "**" + conteudo + "**"
                        + texto.substring(pos + conteudo.length());
            }

            default:
                System.out.println("Tipo de ação desconhecido: " + tipo);
                return null;
        }
    }

    // Desfaz a última ação
    public void desfazer() {
        if (pilhaDesfazer.isEmpty()) {
            System.out.println("Nada para desfazer.");
            return;
        }
        Acao acao = pilhaDesfazer.pop();
        String textoDepois = texto;          // como o texto estava após a ação
        texto = acao.getTextoAnterior();     // restaura o estado de antes

        // Na pilha de refazer, o "textoAnterior" guarda o texto de DEPOIS da ação,
        // que é o que será restaurado ao refazer.
        pilhaRefazer.push(new Acao(acao.getTipo(), acao.getConteudo(), textoDepois));

        System.out.println("Desfeito: " + acao);
        System.out.println("   Texto: \"" + texto + "\"");
    }

    // Refaz a última ação desfeita
    public void refazer() {
        if (pilhaRefazer.isEmpty()) {
            System.out.println("Nada para refazer.");
            return;
        }
        Acao acao = pilhaRefazer.pop();
        String textoAntes = texto;           // estado atual, vira o "anterior" da ação
        texto = acao.getTextoAnterior();     // aqui guarda o texto de depois da ação

        pilhaDesfazer.push(new Acao(acao.getTipo(), acao.getConteudo(), textoAntes));

        System.out.println("Refeito: " + acao);
        System.out.println("   Texto: \"" + texto + "\"");
    }

    public void mostrarTexto() {
        System.out.println("Texto atual: \"" + texto + "\"");
    }

    public void mostrarHistorico() {
        System.out.println("Histórico (desfazer): " + pilhaDesfazer);
    }

    public String getTexto() {
        return texto;
    }

    public int tamanhoHistorico() {
        return pilhaDesfazer.size();
    }

}
