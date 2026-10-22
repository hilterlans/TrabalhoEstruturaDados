/**
 * ESTRUTURA DE DADOS: Grafo Ponderado (Lista de Adjacência)
 *
 * Representação:
 *   - Cada vértice tem um índice (0..n-1) e um nome.
 *   - Cada vértice possui uma lista encadeada de arestas (lista de adjacência).
 *
 * Complexidade (V = vértices, E = arestas):
 *   - adicionarVertice ........ O(1) amortizado (dobra o vetor quando enche)
 *   - adicionarAresta ......... O(1)
 *   - indiceDe (busca nome) ... O(V)
 *   - Dijkstra (vetor simples)  O(V²) tempo, O(V) espaço extra
 *   - BFS (menor nº de saltos)  O(V + E) tempo, O(V) espaço extra
 *   - Espaço total do grafo ... O(V + E)
 */
package naolinearesgrafos;
public class EstruturaGrafos {

    /** Nó da lista encadeada de adjacência: aresta saindo de um vértice. */
    private static class Aresta {
        int destino;
        int peso;
        Aresta proxima;

        Aresta(int destino, int peso, Aresta proxima) {
            this.destino = destino;
            this.peso = peso;
            this.proxima = proxima;
        }
    }

    /** Resultado de uma consulta de caminho mínimo. */
    public static class Caminho {
        private final int custo;
        private final String[] vertices; // do origem até o destino, na ordem

        Caminho(int custo, String[] vertices) {
            this.custo = custo;
            this.vertices = vertices;
        }

        public int getCusto() {
            return custo;
        }

        public String[] getVertices() {
            return vertices;
        }

        public int getQuantidadeDeParadas() {
            return vertices.length - 1;
        }
    }

    private static final int CAPACIDADE_INICIAL = 8;
    private static final int INFINITO = Integer.MAX_VALUE;

    private String[] nomes;
    private Aresta[] adjacencia;
    private int totalVertices;
    private int totalArestas;

    public EstruturaGrafos() {
        this.nomes = new String[CAPACIDADE_INICIAL];
        this.adjacencia = new Aresta[CAPACIDADE_INICIAL];
        this.totalVertices = 0;
        this.totalArestas = 0;
    }

    // ------------------------------------------------------------------
    // Construção do grafo
    // ------------------------------------------------------------------

