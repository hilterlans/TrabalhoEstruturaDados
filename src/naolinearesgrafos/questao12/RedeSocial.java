/**
 * QUESTÃO 12 - Rede de Amizades em uma Rede Social Simples
 *
 **/
package naolinearesgrafos.questao12;

import naolinearesgrafos.EstruturaGrafos;

public class RedeSocial {

    private static final int PESO_AMIZADE = 1;

    private final EstruturaGrafos grafo;

    public RedeSocial() {
        this.grafo = new EstruturaGrafos();
    }

    // ------------------------------------------------------------------
    // Operações básicas
    // ------------------------------------------------------------------

    /** Cadastra um novo usuário na rede. */
    public void adicionarUsuario(String nome) {
        grafo.adicionarVertice(nome);
    }

    /** Cria uma amizade (não-direcionada) entre dois usuários existentes. */
    public void adicionarAmizade(String usuarioA, String usuarioB) {
        if (usuarioA.equals(usuarioB)) {
            throw new IllegalArgumentException("Um usuário não pode ser amigo de si mesmo: " + usuarioA);
        }
        if (grafo.temAresta(usuarioA, usuarioB)) {
            throw new IllegalArgumentException("Amizade já existe: " + usuarioA + " e " + usuarioB);
        }
        grafo.adicionarAresta(usuarioA, usuarioB, PESO_AMIZADE);
    }

    public int getTotalUsuarios() {
        return grafo.getTotalVertices();
    }

    public int getTotalAmizades() {
        return grafo.getTotalArestas();
    }

    /** Lista os amigos diretos de um usuário. */
    public String[] listarAmigos(String usuario) {
        return grafo.vizinhosDe(usuario);
    }

    // ------------------------------------------------------------------
    // Consultas de relacionamento
    // ------------------------------------------------------------------

    /** Amigos que os dois usuários têm em comum (interseção das listas). */
    public String[] amigosEmComum(String usuarioA, String usuarioB) {
        String[] amigosA = grafo.vizinhosDe(usuarioA);
        String[] amigosB = grafo.vizinhosDe(usuarioB);

        String[] temporario = new String[amigosA.length];
        int quantidade = 0;
        for (String amigo : amigosA) {
            if (contem(amigosB, amigo)) {
                temporario[quantidade++] = amigo;
            }
        }
        return copiar(temporario, quantidade);
    }

    /**
     * Graus de separação entre dois usuários via BFS:
     * 0 = mesma pessoa, 1 = amigos diretos, 2 = amigo de um amigo, ...
     * Retorna -1 se não há nenhuma ligação entre eles.
     */
    public int grausDeSeparacao(String usuarioA, String usuarioB) {
        EstruturaGrafos.Caminho caminho = grafo.menorCaminhoPorSaltos(usuarioA, usuarioB);
        return caminho == null ? -1 : caminho.getCusto();
    }

    /** Cadeia de pessoas que liga A a B (null se não conectados). */
    public String[] cadeiaDeConexao(String usuarioA, String usuarioB) {
        EstruturaGrafos.Caminho caminho = grafo.menorCaminhoPorSaltos(usuarioA, usuarioB);
        return caminho == null ? null : caminho.getVertices();
    }

    /**
     * Sugere conexões: pessoas que ainda não são amigas do usuário, ordenadas
     * pela quantidade de amigos em comum (maior primeiro). Só entram na lista
     * pessoas com pelo menos 1 amigo em comum.
     * Cada posição devolvida tem o formato "Nome (N em comum)".
     */
    public String[] sugerirAmigos(String usuario) {
        int total = grafo.getTotalVertices();
        String[] candidatos = new String[total];
        int[] emComum = new int[total];
        int quantidade = 0;

        for (int i = 0; i < total; i++) {
            String outro = grafo.getNome(i);
            if (outro.equals(usuario) || grafo.temAresta(usuario, outro)) {
                continue;
            }
            int comuns = amigosEmComum(usuario, outro).length;
            if (comuns == 0) {
                continue;
            }
            // Inserção ordenada (decrescente por amigos em comum).
            int posicao = quantidade;
            while (posicao > 0 && emComum[posicao - 1] < comuns) {
                candidatos[posicao] = candidatos[posicao - 1];
                emComum[posicao] = emComum[posicao - 1];
                posicao--;
            }
            candidatos[posicao] = outro;
            emComum[posicao] = comuns;
            quantidade++;
        }

        String[] resultado = new String[quantidade];
        for (int i = 0; i < quantidade; i++) {
            resultado[i] = candidatos[i] + " (" + emComum[i] + " em comum)";
        }
        return resultado;
    }

    /** Imprime a lista de adjacência da rede (usuário -> amigos). */
    public void imprimirRede() {
        grafo.imprimirListaDeAdjacencia();
    }

    // ------------------------------------------------------------------
    // Auxiliares
    // ------------------------------------------------------------------

    private boolean contem(String[] vetor, String valor) {
        for (String item : vetor) {
            if (item.equals(valor)) {
                return true;
            }
        }
        return false;
    }

    private String[] copiar(String[] origem, int quantidade) {
        String[] copia = new String[quantidade];
        for (int i = 0; i < quantidade; i++) {
            copia[i] = origem[i];
        }
        return copia;
    }
}