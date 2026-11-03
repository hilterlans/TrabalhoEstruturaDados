package ordenacao.Questao22;


public class Main {
    public static void main(String[] args) {
        String[] departamentoA = {
            "Ana", "Beatriz", "Bruno", "Caio", "Carla", "Daniel", "Eduardo", "Elisa", "Fabio", "Fernanda",
            "Gabriel", "Helena", "Igor", "Joana", "Lucas", "Marcos", "Nadia", "Paula", "Rafael", "Sofia"
        };
        String[] departamentoB = {
            "Alice", "Ana", "Arthur", "Beatriz", "Bianca", "Caio", "Clara", "Daniel", "Diego", "Eduardo",
            "Felipe", "Gabriel", "Giovana", "Joana", "Julia", "Lucas", "Marina", "Paula", "Tiago", "Vitor"
        };
        System.out.println("=== LISTA DO DEPARTAMENTO A ===");
        imprimirLinha(departamentoA);
        System.out.println("\n=== LISTA DO DEPARTAMENTO B ===");
        imprimirLinha(departamentoB);
        String[] resultado = mesclarSemDuplicatas(departamentoA, departamentoB);
        System.out.println("\n=== LISTA UNICA ORDENADA, SEM DUPLICATAS ===");
        for (String nome : resultado) System.out.println(nome);
        System.out.println("Total de contatos: " + resultado.length);
    }
    private static void imprimirLinha(String[] nomes) {
        System.out.print("[");
        for (int i = 0; i < nomes.length; i++) {
            System.out.print(nomes[i]);
            if (i < nomes.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    private static String[] mesclarSemDuplicatas(String[] a, String[] b) {
        String[] aux = new String[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            int cmp = a[i].compareToIgnoreCase(b[j]);
            String escolhido;
            if (cmp < 0) escolhido = a[i++];
            else if (cmp > 0) escolhido = b[j++];
            else { escolhido = a[i++]; j++; }
            if (k == 0 || !aux[k - 1].equalsIgnoreCase(escolhido)) aux[k++] = escolhido;
        }
        while (i < a.length) {
            String nome = a[i++];
            if (k == 0 || !aux[k - 1].equalsIgnoreCase(nome)) aux[k++] = nome;
        }
        while (j < b.length) {
            String nome = b[j++];
            if (k == 0 || !aux[k - 1].equalsIgnoreCase(nome)) aux[k++] = nome;
        }
        String[] resultado = new String[k];
        for (int x = 0; x < k; x++) {
            resultado[x] = aux[x];
        }
        return resultado;
    }
}
