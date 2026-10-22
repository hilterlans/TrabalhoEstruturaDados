package lineareslista.questao6;

public class MainEstoque {
    // Movimentos da semana: {tipo (E = entrada, S = saída), código, quantidade}
    private static final String[] DIAS = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
    private static final String[][][] MOVIMENTOS = {
        /* Segunda */ {{"S", "001", "30"}, {"S", "004", "400"}, {"E", "002", "10"}, {"S", "008", "15"}},
        /* Terça   */ {{"S", "001", "35"}, {"S", "005", "200"}, {"S", "009", "25"}, {"S", "012", "8"}},
        /* Quarta  */ {{"S", "010", "40"}, {"S", "003", "20"}, {"E", "001", "60"}, {"S", "999", "5"}},
        /* Quinta  */ {{"S", "006", "12"}, {"S", "007", "30"}, {"S", "011", "20"}, {"S", "001", "200"}},
        /* Sexta   */ {{"S", "004", "900"}, {"E", "004", "1500"}, {"S", "008", "30"}, {"S", "009", "40"}},
        /* Sábado  */ {{"S", "005", "450"}, {"S", "001", "50"}, {"E", "007", "50"}, {"S", "002", "25"}},
        /* Domingo */ {{"S", "012", "10"}, {"S", "006", "3"}, {"E", "005", "600"}}
    };

    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        System.out.println("===== QUESTÃO 6 – CONTROLE DE ESTOQUE (LISTA ENCADEADA) =====\n");
        cadastrarProdutosIniciais(estoque);

        System.out.println("--- Estoque inicial (" + estoque.getTamanho() + " produtos) ---");
        estoque.listarProdutos();
        System.out.printf("Valor total em estoque: R$ %.2f%n", estoque.calcularValorTotal());

        System.out.println("\n--- Operações de cadastro e consulta ---");
        boolean duplicado = estoque.cadastrarProduto(new Produto("001", "Cimento repetido", 1, 1.0));
        System.out.println("Cadastrar código repetido (001): " + (duplicado ? "aceito" : "recusado, código já existe"));
        System.out.println("Buscar por código 006: " + estoque.buscarPorCodigo("006"));
        System.out.println("Buscar por nome 'tubo pvc 100mm': " + estoque.buscarPorNome("tubo pvc 100mm"));
        System.out.println("Buscar por nome 'Martelo': " + estoque.buscarPorNome("Martelo"));
        estoque.atualizarPreco("001", 34.50);
        estoque.atualizarQuantidade("003", 25);
        System.out.println("Reajuste do cimento e inventário da brita aplicados.");

        System.out.println("\n===== SIMULAÇÃO DA SEMANA =====");
        for (int d = 0; d < DIAS.length; d++) {
            System.out.println("\n>>> " + DIAS[d]);
            for (String[] mov : MOVIMENTOS[d]) {
                executar(estoque, mov[0], mov[1], Integer.parseInt(mov[2]));
            }
            System.out.println("   -- Fechamento do dia --");
            estoque.listarAbaixoDoMinimo();
            estoque.removerEsgotados();
        }

        System.out.println("\n--- Estoque final (" + estoque.getTamanho() + " produtos ativos) ---");
        estoque.listarProdutos();
        System.out.printf("Valor total em estoque: R$ %.2f%n", estoque.calcularValorTotal());

        System.out.println("\n--- Remoção manual de produto ---");
        Produto removido = estoque.removerProduto("011");
        System.out.println("Removido: " + (removido != null ? removido.getNome() : "nenhum"));
        System.out.println("Remover código inexistente (555): " + (estoque.removerProduto("555") == null ? "não encontrado" : "removido"));
    }

    private static void cadastrarProdutosIniciais(Estoque e) {
        e.cadastrarProduto(new Produto("001", "Cimento 50kg", 120, 32.90, 40));
        e.cadastrarProduto(new Produto("002", "Areia média (m³)", 30, 95.00, 10));
        e.cadastrarProduto(new Produto("003", "Brita 1 (m³)", 25, 110.00, 10));
        e.cadastrarProduto(new Produto("004", "Tijolo 8 furos", 2000, 0.85, 500));
        e.cadastrarProduto(new Produto("005", "Telha cerâmica", 800, 2.40, 300));
        e.cadastrarProduto(new Produto("006", "Tinta acrílica 18L", 15, 289.90, 5));
        e.cadastrarProduto(new Produto("007", "Cal hidratada 20kg", 40, 14.50, 15));
        e.cadastrarProduto(new Produto("008", "Argamassa AC-II", 60, 22.90, 20));
        e.cadastrarProduto(new Produto("009", "Vergalhão 10mm", 80, 54.00, 30));
        e.cadastrarProduto(new Produto("010", "Prego 18x27 (kg)", 40, 12.00, 20));
        e.cadastrarProduto(new Produto("011", "Tubo PVC 100mm", 35, 48.50, 12));
        e.cadastrarProduto(new Produto("012", "Fio 2,5mm (rolo)", 20, 189.00, 6));
    }

    private static void executar(Estoque estoque, String tipo, String codigo, int quantidade) {
        try {
            if (tipo.equals("E")) {
                estoque.registrarEntrada(codigo, quantidade);
                System.out.println("   Entrada: " + quantidade + " un. do produto " + codigo);
            } else {
                estoque.registrarSaida(codigo, quantidade);
                System.out.println("   Saída:   " + quantidade + " un. do produto " + codigo);
            }
        } catch (IllegalArgumentException | IllegalStateException ex) {
            System.out.println("   ERRO ao movimentar " + codigo + ": " + ex.getMessage());
        }
    }
}
