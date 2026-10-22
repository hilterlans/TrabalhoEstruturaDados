/**
 * QUESTÃO 13 - Simulação: 22 usuários, autenticação, remoção de conta,
 * tratamento de erros e teste de colisões em uma tabela pequena.
 */
package naolineareshash.questao13;

public class Main {

    public static void main(String[] args) {
        // Tabela começa com 8 baldes e cresce sozinha (fator de carga máximo 0,75).
        SistemaLogin sistema = new SistemaLogin(8, 0.75);

        String[][] cadastros = {
                {"ana.silva@email.com", "Ana Silva"},
                {"bruno.costa@email.com", "Bruno Costa"},
                {"carla.dias@email.com", "Carla Dias"},
                {"diego.lima@email.com", "Diego Lima"},
                {"elisa.rocha@email.com", "Elisa Rocha"},
                {"felipe.alves@email.com", "Felipe Alves"},
                {"gabriela.nunes@email.com", "Gabriela Nunes"},
                {"hugo.martins@email.com", "Hugo Martins"},
                {"isabela.ramos@email.com", "Isabela Ramos"},
                {"joao.pereira@email.com", "João Pereira"},
                {"karina.melo@email.com", "Karina Melo"},
                {"lucas.barros@email.com", "Lucas Barros"},
                {"marina.freitas@email.com", "Marina Freitas"},
                {"nicolas.araujo@email.com", "Nicolas Araújo"},
                {"olivia.campos@email.com", "Olívia Campos"},
                {"paulo.souza@email.com", "Paulo Souza"},
                {"renata.gomes@email.com", "Renata Gomes"},
                {"sofia.teixeira@email.com", "Sofia Teixeira"},
                {"thiago.moura@email.com", "Thiago Moura"},
                {"ursula.pinto@email.com", "Úrsula Pinto"},
                {"victor.cardoso@email.com", "Victor Cardoso"},
                {"wanda.lopes@email.com", "Wanda Lopes"}
        };

        System.out.println("=== CADASTRO DE USUÁRIOS ===");
        System.out.println("Capacidade inicial da tabela: " + sistema.getCapacidade());
        for (int i = 0; i < cadastros.length; i++) {
            sistema.cadastrar(cadastros[i][0], cadastros[i][1], "senha@" + (1000 + i));
        }
        System.out.println(sistema.getTotalUsuarios() + " usuários cadastrados.");
        imprimirEstatisticas(sistema);

        System.out.println("\n--- Usuários cadastrados ---");
        sistema.listar();

        System.out.println("\n=== AUTENTICAÇÃO ===");
        testarLogin(sistema, "ana.silva@email.com", "senha@1000");
        testarLogin(sistema, "ANA.SILVA@email.com", "senha@1000");   // maiúsculas não importam
        testarLogin(sistema, "ana.silva@email.com", "senha-errada");
        testarLogin(sistema, "fantasma@email.com", "senha@1000");
        testarLogin(sistema, "wanda.lopes@email.com", "senha@1021");

        System.out.println("\n=== REMOÇÃO DE CONTA ===");
        tentar("Remover Bruno com senha errada",
                () -> sistema.removerConta("bruno.costa@email.com", "xxxxxx"));
        tentar("Remover Bruno com senha correta",
                () -> sistema.removerConta("bruno.costa@email.com", "senha@1001"));
        testarLogin(sistema, "bruno.costa@email.com", "senha@1001");
        System.out.println("Usuários restantes: " + sistema.getTotalUsuarios());

        System.out.println("\n=== TRATAMENTO DE ERROS ===");
        tentar("Cadastrar login repetido",
                () -> sistema.cadastrar("ana.silva@email.com", "Outra Ana", "abcdef"));
        tentar("Cadastrar senha curta",
                () -> sistema.cadastrar("novo@email.com", "Novo", "123"));
        tentar("Cadastrar login vazio",
                () -> sistema.cadastrar("   ", "Sem Login", "abcdef"));
        tentar("Remover conta inexistente",
                () -> sistema.removerConta("fantasma@email.com", "abcdef"));

        testeDeColisao();
    }

    /**
     * Força colisões: 12 usuários em uma tabela de 5 baldes que NÃO cresce
     * (fator de carga máximo 100). Mostra as cadeias e prova que o login
     * continua funcionando para todos, mesmo com baldes compartilhados.
     */
    private static void testeDeColisao() {
        System.out.println("\n=== TESTE DE COLISÃO (5 baldes, sem redimensionar) ===");
        SistemaLogin pequeno = new SistemaLogin(5, 100.0);

        String[] logins = {"u01", "u02", "u03", "u04", "u05", "u06",
                "u07", "u08", "u09", "u10", "u11", "u12"};
        for (String login : logins) {
            pequeno.cadastrar(login, "Usuário " + login, "senha-" + login);
        }
        imprimirEstatisticas(pequeno);
        System.out.println("Estrutura interna (balde -> cadeia):");
        pequeno.imprimirEstruturaInterna();

        int acertos = 0;
        for (String login : logins) {
            if (pequeno.autenticar(login, "senha-" + login)) {
                acertos++;
            }
        }
        System.out.println("Logins corretos mesmo com colisões: " + acertos + "/" + logins.length);
    }

    // ------------------------------------------------------------------
    // Auxiliares de impressão
    // ------------------------------------------------------------------

    private static void imprimirEstatisticas(SistemaLogin sistema) {
        System.out.println(String.format(
                "Capacidade: %d | Usuários: %d | Fator de carga: %.2f | Colisões: %d | Maior cadeia: %d",
                sistema.getCapacidade(), sistema.getTotalUsuarios(), sistema.getFatorDeCarga(),
                sistema.getTotalDeColisoes(), sistema.getMaiorCadeia()));
    }

    private static void testarLogin(SistemaLogin sistema, String login, String senha) {
        boolean ok = sistema.autenticar(login, senha);
        System.out.println("Login " + login + ": " + (ok ? "ACEITO" : "NEGADO"));
    }

    private static void tentar(String descricao, Runnable acao) {
        try {
            acao.run();
            System.out.println(descricao + ": OK");
        } catch (RuntimeException e) {
            System.out.println(descricao + ": erro tratado -> " + e.getMessage());
        }
    }
}