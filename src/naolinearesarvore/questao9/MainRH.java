/**
 * QUESTÃO 9 – Índice de CPF para o sistema de RH (AVL)
 *
 * Justificativa de escolha:
 *   Com milhares de funcionários, a busca precisa ser rápida no PIOR caso. Uma
 *   BST comum degenera em lista quando os CPFs chegam em ordem (por exemplo,
 *   importados de uma planilha ordenada) e a busca passa a ser O(n). A AVL
 *   rebalanceia por rotações a cada inserção/remoção e garante altura
 *   O(log n), logo busca, inserção e remoção O(log n) sempre.
 *
 * Complexidade: inserir, buscar e remover O(log n); verificar balanceamento
 * O(n); espaço O(n).
 *
 * CPFs desta simulação são fictícios (não passam pela validação de dígitos).
 */
package naolinearesarvore.questao9;

import naolinearesarvore.EstruturaAVL;
import naolinearesarvore.EstruturaBST;

public class MainRH {
    public static void main(String[] args) {
        String[] nomes = {
            "João Silva", "Maria Oliveira", "Carlos Souza", "Ana Pereira", "Pedro Santos", "Juliana Costa", "Lucas Almeida",
            "Fernanda Lima", "Rafael Gomes", "Camila Rocha", "Bruno Carvalho", "Patrícia Dias", "Marcelo Ribeiro",
            "Aline Martins", "Diego Barbosa", "Larissa Cunha", "Felipe Araújo", "Vanessa Moura", "Gustavo Nunes",
            "Renata Freitas", "Thiago Pinto", "Bianca Teixeira", "Rodrigo Melo", "Priscila Castro", "Eduardo Ramos",
            "Tatiana Lopes", "André Monteiro", "Débora Farias"
        };
        String[] cargos = {"Analista", "Gerente", "Assistente", "Desenvolvedor", "Coordenador", "Auxiliar administrativo", "Contador"};

        EstruturaAVL<Funcionario> avl = new EstruturaAVL<>();
        EstruturaBST<Funcionario> bst = new EstruturaBST<>();

        System.out.println("===== QUESTÃO 9 – ÍNDICE DE CPF DO RH (AVL) =====\n");

        // Pior caso para uma BST: CPFs em ordem crescente
        for (int i = 0; i < nomes.length; i++) {
            Funcionario f = new Funcionario(cpfFicticio(i), nomes[i], cargos[i % cargos.length]);
            avl.inserir(f);
            bst.inserir(f);
        }

        int n = avl.getTamanho();
        System.out.println("Funcionários inseridos em ordem crescente de CPF: " + n);
        System.out.println("Altura da BST comum (sem balanceamento): " + bst.altura() + "  <- virou uma lista");
        System.out.println("Altura da AVL: " + avl.altura() + " (mínimo possível: " + alturaMinima(n) + ")");
        System.out.println("Fator de balanceamento da raiz: " + avl.fatorBalanceamentoRaiz());
        System.out.println("Todos os nós com |fator| <= 1? " + avl.estaBalanceada());

        System.out.println("\n--- Estrutura de uma AVL pequena (7 funcionários) ---");
        EstruturaAVL<Funcionario> pequena = new EstruturaAVL<>();
        for (int i = 0; i < 7; i++) {
            pequena.inserir(new Funcionario(cpfFicticio(i), nomes[i], cargos[i]));
        }
        pequena.imprimirArvore(f -> f.getNome());

        System.out.println("\n--- Listagem ordenada por CPF (primeiros 5) ---");
        int[] contador = {0};
        avl.inOrder(f -> {
            if (contador[0]++ < 5) System.out.println("   " + f);
        });

        System.out.println("\n--- Buscas ---");
        buscar(avl, cpfFicticio(9));
        buscar(avl, cpfFicticio(27));
        buscar(avl, "999.999.999-99");

        System.out.println("\n--- Inserção repetida ---");
        boolean repetido = avl.inserir(new Funcionario(cpfFicticio(3), "Impostor", "Nenhum"));
        System.out.println("Inserir CPF já cadastrado: " + (repetido ? "aceito" : "recusado, CPF já existe"));

        System.out.println("\n--- Remoções (a árvore é rebalanceada a cada uma) ---");
        int[] remover = {0, 1, 2, 3, 13, 20, 27};
        for (int i : remover) {
            boolean ok = avl.remover(new Funcionario(cpfFicticio(i)));
            System.out.println("Remover " + cpfFicticio(i) + ": " + (ok ? "removido" : "não encontrado")
                    + " | tamanho " + avl.getTamanho() + ", altura " + avl.altura()
                    + ", fator da raiz " + avl.fatorBalanceamentoRaiz() + ", balanceada? " + avl.estaBalanceada());
        }
        System.out.println("Remover CPF inexistente: " + (avl.remover(new Funcionario("999.999.999-99")) ? "removido" : "não encontrado"));
    }

    // CPFs fictícios em ordem crescente: 100.200.300-00, 101.201.301-07, ...
    private static String cpfFicticio(int i) {
        return String.format("%03d.%03d.%03d-%02d", 100 + i, 200 + i, 300 + i, (i * 7) % 100);
    }

    private static void buscar(EstruturaAVL<Funcionario> avl, String cpf) {
        Funcionario f = avl.buscar(new Funcionario(cpf));
        System.out.println("Buscar " + cpf + ": " + (f != null ? "encontrado -> " + f : "não encontrado"));
    }

    /** Menor altura possível para n nós: teto de log2(n + 1). */
    private static int alturaMinima(int n) {
        int altura = 0;
        while ((1 << altura) < n + 1) altura++;
        return altura;
    }
}
