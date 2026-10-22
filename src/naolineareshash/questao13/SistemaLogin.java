/**
 * QUESTÃO 13 - Cadastro de Usuários com Login Rápido
 *
 * Reutiliza a classe EstruturaTabelaHash (encadeamento separado). A chave é
 * o login (e-mail ou nome de usuário, sem diferenciar maiúsculas) e o valor
 * é o objeto Usuario, que guarda o hash da senha.
 *
 * Justificativa da estrutura: a operação predominante é localizar um usuário
 * por chave exata no momento do login, e a ordem dos usuários não importa.
 * A tabela hash faz isso em O(1) médio, contra O(log n) de uma árvore
 * balanceada ou O(n) de uma lista.
 *
 * Complexidade (k = tamanho do login):
 *   - cadastrar ... O(k) médio (mais o custo do SHA-256)
 *   - autenticar .. O(k) médio (mais o custo do SHA-256)
 *   - removerConta  O(k) médio
 *   - listar ...... O(n + m)  (n = usuários, m = capacidade da tabela)
 */
package naolineareshash.questao13;

import naolineareshash.EstruturaTabelaHash;

public class SistemaLogin {

    private static final int TAMANHO_MINIMO_SENHA = 6;

    private final EstruturaTabelaHash<Usuario> usuarios;

    public SistemaLogin() {
        this.usuarios = new EstruturaTabelaHash<Usuario>();
    }

    /** Permite escolher capacidade e fator de carga (útil para forçar colisões em testes). */
    public SistemaLogin(int capacidadeInicial, double fatorCargaMaximo) {
        this.usuarios = new EstruturaTabelaHash<Usuario>(capacidadeInicial, fatorCargaMaximo);
    }

    // ------------------------------------------------------------------
    // Operações
    // ------------------------------------------------------------------

    /** Cadastra um novo usuário. Login repetido ou senha curta é erro. */
    public void cadastrar(String login, String nome, String senha) {
        String chave = normalizar(login);
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (senha == null || senha.length() < TAMANHO_MINIMO_SENHA) {
            throw new IllegalArgumentException(
                    "Senha deve ter pelo menos " + TAMANHO_MINIMO_SENHA + " caracteres.");
        }
        if (usuarios.contem(chave)) {
            throw new IllegalArgumentException("Login já cadastrado: " + chave);
        }
        usuarios.inserir(chave, new Usuario(chave, nome.trim(), senha));
    }

    /**
     * Valida login e senha. Devolve false tanto para login inexistente quanto
     * para senha errada, sem revelar qual dos dois falhou.
     */
    public boolean autenticar(String login, String senha) {
        Usuario usuario = usuarios.buscar(normalizar(login));
        return usuario != null && usuario.senhaConfere(senha);
    }

    /** Remove a conta, exigindo a senha correta. */
    public void removerConta(String login, String senha) {
        if (!autenticar(login, senha)) {
            throw new IllegalArgumentException("Login ou senha inválidos: não foi possível remover a conta.");
        }
        usuarios.remover(normalizar(login));
    }

    /** Imprime os usuários cadastrados (login, nome e início do hash da senha). */
    public void listar() {
        String[] logins = usuarios.chaves();
        if (logins.length == 0) {
            System.out.println("  (nenhum usuário cadastrado)");
            return;
        }
        for (String login : logins) {
            Usuario u = usuarios.buscar(login);
            System.out.println(String.format("  %-28s %-18s hash: %s",
                    u.getLogin(), u.getNome(), u.hashResumido()));
        }
    }

    // ------------------------------------------------------------------
    // Estatísticas da tabela (para os testes de colisão)
    // ------------------------------------------------------------------

    public int getTotalUsuarios() {
        return usuarios.tamanho();
    }

    public int getCapacidade() {
        return usuarios.getCapacidade();
    }

    public double getFatorDeCarga() {
        return usuarios.getFatorDeCarga();
    }

    public int getTotalDeColisoes() {
        return usuarios.getTotalDeColisoes();
    }

    public int getMaiorCadeia() {
        return usuarios.getMaiorCadeia();
    }

    public void imprimirEstruturaInterna() {
        usuarios.imprimirEstrutura();
    }

    // ------------------------------------------------------------------
    // Auxiliares
    // ------------------------------------------------------------------

    private String normalizar(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login não pode ser vazio.");
        }
        return login.trim().toLowerCase();
    }
}