    /** Adiciona um vértice e devolve o seu índice. Nome repetido é erro. */
    public int adicionarVertice(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do vértice não pode ser vazio.");
        }
        if (indiceDe(nome) != -1) {
            throw new IllegalArgumentException("Vértice já existe: " + nome);
        }
        if (totalVertices == nomes.length) {
            aumentarCapacidade();
        }
        nomes[totalVertices] = nome;
        adjacencia[totalVertices] = null;
        return totalVertices++;
    }

    /** Aresta bidirecional (ruas de mão dupla). */
    public void adicionarAresta(String origem, String destino, int peso) {
        int a = indiceObrigatorio(origem);
        int b = indiceObrigatorio(destino);
        validarPeso(peso);
        adjacencia[a] = new Aresta(b, peso, adjacencia[a]);
        adjacencia[b] = new Aresta(a, peso, adjacencia[b]);
        totalArestas++;
    }

    /** Aresta com sentido único (ruas de mão única). */
    public void adicionarArestaDirecionada(String origem, String destino, int peso) {
        int a = indiceObrigatorio(origem);
        int b = indiceObrigatorio(destino);
        validarPeso(peso);
        adjacencia[a] = new Aresta(b, peso, adjacencia[a]);
        totalArestas++;
    }

    // ------------------------------------------------------------------
    // Consultas
    // ------------------------------------------------------------------

    public int getTotalVertices() {
        return totalVertices;
    }

    public int getTotalArestas() {
        return totalArestas;
    }

    public String getNome(int indice) {
        if (indice < 0 || indice >= totalVertices) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
        }
        return nomes[indice];
    }

    /** Devolve o índice do vértice ou -1 se não existir. */
    public int indiceDe(String nome) {
        for (int i = 0; i < totalVertices; i++) {
            if (nomes[i].equals(nome)) {
                return i;
            }
        }
        return -1;
    }

    /** Indica se existe aresta saindo de origem e chegando em destino. O(grau). */
    public boolean temAresta(String origem, String destino) {
        int a = indiceObrigatorio(origem);
        int b = indiceObrigatorio(destino);
        for (Aresta atual = adjacencia[a]; atual != null; atual = atual.proxima) {
            if (atual.destino == b) {
                return true;
            }
        }
        return false;
    }

    /** Nomes dos vizinhos diretos de um vértice (ordem de inserção inversa). O(grau). */
    public String[] vizinhosDe(String nome) {
        int v = indiceObrigatorio(nome);
        int grau = 0;
        for (Aresta atual = adjacencia[v]; atual != null; atual = atual.proxima) {
            grau++;
        }
        String[] vizinhos = new String[grau];
        int i = 0;
        for (Aresta atual = adjacencia[v]; atual != null; atual = atual.proxima) {
            vizinhos[i++] = nomes[atual.destino];
        }
        return vizinhos;
    }

    /** Imprime a lista de adjacência: vértice -> [vizinho (peso), ...]. */
    public void imprimirListaDeAdjacencia() {
        for (int i = 0; i < totalVertices; i++) {
            StringBuilder linha = new StringBuilder();
            linha.append(String.format("  %-24s -> ", nomes[i]));
            Aresta atual = adjacencia[i];
            if (atual == null) {
                linha.append("(sem conexões)");
            }
            while (atual != null) {
                linha.append(nomes[atual.destino]).append(" (").append(atual.peso).append(")");
                if (atual.proxima != null) {
                    linha.append(", ");
                }
                atual = atual.proxima;
            }
            System.out.println(linha);
        }
    }

    // ------------------------------------------------------------------
    // Busca em largura (BFS) - menor número de saltos, ignorando pesos
    // ------------------------------------------------------------------

    /**
     * Caminho com o MENOR NÚMERO DE ARESTAS entre dois vértices (BFS).
     * O custo devolvido é o número de saltos (graus de separação).
     * Retorna null se o destino não for alcançável.
     */
    public Caminho menorCaminhoPorSaltos(String origem, String destino) {
        int s = indiceObrigatorio(origem);
        int t = indiceObrigatorio(destino);

        int[] anterior = new int[totalVertices];
        int[] nivel = new int[totalVertices];
        for (int i = 0; i < totalVertices; i++) {
            anterior[i] = -1;
            nivel[i] = -1;
        }

        // Fila circular simples feita com vetor (cada vértice entra no máximo 1 vez).
        int[] fila = new int[totalVertices];
        int inicio = 0;
        int fim = 0;
        fila[fim++] = s;
        nivel[s] = 0;

        while (inicio < fim && nivel[t] == -1) {
            int u = fila[inicio++];
            for (Aresta a = adjacencia[u]; a != null; a = a.proxima) {
                if (nivel[a.destino] == -1) {
                    nivel[a.destino] = nivel[u] + 1;
                    anterior[a.destino] = u;
                    fila[fim++] = a.destino;
                }
            }
        }

        if (nivel[t] == -1) {
            return null;
        }

        String[] rota = new String[nivel[t] + 1];
        int posicao = rota.length - 1;
        for (int v = t; v != -1; v = anterior[v]) {
            rota[posicao--] = nomes[v];
        }
        return new Caminho(nivel[t], rota);
    }

    // ------------------------------------------------------------------
    // Algoritmo de Dijkstra
    // ------------------------------------------------------------------

    /**
     * Caminho de menor custo entre dois vértices.
     * Retorna null se o destino não for alcançável a partir da origem.
     */
    public Caminho menorCaminho(String origem, String destino) {
        int s = indiceObrigatorio(origem);
        int t = indiceObrigatorio(destino);

        int[] distancia = new int[totalVertices];
        int[] anterior = new int[totalVertices];
        dijkstra(s, distancia, anterior);

        if (distancia[t] == INFINITO) {
            return null;
        }

        // Reconstrói o caminho andando de trás pra frente (destino -> origem).
        int tamanho = 1;
        for (int v = t; v != s; v = anterior[v]) {
            tamanho++;
        }
        String[] rota = new String[tamanho];
        int posicao = tamanho - 1;
        for (int v = t; v != -1; v = anterior[v]) {
            rota[posicao--] = nomes[v];
        }
        return new Caminho(distancia[t], rota);
    }

    /**
     * Menor custo da origem até TODOS os vértices.
     * Posição i do vetor = custo até o vértice de índice i (-1 se inalcançável).
     */
    public int[] menoresCustosDe(String origem) {
        int s = indiceObrigatorio(origem);
        int[] distancia = new int[totalVertices];
        int[] anterior = new int[totalVertices];
        dijkstra(s, distancia, anterior);
        for (int i = 0; i < totalVertices; i++) {
            if (distancia[i] == INFINITO) {
                distancia[i] = -1;
            }
        }
        return distancia;
    }

    /** Dijkstra com busca linear do vértice de menor distância: O(V²). */
    private void dijkstra(int origem, int[] distancia, int[] anterior) {
        boolean[] fechado = new boolean[totalVertices];
        for (int i = 0; i < totalVertices; i++) {
            distancia[i] = INFINITO;
            anterior[i] = -1;
        }
        distancia[origem] = 0;

        for (int rodada = 0; rodada < totalVertices; rodada++) {
            // 1) escolhe o vértice aberto com a menor distância conhecida
            int u = -1;
            for (int i = 0; i < totalVertices; i++) {
                if (!fechado[i] && distancia[i] != INFINITO
                        && (u == -1 || distancia[i] < distancia[u])) {
                    u = i;
                }
            }
            if (u == -1) {
                break; // o que sobrou é inalcançável
            }
            fechado[u] = true;

            // 2) relaxa as arestas que saem de u
            for (Aresta a = adjacencia[u]; a != null; a = a.proxima) {
                int novaDistancia = distancia[u] + a.peso;
                if (novaDistancia < distancia[a.destino]) {
                    distancia[a.destino] = novaDistancia;
                    anterior[a.destino] = u;
                }
            }
        }
    }

    // ------------------------------------------------------------------
    // Auxiliares privados
    // ------------------------------------------------------------------

    private int indiceObrigatorio(String nome) {
        int indice = indiceDe(nome);
        if (indice == -1) {
            throw new IllegalArgumentException("Vértice inexistente: " + nome);
        }
        return indice;
    }

    private void validarPeso(int peso) {
        if (peso < 0) {
            throw new IllegalArgumentException("Dijkstra não aceita peso negativo: " + peso);
        }
    }

    private void aumentarCapacidade() {
        int novaCapacidade = nomes.length * 2;
        String[] novosNomes = new String[novaCapacidade];
        Aresta[] novaAdjacencia = new Aresta[novaCapacidade];
        for (int i = 0; i < totalVertices; i++) {
            novosNomes[i] = nomes[i];
            novaAdjacencia[i] = adjacencia[i];
        }
        nomes = novosNomes;
        adjacencia = novaAdjacencia;
    }
}