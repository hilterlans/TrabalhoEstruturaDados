/**
 * Texto usado na simulação (artigo curto sobre tabelas hash, escrito para o
 * trabalho). Para analisar outro texto, passe o caminho de um arquivo .txt
 * como argumento do Main.
 */
package naolineareshash.questao14;

public class TextoExemplo {

    public static final String ARTIGO =
            "A tabela hash é uma das estruturas de dados mais usadas na computação. "
                    + "Ela guarda pares de chave e valor e permite encontrar um valor a partir da chave "
                    + "em tempo constante médio. Para isso, a tabela usa uma função de espalhamento, que "
                    + "transforma a chave em um número. Esse número indica a posição do vetor onde o valor "
                    + "deve ser guardado.\n\n"
                    + "Quando duas chaves diferentes resultam na mesma posição, ocorre uma colisão. "
                    + "Existem duas estratégias clássicas para tratar colisões. Na primeira, chamada de "
                    + "encadeamento, cada posição do vetor guarda uma lista com todas as chaves que "
                    + "caíram ali. Na segunda, chamada de endereçamento aberto, a chave é colocada na "
                    + "próxima posição livre do vetor. Em ambos os casos, uma boa função de espalhamento "
                    + "reduz o número de colisões e mantém a tabela rápida.\n\n"
                    + "O fator de carga mede o quão cheia a tabela está. Quando ele passa de um limite, "
                    + "a tabela cresce: o vetor dobra de tamanho e todas as chaves são colocadas de novo "
                    + "nas novas posições. Esse processo de redimensionamento custa caro, mas acontece "
                    + "poucas vezes, e por isso o custo médio de cada inserção continua constante.\n\n"
                    + "As aplicações de tabelas hash são muitas. Os compiladores usam tabelas hash para "
                    + "guardar os nomes das variáveis. Os sistemas de login usam tabelas hash para "
                    + "encontrar o usuário pelo e-mail. Os navegadores usam tabelas hash como cache de "
                    + "páginas. Os analisadores de texto usam tabelas hash para contar quantas vezes cada "
                    + "palavra aparece, e é exatamente isso que este programa faz: cada palavra do texto "
                    + "é uma chave, e a quantidade de vezes que ela aparece é o valor.\n\n"
                    + "Apesar de tão úteis, as tabelas hash têm limitações. A ordem das chaves não é "
                    + "preservada, então a tabela não serve quando é preciso percorrer os dados em ordem. "
                    + "Além disso, no pior caso, quando todas as chaves caem na mesma posição, a busca "
                    + "deixa de ser constante e passa a ser linear. Por isso, a escolha da função de "
                    + "espalhamento e do fator de carga é importante para que a tabela funcione bem.";

    private TextoExemplo() {
    }
}