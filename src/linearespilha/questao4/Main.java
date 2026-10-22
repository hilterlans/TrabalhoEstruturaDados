package linearespilha.questao4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VerificadorExpressoes verificador = new VerificadorExpressoes();

        String[] exemplos = {
                "{[()]}",
                "([{}])",
                "(a + b) * [c - d]",
                "([)]",
                "{[(])}",
                "((a + b)",
                "a + b)"
        };

        System.out.println("===== QUESTAO 4: VERIFICACAO DE EXPRESSOES =====");
        for (String expressao : exemplos) {
            System.out.println(expressao + " -> "
                    + (verificador.verificar(expressao) ? "VALIDA" : "INVALIDA"));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDigite uma expressao para verificar (ou Enter para sair): ");
        String entrada = scanner.nextLine();
        if (!entrada.isBlank()) {
            System.out.println("Resultado: "
                    + (verificador.verificar(entrada) ? "EXPRESSAO VALIDA" : "EXPRESSAO INVALIDA"));
        }
    }
}
