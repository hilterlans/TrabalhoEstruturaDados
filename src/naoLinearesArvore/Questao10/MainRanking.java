package naoLinearesArvore.Questao10;
import naoLinearesArvore.EstruturaAVL;

public class MainRanking {
    public static void main(String[] args) {
        EstruturaAVL<Jogador> ranking = new EstruturaAVL<>();

        ranking.inserir(new Jogador("ProPlayerBR", 1500));
        ranking.inserir(new Jogador("NoobMaster", 800));
        ranking.inserir(new Jogador("EmanuelDev", 2500)); // Homenagem

        System.out.println("--- Ranking de Jogadores (Ordem Decrescente) ---");
        // inOrder da AVL implementada percorre Dir -> Raiz -> Esq, então será decrescente.
        ranking.inOrder(jogador -> System.out.println(jogador.toString()));
    }
}
