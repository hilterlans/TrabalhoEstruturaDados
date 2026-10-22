package naolinearesarvore.questao8;
import naolinearesarvore.EstruturaBST;

public class MainAutocomplete {
    public static void main(String[] args) {
        EstruturaBST<ProdutoTrie> arvoreProdutos = new EstruturaBST<>();

        arvoreProdutos.inserir(new ProdutoTrie("Teclado Mecânico"));
        arvoreProdutos.inserir(new ProdutoTrie("Mouse Gamer"));
        arvoreProdutos.inserir(new ProdutoTrie("Monitor 144hz"));
        arvoreProdutos.inserir(new ProdutoTrie("Mousepad"));
        arvoreProdutos.inserir(new ProdutoTrie("Teclado Sem Fio"));

        System.out.println("--- Sugestões para 'Mo' ---");
        sugerir(arvoreProdutos.getRaiz(), "Mo");
    }

    public static void sugerir(EstruturaBST<ProdutoTrie>.No raiz, String prefixo) {
        if (raiz == null) return;
        
        String nome = raiz.dado.getNome();
        if (nome.toLowerCase().startsWith(prefixo.toLowerCase())) {
            System.out.println(nome);
        }
        
        sugerir(raiz.esq, prefixo);
        sugerir(raiz.dir, prefixo);
    }
}
