/**
 * Usuário do sistema. A senha NUNCA é guardada em texto puro: armazenamos
 * apenas o hash SHA-256 de (salt + senha), com um salt aleatório por usuário.
 *
 * Observação didática: em sistemas reais prefere-se um algoritmo de hash de
 * senha lento (bcrypt, scrypt, Argon2). SHA-256 com salt é suficiente aqui,
 * pois o foco do trabalho é a tabela hash.
 */
package naolineareshash.questao13;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Usuario {

    private static final int TAMANHO_SALT = 16;
    private static final SecureRandom ALEATORIO = new SecureRandom();

    private final String login;
    private final String nome;
    private final byte[] salt;
    private final byte[] senhaHash;

    public Usuario(String login, String nome, String senha) {
        this.login = login;
        this.nome = nome;
        this.salt = new byte[TAMANHO_SALT];
        ALEATORIO.nextBytes(this.salt);
        this.senhaHash = calcularHash(this.salt, senha);
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    /** Confere a senha informada comparando em tempo constante. */
    public boolean senhaConfere(String senhaInformada) {
        if (senhaInformada == null) {
            return false;
        }
        return MessageDigest.isEqual(senhaHash, calcularHash(salt, senhaInformada));
    }

    /** Primeiros caracteres do hash em hexadecimal (apenas para exibição). */
    public String hashResumido() {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            hex.append(String.format("%02x", senhaHash[i]));
        }
        return hex.toString() + "...";
    }

    private static byte[] calcularHash(byte[] salt, String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            return digest.digest(senha.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 indisponível nesta JVM.", e);
        }
    }
}
