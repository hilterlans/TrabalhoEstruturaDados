package naoLinearesArvore.Questao7;
import naoLinearesArvore.EstruturaBST;

public class MainCadastro {
    public static void main(String[] args) {
        EstruturaBST<Aluno> arvoreAlunos = new EstruturaBST<>();

        arvoreAlunos.inserir(new Aluno("2026003", "Carlos Silva", "Engenharia de Software"));
        arvoreAlunos.inserir(new Aluno("2026001", "Ana Maria", "Direito"));
        arvoreAlunos.inserir(new Aluno("2026002", "Bruno Costa", "Administração"));

        System.out.println("--- Alunos Cadastrados (Ordem Crescente de Matrícula) ---");
        arvoreAlunos.inOrder(aluno -> System.out.println(aluno.toString()));

        System.out.println("\nBuscando matrícula 2026002:");
        Aluno busca = new Aluno("2026002", "", "");
        Aluno resultado = arvoreAlunos.buscar(busca);
        if(resultado != null) {
            System.out.println("Encontrado: " + resultado);
        } else {
            System.out.println("Não encontrado.");
        }
    }
}
