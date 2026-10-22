/**
 * QUESTÃO 6 – Controle de estoque de uma pequena loja (Lista Encadeada)
 *
 * Justificativa de escolha:
 *   O estoque cresce e diminui o tempo todo (produtos novos entram e
 *   esgotados saem) e não dá para saber o tamanho máximo de antemão. A lista
 *   encadeada permite inserir no fim e remover qualquer produto sem
 *   deslocar os demais, ao contrário de um array de tamanho fixo.
 *
 * Complexidade (n = produtos na lista):
 *   - cadastrar ................. O(n) (confere código repetido e insere no fim)
 *   - buscar por código/nome .... O(n)
 *   - atualizar / entrada / saída O(n) (a busca domina; a alteração é O(1))
 *   - remover produto ........... O(n)
 *   - listar abaixo do mínimo ... O(n)
 *   - remover esgotados ......... O(n * e), com e = quantidade de esgotados
 *   - Espaço .................... O(n)
 */
package lineareslista.questao6;

import lineareslista.EstruturaLista;

public class Estoque {
    private final EstruturaLista<Produto> produtos = new EstruturaLista<>();

    /** Cadastra o produto. Devolve false se o código já existe. */
    public boolean cadastrarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto nulo.");
        }
        if (buscarPorCodigo(produto.getCodigo()) != null) {
            return false;
        }
        produtos.inserirFim(produto);
        return true;
    }

    public Produto buscarPorCodigo(String codigo) {
        if (codigo == null) return null;
        for (EstruturaLista<Produto>.No no = produtos.getCabeca(); no != null; no = no.proximo) {
            if (no.dado.getCodigo().equalsIgnoreCase(codigo)) return no.dado;
        }
        return null;
    }

    public Produto buscarPorNome(String nome) {
        if (nome == null) return null;
        for (EstruturaLista<Produto>.No no = produtos.getCabeca(); no != null; no = no.proximo) {
            if (no.dado.getNome().equalsIgnoreCase(nome)) return no.dado;
        }
        return null;
    }

    /** Define a quantidade (ex.: acerto de inventário). */
    public void atualizarQuantidade(String codigo, int novaQuantidade) {
        if (novaQuantidade < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }
        exigir(codigo).setQuantidade(novaQuantidade);
    }

    public void atualizarPreco(String codigo, double novoPreco) {
        if (novoPreco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        exigir(codigo).setPreco(novoPreco);
    }

    /** Entrada de mercadoria: soma à quantidade atual. */
    public void registrarEntrada(String codigo, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de entrada deve ser positiva.");
        }
        Produto p = exigir(codigo);
        p.setQuantidade(p.getQuantidade() + quantidade);
    }

    /** Saída (venda): não permite deixar o estoque negativo. */
    public void registrarSaida(String codigo, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de saída deve ser positiva.");
        }
        Produto p = exigir(codigo);
        if (quantidade > p.getQuantidade()) {
            throw new IllegalStateException("Estoque insuficiente de " + p.getNome()
                    + ": pedido " + quantidade + ", disponível " + p.getQuantidade() + ".");
        }
        p.setQuantidade(p.getQuantidade() - quantidade);
    }

    /** Remove o produto da lista ativa. Devolve o produto removido ou null. */
    public Produto removerProduto(String codigo) {
        Produto p = buscarPorCodigo(codigo);
        if (p == null) return null;
        produtos.remover(p);
        return p;
    }

    /** Remove da lista ativa todos os produtos esgotados e devolve quantos saíram. */
    public int removerEsgotados() {
        int removidos = 0;
        boolean achou = true;
        while (achou) {
            achou = false;
            for (EstruturaLista<Produto>.No no = produtos.getCabeca(); no != null; no = no.proximo) {
                if (no.dado.estaEsgotado()) {
                    System.out.println("   Esgotado e removido da lista ativa: " + no.dado.getNome());
                    produtos.remover(no.dado);
                    removidos++;
                    achou = true;
                    break;
                }
            }
        }
        return removidos;
    }

    public void listarProdutos() {
        if (produtos.getCabeca() == null) {
            System.out.println("Estoque vazio.");
            return;
        }
        for (EstruturaLista<Produto>.No no = produtos.getCabeca(); no != null; no = no.proximo) {
            System.out.println("   " + no.dado);
        }
    }

    /** Lista os produtos com quantidade menor que o estoque mínimo. Devolve quantos são. */
    public int listarAbaixoDoMinimo() {
        int total = 0;
        for (EstruturaLista<Produto>.No no = produtos.getCabeca(); no != null; no = no.proximo) {
            if (no.dado.abaixoDoMinimo()) {
                System.out.println("   ATENÇÃO: " + no.dado);
                total++;
            }
        }
        if (total == 0) {
            System.out.println("   Nenhum produto abaixo do estoque mínimo.");
        }
        return total;
    }

    public double calcularValorTotal() {
        double total = 0;
        for (EstruturaLista<Produto>.No no = produtos.getCabeca(); no != null; no = no.proximo) {
            total += no.dado.getPreco() * no.dado.getQuantidade();
        }
        return total;
    }

    public int getTamanho() { return produtos.getTamanho(); }

    private Produto exigir(String codigo) {
        Produto p = buscarPorCodigo(codigo);
        if (p == null) {
            throw new IllegalArgumentException("Produto não encontrado: código " + codigo);
        }
        return p;
    }
}
