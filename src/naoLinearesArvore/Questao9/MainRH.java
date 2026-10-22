package naoLinearesArvore.Questao9;
import naoLinearesArvore.EstruturaAVL;

public class MainRH {
    public static void main(String[] args) {
        EstruturaAVL<Funcionario> arvoreRH = new EstruturaAVL<>();

        arvoreRH.inserir(new Funcionario("111.111.111-11", "João Silva"));
        arvoreRH.inserir(new Funcionario("333.333.333-33", "Marcos Nunes"));
        arvoreRH.inserir(new Funcionario("222.222.222-22", "Fernanda Lima"));

        System.out.println("--- Funcionários Balanceados na AVL ---");
        arvoreRH.inOrder(func -> System.out.println(func.toString()));
    }
}
