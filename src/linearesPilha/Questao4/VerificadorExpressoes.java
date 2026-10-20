package linearesPilha.Questao4;

import linearesPilha.EstruturaPilha;

public class VerificadorExpressoes {
    public boolean verificar(String expressao) {
        if (expressao == null) {
            return false;
        }

        EstruturaPilha.Pilha<Character> pilha = new EstruturaPilha.Pilha<>();

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                pilha.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (pilha.isEmpty()) {
                    return false;
                }

                char abertura = pilha.pop();
                if (!combina(abertura, c)) {
                    return false;
                }
            }
        }

        return pilha.isEmpty();
    }

    private boolean combina(char abertura, char fechamento) {
        return (abertura == '(' && fechamento == ')')
                || (abertura == '[' && fechamento == ']')
                || (abertura == '{' && fechamento == '}');
    }
}
