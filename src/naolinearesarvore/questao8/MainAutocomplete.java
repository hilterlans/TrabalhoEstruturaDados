package naolinearesarvore.questao8;

public class MainAutocomplete {
    public static void main(String[] args) {
        AutocompleteProdutos auto = new AutocompleteProdutos();

        String[] produtos = {
            "Monitor 24 polegadas", "Mouse Gamer", "Teclado Mecânico", "Headset Gamer", "Webcam Full HD",
            "Mousepad Grande", "Notebook 15 polegadas", "Carregador USB-C", "Cabo HDMI 2m", "Fone Bluetooth",
            "Caixa de Som Portátil", "Cadeira Gamer", "Mesa para Computador", "Monitor 144hz", "Teclado Sem Fio",
            "Tablet 10 polegadas", "Smartphone 128GB", "Capa para Celular", "Carregador Portátil", "SSD 480GB",
            "HD Externo 1TB", "Pen Drive 64GB", "Roteador Wi-Fi 6", "Impressora Multifuncional", "Cartucho de Tinta Preta",
            "Mochila para Notebook", "Suporte para Monitor", "Hub USB 4 portas", "Microfone Condensador", "Mouse Sem Fio",
            "Fonte 500W", "Placa de Vídeo 8GB"
        };

        System.out.println("===== QUESTÃO 8 – AUTOCOMPLETE DE PRODUTOS (BST) =====\n");
        for (String nome : produtos) {
            auto.cadastrar(nome);
        }
        boolean repetido = auto.cadastrar("mouse gamer");
        System.out.println("Produtos cadastrados: " + auto.getTotalProdutos() + " | Altura da árvore: " + auto.getAlturaArvore());
        System.out.println("Cadastrar 'mouse gamer' de novo: " + (repetido ? "aceito" : "recusado, produto já existe"));

        System.out.println();
        testar(auto, "Mo", 10);
        testar(auto, "mouse", 10);
        testar(auto, "Te", 10);
        testar(auto, "Ca", 10);
        testar(auto, "Carregador", 10);
        testar(auto, "Mo", 2);   // limite de sugestões
        testar(auto, "Z", 10);   // nenhum produto
        testar(auto, "", 5);     // prefixo vazio: primeiros 5 em ordem alfabética
    }

    private static void testar(AutocompleteProdutos auto, String prefixo, int limite) {
        String[] sugestoes = auto.sugerir(prefixo, limite);
        System.out.println("Digitou '" + prefixo + "' (máx. " + limite + " sugestões) -> "
                + sugestoes.length + " resultado(s), " + auto.getNosVisitados() + " nós visitados de " + auto.getTotalProdutos());
        if (sugestoes.length == 0) {
            System.out.println("   (nenhuma sugestão)");
        }
        for (String s : sugestoes) {
            System.out.println("   - " + s);
        }
    }
}
