/**
 * QUESTÃO 7 – Cadastro e busca de alunos por matrícula (BST)
 *
 * Justificativa de escolha:
 *   A secretaria precisa de buscas rápidas por matrícula e de listagem
 *   ordenada, com cadastros e remoções frequentes. A BST mantém os alunos
 *   sempre ordenados (percurso em ordem) e busca/insere/remove em O(log n) em
 *   média, sem reordenar toda a coleção como aconteceria com um vetor.
 *   Limitação: sem balanceamento, a altura pode chegar a O(n) (ver Questão 9).
 *
 * Complexidade: inserir, buscar e remover O(h) (O(log n) médio, O(n) no pior
 * caso); listagem em ordem O(n); espaço O(n).
 */
package naolinearesarvore.questao7;

import naolinearesarvore.EstruturaBST;

public class MainCadastro {
    public static void main(String[] args) {
        EstruturaBST<Aluno> arvoreAlunos = new EstruturaBST<>();

        // Ordem de inserção escolhida para a árvore ficar equilibrada
        int[] ordem = {11, 5, 16, 3, 8, 13, 19, 1, 4, 6, 9, 12, 14, 17, 21, 2, 7, 10, 15, 18, 20, 22};
        String[] nomes = {
            "Ana Maria", "Bruno Costa", "Carlos Silva", "Daniela Rocha", "Eduardo Lima", "Fernanda Souza",
            "Gabriel Alves", "Helena Martins", "Igor Pereira", "Juliana Mendes", "Kleber Nunes", "Larissa Barros",
            "Marcos Teixeira", "Natália Ribeiro", "Otávio Cardoso", "Patrícia Gomes", "Rafael Duarte",
            "Sabrina Lopes", "Tiago Moreira", "Úrsula Freitas", "Vinícius Araújo", "Wesley Campos"
        };
        String[] cursos = {"Engenharia de Software", "Direito", "Administração", "Medicina", "Arquitetura", "Psicologia"};

        System.out.println("===== QUESTÃO 7 – CADASTRO DE ALUNOS (BST) =====\n");
        for (int i = 0; i < ordem.length; i++) {
            String matricula = String.format("2026%03d", ordem[i]);
            arvoreAlunos.inserir(new Aluno(matricula, nomes[i], cursos[i % cursos.length]));
        }
        System.out.println("Alunos cadastrados: " + arvoreAlunos.getTamanho() + " | Altura da árvore: " + arvoreAlunos.altura());

        boolean repetido = arvoreAlunos.inserir(new Aluno("2026005", "Aluno Repetido", "Direito"));
        System.out.println("Cadastrar matrícula repetida (2026005): " + (repetido ? "aceito" : "recusado, matrícula já existe"));

        System.out.println("\n--- Listagem ordenada por matrícula ---");
        listar(arvoreAlunos);

        System.out.println("\n--- Buscas ---");
        buscar(arvoreAlunos, "2026013");
        buscar(arvoreAlunos, "2026022");
        buscar(arvoreAlunos, "2026099");

        System.out.println("\n--- Remoções (um caso de cada tipo) ---");
        remover(arvoreAlunos, "2026022", "nó folha");
        remover(arvoreAlunos, "2026001", "nó com um filho");
        remover(arvoreAlunos, "2026016", "nó com dois filhos");
        remover(arvoreAlunos, "2026011", "raiz, com dois filhos");
        remover(arvoreAlunos, "2026500", "matrícula inexistente");

        System.out.println("\n--- Listagem após as remoções (" + arvoreAlunos.getTamanho()
                + " alunos, altura " + arvoreAlunos.altura() + ") ---");
        listar(arvoreAlunos);
    }

    private static void listar(EstruturaBST<Aluno> arvore) {
        arvore.inOrder(aluno -> System.out.println("   " + aluno));
    }

    private static void buscar(EstruturaBST<Aluno> arvore, String matricula) {
        Aluno resultado = arvore.buscar(new Aluno(matricula, "", ""));
        System.out.println("Buscar " + matricula + ": " + (resultado != null ? "encontrado -> " + resultado : "não encontrado"));
    }

    private static void remover(EstruturaBST<Aluno> arvore, String matricula, String caso) {
        boolean ok = arvore.remover(new Aluno(matricula, "", ""));
        System.out.println("Remover " + matricula + " (" + caso + "): " + (ok ? "removido" : "não encontrado"));
    }
}
