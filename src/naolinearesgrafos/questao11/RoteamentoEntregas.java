package naolinearesgrafos.questao11;

import naolinearesgrafos.EstruturaGrafos;

public class RoteamentoEntregas {
    public static final String CENTRO_DISTRIBUICAO = "Centro de Distribuição";

    private final EstruturaGrafos mapa;

    public RoteamentoEntregas() {
        this.mapa = new EstruturaGrafos();
        montarMapaDaCidade();
    }

    /** 13 vértices (12 conectados + 1 ilha sem estrada, para testar o erro). */
    private void montarMapaDaCidade() {
        String[] pontos = {
                CENTRO_DISTRIBUICAO, "Centro Comercial", "Bairro Industrial", "Vila Nova",
                "Jardim das Flores", "Bairro Universitário", "Parque Verde", "Residencial Sol",
                "Alto da Colina", "Vila Esperança", "Porto Seco", "Lagoa Azul",
                "Ilha Remota"
        };
        for (String ponto : pontos) {
            mapa.adicionarVertice(ponto);
        }

        // ruas de mão dupla: (ponto A, ponto B, minutos)
        mapa.adicionarAresta(CENTRO_DISTRIBUICAO, "Centro Comercial", 5);
        mapa.adicionarAresta(CENTRO_DISTRIBUICAO, "Bairro Industrial", 8);
        mapa.adicionarAresta(CENTRO_DISTRIBUICAO, "Vila Nova", 12);
        mapa.adicionarAresta("Centro Comercial", "Jardim das Flores", 7);
        mapa.adicionarAresta("Centro Comercial", "Bairro Universitário", 6);
        mapa.adicionarAresta("Vila Nova", "Bairro Industrial", 4);
        mapa.adicionarAresta("Vila Nova", "Parque Verde", 9);
        mapa.adicionarAresta("Bairro Industrial", "Porto Seco", 11);
        mapa.adicionarAresta("Jardim das Flores", "Bairro Universitário", 3);
        mapa.adicionarAresta("Jardim das Flores", "Residencial Sol", 10);
        mapa.adicionarAresta("Bairro Universitário", "Alto da Colina", 8);
        mapa.adicionarAresta("Parque Verde", "Vila Esperança", 6);
        mapa.adicionarAresta("Parque Verde", "Lagoa Azul", 14);
        mapa.adicionarAresta("Residencial Sol", "Alto da Colina", 5);
        mapa.adicionarAresta("Residencial Sol", "Lagoa Azul", 9);
        mapa.adicionarAresta("Vila Esperança", "Porto Seco", 7);
        mapa.adicionarAresta("Alto da Colina", "Lagoa Azul", 4);
        mapa.adicionarAresta("Porto Seco", "Lagoa Azul", 10);
        // "Ilha Remota" fica sem nenhuma estrada de propósito.
    }

    public void mostrarMapa() {
        System.out.println("Mapa da cidade (lista de adjacência, tempo em minutos):");
        mapa.imprimirListaDeAdjacencia();
        System.out.println("\nTotal: " + mapa.getTotalVertices() + " pontos e "
                + mapa.getTotalArestas() + " ruas.");
    }

    /** Calcula e imprime a rota de menor tempo entre dois pontos. */
    public void mostrarRota(String origem, String destino) {
        System.out.println("Rota: " + origem + " -> " + destino);
        try {
            EstruturaGrafos.Caminho caminho = mapa.menorCaminho(origem, destino);
            if (caminho == null) {
                System.out.println("  Sem rota possível (destino inalcançável).");
                return;
            }
            System.out.println("  Percurso : " + juntar(caminho.getVertices()));
            System.out.println("  Trechos  : " + caminho.getQuantidadeDeParadas());
            System.out.println("  Tempo    : " + caminho.getCusto() + " min");
        } catch (IllegalArgumentException erro) {
            System.out.println("  Erro: " + erro.getMessage());
        }
    }

    /** Tabela com o tempo do centro de distribuição até todos os pontos. */
    public void mostrarTempoAteTodosOsPontos(String origem) {
        System.out.println("Tempo de entrega a partir de \"" + origem + "\":");
        int[] tempos = mapa.menoresCustosDe(origem);
        for (int i = 0; i < tempos.length; i++) {
            String texto = tempos[i] == -1 ? "inalcançável" : tempos[i] + " min";
            System.out.printf("  %-24s %s%n", mapa.getNome(i), texto);
        }
    }

    private String juntar(String[] vertices) {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < vertices.length; i++) {
            texto.append(vertices[i]);
            if (i < vertices.length - 1) {
                texto.append(" -> ");
            }
        }
        return texto.toString();
    }
}
