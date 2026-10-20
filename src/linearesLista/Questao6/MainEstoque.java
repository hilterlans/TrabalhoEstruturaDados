package linearesLista.Questao6;

public class MainEstoque {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        System.out.println("===== QUESTAO 6: CONTROLE DE ESTOQUE =====");
        estoque.cadastrarProduto(new Produto("P001", "Teclado", 12, 79.90));
        estoque.cadastrarProduto(new Produto("P002", "Mouse", 20, 39.90));
        estoque.cadastrarProduto(new Produto("P003", "Monitor", 7, 699.00));
        estoque.cadastrarProduto(new Produto("P004", "Cabo HDMI", 25, 24.50));
        estoque.cadastrarProduto(new Produto("P005", "Headset", 10, 119.90));
        estoque.cadastrarProduto(new Produto("P006", "Webcam", 6, 159.00));
        estoque.cadastrarProduto(new Produto("P007", "Mousepad", 30, 19.90));
        estoque.cadastrarProduto(new Produto("P008", "SSD 480 GB", 8, 249.90));
        estoque.cadastrarProduto(new Produto("P009", "Memoria RAM 8 GB", 15, 129.90));
        estoque.cadastrarProduto(new Produto("P010", "Adaptador USB", 18, 29.90));

        System.out.println("\nProdutos cadastrados (" + estoque.getTamanho() + "):");
        estoque.listarProdutos();

        System.out.println("\nBuscando produto P003:");
        Produto produto = estoque.buscarPorCodigo("P003");
        System.out.println(produto == null ? "Produto nao encontrado." : produto);

        System.out.println("\nAtualizando estoque do mouse para 16 unidades:");
        System.out.println(estoque.atualizarQuantidade("P002", 16) ? "Quantidade atualizada." : "Produto nao encontrado.");

        System.out.println("\nAtualizando preco do teclado para R$ 84,90:");
        System.out.println(estoque.atualizarPreco("P001", 84.90) ? "Preco atualizado." : "Produto nao encontrado.");

        System.out.println("\nTentando cadastrar codigo duplicado P001:");
        System.out.println(estoque.cadastrarProduto(new Produto("P001", "Teclado repetido", 1, 50.00))
                ? "Produto cadastrado." : "Cadastro recusado: codigo ja existe.");

        System.out.println("\nRemovendo produto P004:");
        Produto removido = estoque.removerProduto("P004");
        System.out.println(removido == null ? "Produto nao encontrado." : "Removido: " + removido);

        System.out.printf("\nValor total do estoque: R$ %.2f%n", estoque.calcularValorTotal());
        System.out.println("\n===== ESTOQUE FINAL =====");
        estoque.listarProdutos();
    }
}
