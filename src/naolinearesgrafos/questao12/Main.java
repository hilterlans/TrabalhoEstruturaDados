
package naolinearesgrafos.questao12;

public class Main {

    public static void main(String[] args) {
        RedeSocial rede = new RedeSocial();

        String[] usuarios = {
                "Ana", "Bruno", "Carla", "Diego", "Elisa", "Felipe",
                "Gabriela", "Hugo", "Isabela", "João", "Karina", "Lucas",
                "Marina", "Nicolas", "Olívia", "Paulo", "Renata", "Sofia"
        };
        for (String u : usuarios) {
            rede.adicionarUsuario(u);
        }

        // Comunidade 1 (faculdade)
        amizade(rede, "Ana", "Bruno");
        amizade(rede, "Ana", "Carla");
        amizade(rede, "Ana", "Diego");
        amizade(rede, "Bruno", "Carla");
        amizade(rede, "Bruno", "Elisa");
        amizade(rede, "Carla", "Diego");
        amizade(rede, "Diego", "Felipe");
        amizade(rede, "Elisa", "Gabriela");
        amizade(rede, "Felipe", "Gabriela");
        amizade(rede, "Gabriela", "Hugo");
        amizade(rede, "Hugo", "Isabela");
        amizade(rede, "Isabela", "João");

        // Comunidade 2 (trabalho)
        amizade(rede, "Karina", "Lucas");
        amizade(rede, "Karina", "Marina");
        amizade(rede, "Lucas", "Marina");
        amizade(rede, "Lucas", "Nicolas");
        amizade(rede, "Marina", "Olívia");
        amizade(rede, "Nicolas", "Olívia");
        amizade(rede, "Olívia", "Paulo");
        amizade(rede, "Paulo", "Renata");

        // Ponte entre as comunidades
        amizade(rede, "João", "Karina");

        // Sofia fica sem nenhuma amizade (usuária isolada).

        System.out.println("=== REDE SOCIAL: " + rede.getTotalUsuarios() + " usuários, "
                + rede.getTotalAmizades() + " amizades ===\n");

        System.out.println("--- Lista de adjacência da rede ---");
        rede.imprimirRede();

        System.out.println("\n--- Listar amigos ---");
        mostrarAmigos(rede, "Ana");
        mostrarAmigos(rede, "Gabriela");
        mostrarAmigos(rede, "Sofia");

        System.out.println("\n--- Amigos em comum ---");
        mostrarEmComum(rede, "Ana", "Bruno");
        mostrarEmComum(rede, "Bruno", "Diego");
        mostrarEmComum(rede, "Lucas", "Olívia");
        mostrarEmComum(rede, "Ana", "Karina");

        System.out.println("\n--- Graus de separação (BFS) ---");
        mostrarSeparacao(rede, "Ana", "Ana");
        mostrarSeparacao(rede, "Ana", "Bruno");
        mostrarSeparacao(rede, "Ana", "Elisa");
        mostrarSeparacao(rede, "Ana", "João");
        mostrarSeparacao(rede, "Ana", "Renata");
        mostrarSeparacao(rede, "Ana", "Sofia");

        System.out.println("\n--- Sugestões de amizade ---");
        mostrarSugestoes(rede, "Ana");
        mostrarSugestoes(rede, "Lucas");
        mostrarSugestoes(rede, "Sofia");

        System.out.println("\n--- Tratamento de erros ---");
        tentar(() -> rede.adicionarAmizade("Ana", "Bruno"));
        tentar(() -> rede.adicionarAmizade("Ana", "Ana"));
        tentar(() -> rede.adicionarAmizade("Ana", "Zeca"));
        tentar(() -> rede.adicionarUsuario("Ana"));
        tentar(() -> rede.listarAmigos("Zeca"));
    }

    // ------------------------------------------------------------------
    // Auxiliares de impressão
    // ------------------------------------------------------------------

    private static void amizade(RedeSocial rede, String a, String b) {
        rede.adicionarAmizade(a, b);
    }

    private static void mostrarAmigos(RedeSocial rede, String usuario) {
        String[] amigos = rede.listarAmigos(usuario);
        System.out.println("Amigos de " + usuario + " (" + amigos.length + "): "
                + (amigos.length == 0 ? "nenhum" : String.join(", ", amigos)));
    }

    private static void mostrarEmComum(RedeSocial rede, String a, String b) {
        String[] comuns = rede.amigosEmComum(a, b);
        System.out.println("Em comum entre " + a + " e " + b + ": "
                + (comuns.length == 0 ? "nenhum" : String.join(", ", comuns)));
    }

    private static void mostrarSeparacao(RedeSocial rede, String a, String b) {
        int graus = rede.grausDeSeparacao(a, b);
        if (graus == -1) {
            System.out.println(a + " -> " + b + ": sem conexão");
            return;
        }
        String[] cadeia = rede.cadeiaDeConexao(a, b);
        System.out.println(a + " -> " + b + ": " + graus + " grau(s) | "
                + String.join(" > ", cadeia));
    }

    private static void mostrarSugestoes(RedeSocial rede, String usuario) {
        String[] sugestoes = rede.sugerirAmigos(usuario);
        System.out.println("Sugestões para " + usuario + ": "
                + (sugestoes.length == 0 ? "nenhuma" : String.join(", ", sugestoes)));
    }

    private static void tentar(Runnable acao) {
        try {
            acao.run();
            System.out.println("OK (nenhum erro)");
        } catch (RuntimeException e) {
            System.out.println("Erro tratado: " + e.getMessage());
        }
    }
}