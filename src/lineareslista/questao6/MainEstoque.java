package lineareslista.questao6;
import lineareslista.EstruturaLista;

public class MainEstoque {
    public static void main(String[] args) {
        EstruturaLista<Produto> estoque = new EstruturaLista<>();

        estoque.inserirFim(new Produto("001", "Cimento", 50, 29.90));
        estoque.inserirFim(new Produto("002", "Tijolo", 1000, 0.80));
        estoque.inserirFim(new Produto("003", "Argamassa", 20, 15.50));

        System.out.println("Estoque inicial:");
        listarEstoque(estoque);

        System.out.println("\nRemovendo Tijolo (002)...");
        estoque.remover(new Produto("002", "", 0, 0));
        listarEstoque(estoque);
    }

    public static void listarEstoque(EstruturaLista<Produto> estoque) {
        EstruturaLista<Produto>.No atual = estoque.getCabeca();
        while (atual != null) {
            System.out.println(atual.dado.toString());
            atual = atual.proximo;
        }
    }
}
