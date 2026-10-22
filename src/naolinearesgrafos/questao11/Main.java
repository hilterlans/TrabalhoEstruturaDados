package naolinearesgrafos.questao11;

public class Main {
    public static void main(String[] args) {
        RoteamentoEntregas roteamento = new RoteamentoEntregas();
        String cd = RoteamentoEntregas.CENTRO_DISTRIBUICAO;

        System.out.println("===== QUESTÃO 11 – ROTEAMENTO DE ENTREGAS =====\n");
        roteamento.mostrarMapa();

        System.out.println("\n----- Rotas a partir do centro de distribuição -----");
        roteamento.mostrarRota(cd, "Lagoa Azul");
        roteamento.mostrarRota(cd, "Jardim das Flores");
        roteamento.mostrarRota(cd, "Vila Esperança");

        System.out.println("\n----- Rotas entre outros pares de pontos -----");
        roteamento.mostrarRota("Vila Nova", "Alto da Colina");
        roteamento.mostrarRota("Porto Seco", "Bairro Universitário");
        roteamento.mostrarRota("Parque Verde", "Residencial Sol");

        System.out.println("\n----- Casos de erro -----");
        roteamento.mostrarRota(cd, "Ilha Remota");     // existe, mas sem estrada
        roteamento.mostrarRota(cd, "Bairro Fantasma"); // não existe no mapa
        roteamento.mostrarRota(cd, cd);                // origem = destino

        System.out.println("\n----- Tempo até todos os pontos -----");
        roteamento.mostrarTempoAteTodosOsPontos(cd);
    }
}

