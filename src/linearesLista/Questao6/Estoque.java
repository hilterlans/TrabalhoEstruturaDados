package linearesLista.Questao6;

public class Estoque {
    private static class NoProduto {
        Produto produto;
        NoProduto proximo;

        NoProduto(Produto produto) { this.produto = produto; }
    }

    private NoProduto inicio;
    private int tamanho;

    public boolean cadastrarProduto(Produto produto) {
        if (produto == null || buscarPorCodigo(produto.getCodigo()) != null) {
            return false;
        }
        NoProduto novo = new NoProduto(produto);
        if (inicio == null) {
            inicio = novo;
        } else {
            NoProduto atual = inicio;
            while (atual.proximo != null) atual = atual.proximo;
            atual.proximo = novo;
        }
        tamanho++;
        return true;
    }

    public Produto buscarPorCodigo(String codigo) {
        if (codigo == null) return null;
        NoProduto atual = inicio;
        while (atual != null) {
            if (atual.produto.getCodigo().equalsIgnoreCase(codigo)) return atual.produto;
            atual = atual.proximo;
        }
        return null;
    }

    public boolean atualizarQuantidade(String codigo, int novaQuantidade) {
        Produto produto = buscarPorCodigo(codigo);
        if (produto == null || novaQuantidade < 0) return false;
        produto.setQuantidade(novaQuantidade);
        return true;
    }

    public boolean atualizarPreco(String codigo, double novoPreco) {
        Produto produto = buscarPorCodigo(codigo);
        if (produto == null || novoPreco < 0) return false;
        produto.setPreco(novoPreco);
        return true;
    }

    public Produto removerProduto(String codigo) {
        if (codigo == null || inicio == null) return null;
        if (inicio.produto.getCodigo().equalsIgnoreCase(codigo)) {
            Produto removido = inicio.produto;
            inicio = inicio.proximo;
            tamanho--;
            return removido;
        }
        NoProduto anterior = inicio;
        NoProduto atual = inicio.proximo;
        while (atual != null) {
            if (atual.produto.getCodigo().equalsIgnoreCase(codigo)) {
                anterior.proximo = atual.proximo;
                tamanho--;
                return atual.produto;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return null;
    }

    public void listarProdutos() {
        if (inicio == null) {
            System.out.println("Estoque vazio.");
            return;
        }
        NoProduto atual = inicio;
        while (atual != null) {
            System.out.println(atual.produto);
            atual = atual.proximo;
        }
    }

    public int getTamanho() { return tamanho; }

    public double calcularValorTotal() {
        double total = 0;
        NoProduto atual = inicio;
        while (atual != null) {
            total += atual.produto.getPreco() * atual.produto.getQuantidade();
            atual = atual.proximo;
        }
        return total;
    }
}
